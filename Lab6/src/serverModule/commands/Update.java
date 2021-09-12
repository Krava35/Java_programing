package serverModule.commands;

import common.data.Coordinates;
import common.data.LocationFrom;
import common.data.LocationTo;
import common.data.Route;
import common.exceptions.CollectionIsEmptyException;
import common.exceptions.RouteNotFoundException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.RouteLite;
import serverModule.util.CollectionManager;
import serverModule.util.FileManager;
import serverModule.util.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * 'update' command. Updates the information about selected Route.
 */
public class Update extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public Update(CollectionManager collectionManager, FileManager fileManager) {
        super("update id {element}", "update the value of the collection element by ID");
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument) {
        try {
            if (argument.isEmpty() || objectArgument == null) throw new WrongAmountOfArgumentsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Integer id = Integer.parseInt(argument);
            Route route = collectionManager.getById(id);

            if (route == null) throw new RouteNotFoundException();

            RouteLite routeLite = (RouteLite) objectArgument;

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
                    distance
            ));
            ResponseOutputer.append("Route changed successfully!\n");
            fileManager.writeCollection(collectionManager.getCollection());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.append("The collection is empty!\n");
        } catch (NumberFormatException exception) {
            ResponseOutputer.append("ID must be represented by a number!\n");
        } catch (RouteNotFoundException exception) {
            ResponseOutputer.append("There is no route with this ID in the collection!\n");
        }
        return false;
    }
}
