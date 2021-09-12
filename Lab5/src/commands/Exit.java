package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.Console;

/**
 * 'exit' command. Closes the program.
 */
public class Exit extends Command {

    public Exit() {
        super("exit", "end the program (without saving to file)");
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}
