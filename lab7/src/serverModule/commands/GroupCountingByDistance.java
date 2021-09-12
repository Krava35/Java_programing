package serverModule.commands;

import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.User;
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
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (user == null) throw new NonAuthorizedUserException();
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            ResponseOutputer.append("Collection elements grouped by distance field value: ");
            ResponseOutputer.append(collectionManager.groupCountingByDistance());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        }
        return false;
    }
}
