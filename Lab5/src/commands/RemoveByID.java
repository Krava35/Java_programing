package commands;

import data.Route;
import exceptions.CollectionIsEmptyException;
import exceptions.RouteNotFoundException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;

/**
 * 'remove_by_id' command. Removes the element by its ID.
 */
public class RemoveByID extends Command {
    private CollectionManager collectionManager;

    public RemoveByID(CollectionManager collectionManager) {
        super("remove_by_id id", "remove an item from the collection by its ID");
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
            Integer id = Integer.parseInt(argument);
            Route route = collectionManager.getById(id);
            if (route == null) throw new RouteNotFoundException();
            collectionManager.removeFromCollection(route);
            Console.println("Routes successfully deleted!!");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("The collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printError("ID must be a number!");
        } catch (RouteNotFoundException exception) {
            Console.printError("There is no route with this ID in the collection!");
        }
        return false;
    }
}
