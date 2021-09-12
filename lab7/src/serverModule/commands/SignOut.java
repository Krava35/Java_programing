package serverModule.commands;

import common.exceptions.DatabaseManagerException;
import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.User;
import serverModule.util.DatabaseUserManager;
import serverModule.util.ResponseOutputer;

public class SignOut extends Command{
    private DatabaseUserManager databaseUserManager;

    public SignOut(DatabaseUserManager databaseUserManager) {
        super("sign_out", "sign out from your account");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (user == null) throw new NonAuthorizedUserException();
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            databaseUserManager.updateOnline(user, false);
            ResponseOutputer.append("Account signed out!\n");
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (DatabaseManagerException e) {
            ResponseOutputer.append("An error occurred while accessing the database!\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        }
        return false;
    }
}
