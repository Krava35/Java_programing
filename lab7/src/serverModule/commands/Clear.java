package serverModule.commands;


import common.data.Route;
import common.exceptions.*;
import common.utility.User;
import serverModule.util.CollectionManager;
import serverModule.util.DatabaseCollectionManager;
import serverModule.util.ResponseOutputer;

/**
 *  'clear' command. Clears the collection.
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public Clear(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (user == null) throw new NonAuthorizedUserException();
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            for (Route route : collectionManager.getCollection()) {
                if (!route.getOwner().equals(user)) throw new PermissionDeniedException();
                if (!databaseCollectionManager.checkRouteByIdAndUserId(route.getId(), user)) throw new IllegalDatabaseEditException();
            }
            databaseCollectionManager.clearCollection();
            collectionManager.clearCollection();
            ResponseOutputer.append("The collection has been cleared!\n");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        } catch (PermissionDeniedException e){
            ResponseOutputer.append("Objects owned by other users are read-only!\n");
        } catch (DatabaseManagerException e){
            ResponseOutputer.append("An error occurred while accessing the database!\n");
        } catch (IllegalDatabaseEditException e){
            ResponseOutputer.append("Illegal modification of an object in the database has occurred!\n");
            ResponseOutputer.append("Restart the client app to avoid errors!\n");
        }
        return false;
    }
}
