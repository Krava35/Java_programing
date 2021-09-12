package serverModule.commands;

import common.exceptions.CollectionIsEmptyException;
import common.exceptions.RouteNotFoundException;
import common.exceptions.WrongAmountOfArgumentsException;
import serverModule.util.CollectionManager;
import serverModule.util.FileManager;
import serverModule.util.ResponseOutputer;

/**
 * 'remove_all_by_distance' command. Removes all elements by distance.
 */
public class RemoveAllByDistance extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public RemoveAllByDistance(CollectionManager collectionManager, FileManager fileManager) {
        super("remove_all_by_distance distance",
                "remove from the collection all elements whose distance field value is equivalent to the given one");
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
            Long distance = Long.parseLong(argument);
            if (collectionManager.getByDistance(distance) == null) throw new RouteNotFoundException();
            collectionManager.removeByDistance(distance);
            ResponseOutputer.append("Routes successfully deleted!\n");
            fileManager.writeCollection(collectionManager.getCollection());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.append("The collection is empty!\n");
        } catch (RouteNotFoundException exception) {
            ResponseOutputer.append("There are no routes with such a distance in the collection!\n");
        } catch (NumberFormatException exception){
            ResponseOutputer.append("Distance must be a number!\n");
        }
        return false;
    }
}
