package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;

/**
 * 'sort' command. Sort collection by distance.
 */
public class Sort extends Command {
    private CollectionManager collectionManager;

    public Sort(CollectionManager collectionManager) {
        super("sort", "sort the collection in natural order");
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
            collectionManager.sortedByDistance();
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            System.out.println("Using: '" + getName() + "'");
        }
        return false;
    }
}
