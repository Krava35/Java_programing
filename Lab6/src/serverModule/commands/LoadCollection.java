package serverModule.commands;

import common.exceptions.WrongAmountOfArgumentsException;
import serverModule.util.CollectionManager;
import serverModule.util.ResponseOutputer;

public class LoadCollection extends Command{
    private final CollectionManager collectionManager;

    public LoadCollection(CollectionManager collectionManager) {
        super("loadCollection", "loads the collection (this command is not available to the user)");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument, Object objectArgument) {
        try {
            if (argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            collectionManager.loadCollection();
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("The file name must be passed!\n");
        }
        return false;
    }
}
