package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.Console;

/**
 * 'help' command. Output command to manage collection.
 */
public class Help extends Command{
    public Help(){
        super("help", "display help for available commands");
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
