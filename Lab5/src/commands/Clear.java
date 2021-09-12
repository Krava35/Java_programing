package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;

/**
 *  'clear' command. Clears the collection.
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            collectionManager.clearCollection();
            Console.println("The collection has been cleared!");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}
