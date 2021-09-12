package serverModule.commands;

import common.exceptions.WrongAmountOfArgumentsException;
import serverModule.util.CollectionManager;
import serverModule.util.ResponseOutputer;

/**
 * 'group_counting_by_distance' command. Output group collection by distance.
 */
public class GroupCountingByDistance extends Command {
    private CollectionManager collectionManager;

    public GroupCountingByDistance(CollectionManager collectionManager) {
        super("group_counting_by_distance", "group the elements of the collection by the value of the distance field, output the number of elements in each group");
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
            ResponseOutputer.append("Collection elements grouped by distance field value: ");
            ResponseOutputer.append(collectionManager.groupCountingByDistance());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        return false;
        }
    }
}
