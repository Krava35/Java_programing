package serverModule.commands;


import common.data.Route;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.RouteLite;
import serverModule.util.CollectionManager;
import serverModule.util.FileManager;
import serverModule.util.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * 'add' command. Adds a new element to the collection.
 */
public class Add extends Command{
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public Add(CollectionManager collectionManager, FileManager fileManager) {
        super("add {element}", "add new element to collection");
        this.collectionManager = collectionManager;
        this.fileManager =fileManager;
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
            collectionManager.addToCollection(
                    new Route(
                            collectionManager.generateNextId(),
                            routeLite.getName(),
                            routeLite.getCoordinates(),
                            LocalDateTime.now(),
                            routeLite.getLocationFrom(),
                            routeLite.getLocationTo(),
                            routeLite.getDistance()
                    )
            );
            ResponseOutputer.append("Route added successfully.\n");
            fileManager.writeCollection(collectionManager.getCollection());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        }
        return false;
    }
}
