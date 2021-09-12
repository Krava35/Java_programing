package serverModule.commands;


import common.data.Route;
import common.exceptions.IsLesserException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.RouteLite;
import serverModule.util.CollectionManager;
import serverModule.util.FileManager;
import serverModule.util.ResponseOutputer;

import java.time.LocalDateTime;

/**
 *  'add_if_max' command. Add element to the collection if it more than max element of collection.
 */
public class AddIfMax extends Command{
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public AddIfMax(CollectionManager collectionManager, FileManager fileManager) {
        super("add_if_max {element}",
                "add a new element to the collection if its value is greater than the value of the largest element in this collection");
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
            if (!argument.isEmpty() || objectArgument == null) throw new WrongAmountOfArgumentsException();
            RouteLite routeLite = (RouteLite) objectArgument;
            Route route =  new Route(
                    collectionManager.generateNextId(),
                    routeLite.getName(),
                    routeLite.getCoordinates(),
                    LocalDateTime.now(),
                    routeLite.getLocationFrom(),
                    routeLite.getLocationTo(),
                    routeLite.getDistance()
            );
            if (route.compareTo(collectionManager.getMaxByDistance()) < 1) throw new IsLesserException();
            collectionManager.addToCollection(route);
            ResponseOutputer.append("Route added successfully.\n");
            fileManager.writeCollection(collectionManager.getCollection());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (IsLesserException exception){
            ResponseOutputer.append("The element that you want to add is less than the maximum.\n");
        }
        return false;
    }
}
