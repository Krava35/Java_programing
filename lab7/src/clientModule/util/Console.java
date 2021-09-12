package clientModule.util;

import common.data.Coordinates;
import common.data.LocationFrom;
import common.data.LocationTo;
import common.exceptions.IncorrectInputInScriptException;
import common.exceptions.ScriptRecursionException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.Request;
import common.utility.ResponseCode;
import common.utility.RouteLite;
import common.utility.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Operates command input.
 */
public class Console {
    private Scanner userScanner;
    private Stack<File> scriptFileNames = new Stack<>();
    private Stack<Scanner> scannerStack = new Stack<>();
    private AuthManager authManager;

    public Console(Scanner userScanner, AuthManager authManager) {
        this.userScanner = userScanner;
        this.authManager = authManager;
    }

    private boolean fileMode() {
        return !scannerStack.isEmpty();
    }

    /**
     * Mode for catching serverModule.commands from user input.
     */
    public Request interactiveMode(ResponseCode serverResponseCode, User user) {
        String userInput = "";
        String[] userCommand = {"", ""};
        ProcessCode processCode = null;
        try {
            do {
                try {
                    if (fileMode() && (serverResponseCode == ResponseCode.SERVER_EXIT || serverResponseCode == ResponseCode.ERROR)) {
                        throw new IncorrectInputInScriptException();
                    }
                    while (fileMode() && !userScanner.hasNextLine()) {
                        userScanner.close();
                        userScanner = scannerStack.pop();
                        System.out.println("Returning from the script '" + scriptFileNames.pop().getName() + "'!");
                    }
                    if (fileMode()) {
                        userInput = userScanner.nextLine();
                        if (!userInput.isEmpty()) {
                            System.out.print("$ ");
                            System.out.println(userInput);
                        }
                    } else {
                        System.out.print("$ ");
                        if (userScanner.hasNext()) {
                            userInput = userScanner.nextLine();
                        } else {
                            System.out.println("Client completed!");
                            System.exit(0);
                        }
                    }
                    userCommand = (userInput.trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                } catch (NoSuchElementException | IllegalStateException exception) {
                    System.out.println("An error occurred while entering the command!");
                    userCommand = new String[]{"", ""};
                }
                processCode = checkCommand(userCommand[0], userCommand[1]);
            } while (processCode == ProcessCode.ERROR && !fileMode() || userCommand[0].isEmpty());
            try {
                switch (processCode) {
                    case OBJECT:
                        RouteLite routeToInsert = generateRouteToInsert();
                        return new Request(userCommand[0], userCommand[1], routeToInsert, user);
                    case UPDATE_OBJECT:
                        RouteLite routeToUpdate = generateRouteToUpdate();
                        return new Request(userCommand[0], userCommand[1], routeToUpdate, user);
                    case SCRIPT:
                        File scriptFile = new File(userCommand[1]);
                        if (!scriptFile.exists()) throw new FileNotFoundException();
                        if (!scriptFileNames.isEmpty() && scriptFileNames.search(scriptFile) != -1) {
                            throw new ScriptRecursionException();
                        }
                        scannerStack.push(userScanner);
                        scriptFileNames.push(scriptFile);
                        userScanner = new Scanner(scriptFile);
                        System.out.println("Executing the script '" + scriptFile.getName() + "'!");
                        break;
                    case LOG_IN:
                        return authManager.handle();
                }
            } catch (FileNotFoundException exception) {
                System.out.println("Script file not found!");
            } catch (ScriptRecursionException exception) {
                System.out.println("Scripts cannot be called recursively!");
                throw new IncorrectInputInScriptException();
            }
        } catch (IncorrectInputInScriptException exception) {
            System.out.println("Script execution interrupted!");
            while (!scannerStack.isEmpty()) {
                userScanner.close();
                userScanner = scannerStack.pop();
            }
        }
        return new Request(userCommand[0], userCommand[1], null, user);
    }


    /**
     * Launches the command.
     * @param command Command to launch.
     * @return Exit code.
     */
    private ProcessCode checkCommand(String command, String argument) {
        try {
            switch (command) {
                case "":
                    return ProcessCode.ERROR;
                case "help":
                case "info":
                case "show":
                case "clear":
                case "sort":
                case "group_counting_by_distance":
                case "exit":
                case "sign_out":
                    if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
                    return ProcessCode.OK;
                case "add":
                case "add_if_max":
                case "remove_lower":
                    if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
                    return ProcessCode.OBJECT;
                case "remove_all_by_distance":
                case "filter_less_than_distance":
                case "remove_by_id":
                    if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
                    return ProcessCode.OK;
                case "update":
                    if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
                    return ProcessCode.UPDATE_OBJECT;
                case "execute_script":
                    if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
                    return ProcessCode.SCRIPT;
                case "sign_in":
                    if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
                    return ProcessCode.LOG_IN;
                case "save":
                    System.out.println("This command is not available to customers!");
                    return ProcessCode.ERROR;
                default:
                    System.out.println("Command " + command +" not founded. Input 'help' for help.");
                    return ProcessCode.ERROR;
            }
        } catch (WrongAmountOfArgumentsException exception) {
            System.out.println("Check the argument is entered correctly!");
        }
        return ProcessCode.OK;
    }

    private RouteLite generateRouteToInsert() throws IncorrectInputInScriptException {
        Interactions builder = new Interactions(userScanner);
        if (fileMode()) {
            builder.setFileMode();
        } else {
            builder.setUserMode();
        }
        return new RouteLite(
                builder.askName("Input route name"),
                builder.askCoordinates(),
                builder.askLocationFrom(),
                builder.askLocationTo(),
                builder.askDistance()
        );
    }

    private RouteLite generateRouteToUpdate() throws IncorrectInputInScriptException{
        Interactions builder = new Interactions(userScanner);
        if (fileMode()) {
            builder.setFileMode();
        } else {
            builder.setUserMode();
        }
        String name = builder.askQuestion("Do you want to change the name of the route?") ?
                builder.askName("Input route name") : null;
        Coordinates coordinates = builder.askQuestion("Do you want to change the coordinates of the route?") ?
                builder.askCoordinates() : null;
        LocationFrom locationFrom = builder.askQuestion("Do yoy want to change LocationFrom of the route?") ?
                builder.askLocationFrom() : null;
        LocationTo locationTo = builder.askQuestion("Do you want to change LocationTo of the route?") ?
                builder.askLocationTo() : null;
        Long distance  = builder.askQuestion("Do you want to change distance of the route?") ?
                builder.askDistance() : -1;
        return new RouteLite(
                name,
                coordinates,
                locationFrom,
                locationTo,
                distance
        );
    }

    /**
     * Prints toOut.toString() to Console
     * @param toOut Object to print
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    /**
     * Prints toOut.toString() + \n to Console
     * @param toOut Object to print
     */
    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Prints error: toOut.toString() to Console
     * @param toOut Error to print
     */
    public static void printError(Object toOut) {
        System.out.println("error: " + toOut);
    }

    /**
     * Prints formatted 2-element table to Console
     * @param element1 Left element of the row.
     * @param element2 Right element of the row.
     */
    public static void printTable(Object element1, Object element2) {
        System.out.printf("%-60s%-1s%n", element1, element2);
    }
}
