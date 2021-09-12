package serverModule.commands;


import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.User;
import serverModule.util.ResponseOutputer;

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
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (user == null) throw new NonAuthorizedUserException();
            if (argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            ResponseOutputer.append("Executing the script '" + argument + "'...\n");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        }
        return false;
    }
}
