package commands;

import data.Route;
import exceptions.IncorrectInputInScriptException;
import exceptions.IsLesserException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactions;
import java.time.LocalDateTime;

/**
 *  'add_if_max' command. Add element to the collection if it more than max element of collection.
 */
public class AddIfMax extends Command{
    private final CollectionManager collectionManager;
    private final Interactions interaction;

    public AddIfMax(CollectionManager collectionManager, Interactions interaction) {
        super("add_if_max {element}",
                "add a new element to the collection if its value is greater than the value of the largest element in this collection");
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
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            Route route =  new Route(
                            collectionManager.generateNextId(),
                            interaction.askName("Input route name"),
                            interaction.askCoordinates(),
                            LocalDateTime.now(),
                            interaction.askLocationFrom(),
                            interaction.askLocationTo(),
                            interaction.askDistance()
            );
            if (route.compareTo(collectionManager.getMaxByDistance()) < 1) throw new IsLesserException();
            collectionManager.addToCollection(route);
            Console.println("Route added successfully!");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {
            Console.printError("Failed to execute script.");
        } catch (IsLesserException exception){
            Console.printError("The element that you want to add is less than the maximum.");
        }
        return false;
    }
}
