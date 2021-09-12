package serverModule.commands;


import common.data.Route;
import common.exceptions.*;
import common.utility.RouteLite;
import common.utility.User;
import serverModule.util.CollectionManager;
import serverModule.util.DatabaseCollectionManager;
import serverModule.util.ResponseOutputer;

import java.time.LocalDateTime;

/**
 *  'add_if_max' command. Add element to the collection if it more than max element of collection.
 */
public class AddIfMax extends Command{
    private final CollectionManager collectionManager;
    private final DatabaseCollectionManager databaseCollectionManager;

    public AddIfMax(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("add_if_max {element}",
                "add a new element to the collection if its value is greater than the value of the largest element in this collection");
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
            Route route =  new Route(
                    collectionManager.generateNextId(),
                    routeLite.getName(),
                    routeLite.getCoordinates(),
                    LocalDateTime.now(),
                    routeLite.getLocationFrom(),
                    routeLite.getLocationTo(),
                    routeLite.getDistance(),
                    user
            );
            if (route.compareTo(collectionManager.getMaxByDistance()) < 1) throw new IsLesserException();
            collectionManager.addToCollection(databaseCollectionManager.insertRoute(routeLite, user));
            ResponseOutputer.append("Route added successfully.\n");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (IsLesserException exception){
            ResponseOutputer.append("The element that you want to add is less than the maximum.\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        } catch (CollectionIsEmptyException e){
            ResponseOutputer.append("Collection is empty!\n");
        } catch (DatabaseManagerException e){
            ResponseOutputer.append("An error occurred while accessing the database!\n");
        }
        return false;
    }
}
