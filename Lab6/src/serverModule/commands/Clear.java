package serverModule.commands;


import common.exceptions.WrongAmountOfArgumentsException;
import serverModule.util.CollectionManager;
import serverModule.util.FileManager;
import serverModule.util.ResponseOutputer;

/**
 *  'clear' command. Clears the collection.
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public Clear(CollectionManager collectionManager, FileManager fileManager) {
        super("clear", "clear collection");
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
            collectionManager.clearCollection();
            ResponseOutputer.append("The collection has been cleared!\n");
            fileManager.writeCollection(collectionManager.getCollection());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        }
        return false;
    }
}
