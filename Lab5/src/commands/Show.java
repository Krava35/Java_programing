package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;

/**
 * 'show' command. Shows information about all elements of the collection.
 */
public class Show extends Command {
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "output all elements of the collection");
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
            Console.println(collectionManager);
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}
