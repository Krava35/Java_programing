package serverModule.commands;

import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.User;
import serverModule.util.CollectionManager;
import serverModule.util.ResponseOutputer;

/**
 * 'filter_less_than_distance' command. Output a collection elements that less than a distance argument.
 */
public class FilterLessThanDistance extends Command {
    private CollectionManager collectionManager;

    public FilterLessThanDistance(CollectionManager collectionManager) {
        super("filter_less_than_distance distance", "output elements whose distance field value is less than the specified one");
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
            if (argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            Long distance = Long.parseLong(argument);
            ResponseOutputer.append("Whose distance field value is less than the specified one: \n");
            collectionManager.getLessThanDistance(distance).listIterator().forEachRemaining(route -> ResponseOutputer.append("     " + route));
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (NumberFormatException exception) {
            ResponseOutputer.append("Distance must be a number!\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        }
        return false;
    }
}
