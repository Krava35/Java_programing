package serverModule.commands;


import common.data.Route;
import common.exceptions.*;
import common.utility.RouteLite;
import common.utility.User;
import serverModule.util.CollectionManager;
import serverModule.util.DatabaseCollectionManager;
import serverModule.util.ResponseOutputer;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 'remove_lower' command. Remove all elements lower than user entered.
 * */
public class RemoveLower extends Command {
    private final CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public RemoveLower(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("remove_lower {element}",
                "remove all elements from the collection that are less than the given one");
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
            if (!argument.isEmpty() || objectArgument == null) throw new WrongAmountOfArgumentsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            RouteLite routeLite = (RouteLite) objectArgument;
            Route routeToFind = new Route(
                    collectionManager.generateNextId(),
                    routeLite.getName(),
                    routeLite.getCoordinates(),
                    LocalDateTime.now(),
                    routeLite.getLocationFrom(),
                    routeLite.getLocationTo(),
                    routeLite.getDistance(),
                    user
            );
            List<Route> routes = collectionManager.getLower(routeToFind);
            int k = 0;
            for (Route route : routes) {
                if (!route.getOwner().equals(user)) continue;
                if (!databaseCollectionManager.checkRouteByIdAndUserId(route.getId(), user)) throw new IllegalDatabaseEditException();
                databaseCollectionManager.deleteRouteByID(route.getId());
                collectionManager.removeFromCollection(route);
                k ++ ;
            }
            ResponseOutputer.append("Removed routes: " + k);
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.append("The collection is empty!\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        } catch (IllegalDatabaseEditException e){
            ResponseOutputer.append("Illegal modification of an object in the database has occurred!\n");
            ResponseOutputer.append("Restart the client app to avoid errors!\n");
        } catch (DatabaseManagerException e){
            ResponseOutputer.append("An error occurred while accessing the database!\n");
        }
        return false;
    }
}
