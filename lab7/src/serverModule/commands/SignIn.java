package serverModule.commands;

import common.exceptions.DatabaseManagerException;
import common.exceptions.MultiUserException;
import common.exceptions.UserNotFoundException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.User;
import serverModule.util.DatabaseUserManager;
import serverModule.util.ResponseOutputer;

public class SignIn extends Command{
    private DatabaseUserManager databaseUserManager;

    public SignIn(DatabaseUserManager databaseUserManager) {
        super("sign_in", "sign in to your account");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            if (databaseUserManager.checkUserByUsernameAndPassword(user)) {
                ResponseOutputer.append("Authorization was successful!\n");
                databaseUserManager.updateOnline(user, true);
            }
            else throw new UserNotFoundException();
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (DatabaseManagerException e) {
            ResponseOutputer.append("An error occurred while accessing the database!\n");
        } catch (UserNotFoundException e) {
            ResponseOutputer.append("Wrong username or password!\n");
        } catch (ClassCastException e) {
            ResponseOutputer.append("The object submitted by the client is invalid!\n");
        } catch (MultiUserException e) {
            ResponseOutputer.append("This user is already logged in!\n");
        }
        return false;
    }
}
