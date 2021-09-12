package serverModule.commands;

import common.exceptions.WrongAmountOfArgumentsException;
import serverModule.util.ResponseOutputer;

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
    public boolean execute(String argument, Object objectArgument) {
        try {
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        }
        return false;
    }
}
