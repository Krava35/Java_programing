package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.RouteNotFoundException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactions;

/**
 * 'remove_all_by_distance' command. Removes all elements by distance.
 */
public class RemoveAllByDistance extends Command {
    private CollectionManager collectionManager;
    private Interactions interaction;

    public RemoveAllByDistance(CollectionManager collectionManager) {
        super("remove_all_by_distance distance",
                "remove from the collection all elements whose distance field value is equivalent to the given one");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long distance = Long.parseLong(argument);
            if (collectionManager.getByDistance(distance) == null) throw new RouteNotFoundException();
            collectionManager.removeByDistance(distance);
            Console.println("Routes successfully deleted!");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("The collection is empty!");
        } catch (RouteNotFoundException exception) {
            Console.printError("There are no routes with such a distance in the collection!");
        } catch (NumberFormatException exception){
            Console.printError("Distance must be a number!");
        }
        return false;
    }
}
