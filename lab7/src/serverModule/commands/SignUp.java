package serverModule.commands;

import common.exceptions.DatabaseManagerException;
import common.exceptions.UserAlreadyExistException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.User;
import serverModule.util.DatabaseUserManager;
import serverModule.util.ResponseOutputer;

public class SignUp extends Command{
    private DatabaseUserManager databaseUserManager;

    public SignUp(DatabaseUserManager databaseUserManager) {
        super("sign_up", "new User Registration");
        this.databaseUserManager = databaseUserManager;
    }

    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            if (databaseUserManager.insertUser(user))
                ResponseOutputer.append("registration was successfully!\n");
            else throw new UserAlreadyExistException();
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (DatabaseManagerException exception) {
            ResponseOutputer.append("An error occurred while accessing the database!\n");
        } catch (UserAlreadyExistException e) {
            ResponseOutputer.append("This user already exists!\n");
        } catch (ClassCastException e) {
            ResponseOutputer.append("The object submitted by the client is invalid!\n");
        }
        return false;
    }
}
