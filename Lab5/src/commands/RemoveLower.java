package commands;

import data.Route;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactions;

import java.time.LocalDateTime;

/**
 * 'remove_lower' command. Remove all elements lower than user entered.
 * */
public class RemoveLower extends Command {
    private final CollectionManager collectionManager;
    private final Interactions interaction;

    public RemoveLower(CollectionManager collectionManager, Interactions interaction) {
        super("remove_lower {element}",
                "remove all elements from the collection that are less than the given one");
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
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Route routeToFind = new Route(
                    -1,
                    interaction.askName("Input route name"),
                    interaction.askCoordinates(),
                    LocalDateTime.now(),
                    interaction.askLocationFrom(),
                    interaction.askLocationTo(),
                    interaction.askDistance()
            );
            int collectionSize = collectionManager.collectionSize();
            collectionManager.removeLower(routeToFind);
            Console.println("Removed routes: " + (collectionSize - collectionManager.collectionSize()));
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("The collection is empty!");
        } catch (IncorrectInputInScriptException exception) {
            Console.printError("Failed to execute script.");
        }
        return false;
    }
}
