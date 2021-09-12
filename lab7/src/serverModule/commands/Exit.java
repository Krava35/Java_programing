package serverModule.commands;

import common.exceptions.DatabaseManagerException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.User;
import serverModule.util.DatabaseManager;
import serverModule.util.DatabaseUserManager;
import serverModule.util.ResponseOutputer;

/**
 * 'exit' command. Closes the program.
 */
public class Exit extends Command {
    private DatabaseUserManager databaseUserManager;

    public Exit(DatabaseUserManager databaseUserManager) {
        super("exit", "end the program (without saving to file)");
        this.databaseUserManager = databaseUserManager;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            if (user == null) return true;
            databaseUserManager.updateOnline(user, false);
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (DatabaseManagerException e){
            ResponseOutputer.append("An error occurred while accessing the database!\n");
        }
        return false;
    }
}
