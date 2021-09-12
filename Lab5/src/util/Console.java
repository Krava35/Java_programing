package util;

import exceptions.NoAccessToFileException;
import exceptions.ScriptRecursionException;
import run.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Operates command input.
 */
public class Console {
    private final CommandManager commandManager;
    private final Scanner userScanner;
    private final Interactions interaction;
    private final List<String> scriptStack = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner userScanner, Interactions interaction) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.interaction = interaction;
    }

    /**
     * Mode for catching commands from user input.
     */
    public void interactiveMode() {
        String userCommand[];
        int commandStatus;
        try {
            do {
                Console.print(Main.INPUT_COMMAND);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            Console.printError("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            Console.printError("An unexpected error!");
        }
    }

    /**
     * Mode for catching commands from a script.
     * @param argument Its argument.
     * @return Exit code.
     */
    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try {
            File file = new File(argument);
            if (file.exists() && !file.canRead()) throw new NoAccessToFileException();
            Scanner scriptScanner = new Scanner(file);
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = interaction.getUserScanner();
            interaction.setUserScanner(scriptScanner);
            interaction.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(Main.INPUT_COMMAND + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            interaction.setUserScanner(tmpScanner);
            interaction.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Check the script for the correctness of the entered data!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printError("Script file not found!");
        } catch (NoSuchElementException exception) {
            Console.printError("The script file is empty!");
        } catch (ScriptRecursionException exception) {
            Console.printError("Scripts cannot be called recursively!");
        } catch (IllegalStateException exception) {
            Console.printError("An unexpected error!");
            System.exit(0);
        } catch (NoAccessToFileException e) {
            Console.printError("File access denied!");
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }

    /**
     * Launch the command.
     * @param userCommand Command to launch.
     * @return Exit code.
     */
    private int launchCommand(String[] userCommand) {
        String command = userCommand[0];
        String argument = userCommand[1];
        switch (command) {
            case "":
                break;
            case "help":
                if (!commandManager.help(argument)) return 1;
                break;
            case "info":
                if (!commandManager.info(argument)) return 1;
                break;
            case "show":
                if (!commandManager.show(argument)) return 1;
                break;
            case "add":
                if (!commandManager.add(argument)) return 1;
                break;
            case "update":
                if (!commandManager.update(argument)) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(argument)) return 1;
                break;
            case "clear":
                if (!commandManager.clear(argument)) return 1;
                break;
            case "save":
                if (!commandManager.save(argument)) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(argument)) return 1;
                else return scriptMode(argument);
            case "add_if_max":
                if (!commandManager.addIfMax(argument)) return 1;
                break;
            case "remove_lower":
                if (!commandManager.removeLower(argument)) return 1;
                break;
            case "sort":
                if (!commandManager.sort(argument)) return 1;
                break;
            case "remove_all_by_distance":
                if (!commandManager.removeAllByDistance(argument)) return 1;
                break;
            case "group_counting_by_distance":
                if (!commandManager.groupCountingByDistance(argument)) return 1;
                break;
            case "filter_less_than_distance":
                if (commandManager.filterLessThanDistance(argument)) return 1;
            case "exit":
                if (!commandManager.exit(argument)) return 1;
                else return 2;
            default:
                if (!commandManager.noSuchCommand(command)) return 1;
        }
        return 0;
    }

    /**
     * Prints object to Console.
     * @param toOut Object to print
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    /**
     * Prints object to Console in a new line.
     * @param toOut Object to print
     */
    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Prints error to Console
     * @param toOut Error to print
     */
    public static void printError(Object toOut) {
        System.out.println("Error: " + toOut);
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
