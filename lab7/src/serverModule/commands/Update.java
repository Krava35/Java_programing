package serverModule.commands;

import common.data.Coordinates;
import common.data.LocationFrom;
import common.data.LocationTo;
import common.data.Route;
import common.exceptions.*;
import common.utility.RouteLite;
import common.utility.User;
import serverModule.util.CollectionManager;
import serverModule.util.DatabaseCollectionManager;
import serverModule.util.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * 'update' command. Updates the information about selected Route.
 */
public class Update extends Command {
    private final CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public Update(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("update id {element}", "update the value of the collection element by ID");
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
            if (argument.isEmpty() || objectArgument == null) throw new WrongAmountOfArgumentsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Integer id = Integer.parseInt(argument);
            Route route = collectionManager.getById(id);
            if (route == null) throw new RouteNotFoundException();
            if (!route.getOwner().equals(user)) throw new PermissionDeniedException();
            if (!databaseCollectionManager.checkRouteByIdAndUserId(route.getId(), user)) throw new IllegalDatabaseEditException();
            RouteLite routeLite = (RouteLite) objectArgument;

            databaseCollectionManager.updateRouteByID(id, routeLite);

            String name = routeLite.getName() == null ? route.getName() : routeLite.getName();
            Coordinates coordinates = routeLite.getCoordinates() == null ? route.getCoordinates() : routeLite.getCoordinates();
            LocalDateTime creationDate = route.getCreationDate();
            LocationFrom locationFrom = routeLite.getLocationFrom() == null ? route.getLocationFrom() : routeLite.getLocationFrom();
            LocationTo locationTo = routeLite.getLocationTo() == null ? route.getLocationTo() : routeLite.getLocationTo();
            Long distance = routeLite.getDistance() == -1 ? route.getDistance() : routeLite.getDistance();

            collectionManager.removeFromCollection(route);

            collectionManager.addToCollection(new Route(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    locationFrom,
                    locationTo,
                    distance,
                    user
            ));
            ResponseOutputer.append("Route changed successfully!\n");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.append("The collection is empty!\n");
        } catch (NumberFormatException exception) {
            ResponseOutputer.append("ID must be represented by a number!\n");
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
