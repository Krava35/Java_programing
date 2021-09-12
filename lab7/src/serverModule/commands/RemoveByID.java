package serverModule.commands;

import common.data.Route;
import common.exceptions.*;
import common.utility.User;
import serverModule.util.*;

/**
 * 'remove_by_id' command. Removes the element by its ID.
 */
public class RemoveByID extends Command {
    private final CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public RemoveByID(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("remove_by_id id", "remove an item from the collection by its ID");
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
            if (argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Integer id = Integer.parseInt(argument);
            Route route = collectionManager.getById(id);
            if (route == null) throw new RouteNotFoundException();
            if (!route.getOwner().equals(user)) throw new PermissionDeniedException();
            if (!databaseCollectionManager.checkRouteByIdAndUserId(route.getId(), user)) throw new IllegalDatabaseEditException();
            databaseCollectionManager.deleteRouteByID(route.getId());
            collectionManager.removeFromCollection(route);
            ResponseOutputer.append("Routes successfully deleted!\n");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.append("The collection is empty!\n");
        } catch (NumberFormatException exception) {
            ResponseOutputer.append("ID must be a number!\n");
        } catch (RouteNotFoundException exception) {
            ResponseOutputer.append("There is no route with this ID in the collection!\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        } catch (PermissionDeniedException e){
            ResponseOutputer.append("Objects owned by other users are read-only!\n");
        } catch (IllegalDatabaseEditException e){
            ResponseOutputer.append("Illegal modification of an object in the database has occurred!\n");
            ResponseOutputer.append("Restart the client app to avoid errors!\n");
        } catch (DatabaseManagerException e){
            ResponseOutputer.append("An error occurred while accessing the database!\n");
        }
        return false;
    }
}
