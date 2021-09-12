package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactions;

/**
 * 'filter_less_than_distance' command. Output a collection elements that less than a distance argument.
 */
public class FilterLessThanDistance extends Command {
    private CollectionManager collectionManager;
    private Interactions interaction;

    public FilterLessThanDistance(CollectionManager collectionManager, Interactions interaction) {
        super("filter_less_than_distance distance", "output elements whose distance field value is less than the specified one");
        this.collectionManager = collectionManager;
        this.interaction = interaction;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            Long distance = Long.parseLong(argument);
            Console.println("Whose distance field value is less than the specified one: ");
            collectionManager.getLessThanDistance(distance).listIterator().forEachRemaining(route -> Console.println("     " + route));
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (NumberFormatException exception) {
            Console.printError("Distance must be a number!");
        }
        return false;
    }
}
