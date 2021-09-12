package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.Console;

/**
 * 'execute_script' command. Executes scripts from a file.
 */
public class ExecuteScript extends Command {
    public ExecuteScript() {
        super("execute_script file_name", "execute script from specified file");
    }

    /**
     * Executes the script.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            Console.println("Executing the script '" + argument + "'...");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}
