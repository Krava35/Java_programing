package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactions;

/**
 * 'group_counting_by_distance' command. Output group collection by distance.
 */
public class GroupCountingByDistance extends Command {
    private CollectionManager collectionManager;

    public GroupCountingByDistance(CollectionManager collectionManager, Interactions interaction) {
        super("group_counting_by_distance", "group the elements of the collection by the value of the distance field, output the number of elements in each group");
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
            Console.println("Collection elements grouped by distance field value: ");
            Console.println(collectionManager.groupCountingByDistance());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        return false;
        }
    }
}
