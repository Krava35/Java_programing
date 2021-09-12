package serverModule.commands;

import common.exceptions.WrongAmountOfArgumentsException;
import serverModule.util.CollectionManager;
import serverModule.util.ResponseOutputer;

/**
 * 'save' command. Saves the collection to the file.
 */
public class Save extends Command {
    private final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        super("save", "save collection to file");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument) {
        try {
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            collectionManager.saveCollection();
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        }
        return false;
    }
}
