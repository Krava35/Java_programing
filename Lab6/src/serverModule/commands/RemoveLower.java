package serverModule.commands;


import common.data.Route;
import common.exceptions.CollectionIsEmptyException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.RouteLite;
import serverModule.util.CollectionManager;
import serverModule.util.FileManager;
import serverModule.util.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * 'remove_lower' command. Remove all elements lower than user entered.
 * */
public class RemoveLower extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public RemoveLower(CollectionManager collectionManager, FileManager fileManager) {
        super("remove_lower {element}",
                "remove all elements from the collection that are less than the given one");
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
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            RouteLite routeLite = (RouteLite) objectArgument;
            Route routeToFind = new Route(
                    -1,
                    routeLite.getName(),
                    routeLite.getCoordinates(),
                    LocalDateTime.now(),
                    routeLite.getLocationFrom(),
                    routeLite.getLocationTo(),
                    routeLite.getDistance()
            );
            int collectionSize = collectionManager.collectionSize();
            collectionManager.removeLower(routeToFind);
            ResponseOutputer.append("Removed routes: " + (collectionSize - collectionManager.collectionSize()));
            fileManager.writeCollection(collectionManager.getCollection());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.append("The collection is empty!\n");
        }
        return false;
    }
}
