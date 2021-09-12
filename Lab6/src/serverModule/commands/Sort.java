package serverModule.commands;

import common.exceptions.WrongAmountOfArgumentsException;
import serverModule.util.CollectionManager;
import serverModule.util.FileManager;
import serverModule.util.ResponseOutputer;

/**
 * 'sort' command. Sort collection by distance.
 */
public class Sort extends Command {
    private CollectionManager collectionManager;
    private FileManager fileManager;

    public Sort(CollectionManager collectionManager, FileManager fileManager) {
        super("sort", "sort the collection in natural order");
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
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            collectionManager.sortedByDistance();
            fileManager.writeCollection(collectionManager.getCollection());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        }
        return false;
    }
}
