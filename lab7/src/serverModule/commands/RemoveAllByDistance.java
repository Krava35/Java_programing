package serverModule.commands;

import common.data.Route;
import common.exceptions.*;
import common.utility.User;
import serverModule.util.CollectionManager;
import serverModule.util.DatabaseCollectionManager;
import serverModule.util.ResponseOutputer;

/**
 * 'remove_all_by_distance' command. Removes all elements by distance.
 */
public class RemoveAllByDistance extends Command {
    private final CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public RemoveAllByDistance(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("remove_all_by_distance distance",
                "remove from the collection all elements whose distance field value is equivalent to the given one");
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
            Long distance = Long.parseLong(argument);
            if (collectionManager.getAllByDistance(distance) == null) throw new RouteNotFoundException();
            for (Route route : collectionManager.getAllByDistance(distance)){
                if (!route.getOwner().equals(user)) throw new PermissionDeniedException();
                if (!databaseCollectionManager.checkRouteByIdAndUserId(route.getId(), user)) throw new IllegalDatabaseEditException();
            }
            for (Route route : collectionManager.getAllByDistance(distance)){
                databaseCollectionManager.deleteRouteByID(route.getId());
            }
            collectionManager.removeByDistance(distance);
            ResponseOutputer.append("Routes successfully deleted!\n");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.append("The collection is empty!\n");
        } catch (RouteNotFoundException exception) {
            ResponseOutputer.append("There are no routes with such a distance in the collection!\n");
        } catch (NumberFormatException exception){
            ResponseOutputer.append("Distance must be a number!\n");
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
