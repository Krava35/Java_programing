package serverModule.commands;

import common.data.Route;
import common.exceptions.CollectionIsEmptyException;
import common.exceptions.RouteNotFoundException;
import common.exceptions.WrongAmountOfArgumentsException;
import serverModule.util.CollectionManager;
import serverModule.util.FileManager;
import serverModule.util.ResponseOutputer;

import java.io.File;

/**
 * 'remove_by_id' command. Removes the element by its ID.
 */
public class RemoveByID extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public RemoveByID(CollectionManager collectionManager, FileManager fileManager) {
        super("remove_by_id id", "remove an item from the collection by its ID");
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
            if (argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Integer id = Integer.parseInt(argument);
            Route route = collectionManager.getById(id);
            if (route == null) throw new RouteNotFoundException();
            collectionManager.removeFromCollection(route);
            ResponseOutputer.append("Routes successfully deleted!\n");
            fileManager.writeCollection(collectionManager.getCollection());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.append("The collection is empty!\n");
        } catch (NumberFormatException exception) {
            ResponseOutputer.append("ID must be a number!\n");
        } catch (RouteNotFoundException exception) {
            ResponseOutputer.append("There is no route with this ID in the collection!\n");
        }
        return false;
    }
}
