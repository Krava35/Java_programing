package serverModule.commands;

import common.exceptions.WrongAmountOfArgumentsException;
import serverModule.util.ResponseOutputer;

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
