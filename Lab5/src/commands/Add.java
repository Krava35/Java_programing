package commands;

import data.Route;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactions;
import java.time.LocalDateTime;

/**
 * 'add' command. Adds a new element to the collection.
 */
public class Add extends Command{
    private final CollectionManager collectionManager;
    private final Interactions interaction;

    public Add(CollectionManager collectionManager, Interactions interaction) {
        super("add {element}", "add new element to collection");
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
            collectionManager.addToCollection(
                    new Route(
                            collectionManager.generateNextId(),
                            interaction.askName("Input route name"),
                            interaction.askCoordinates(),
                            LocalDateTime.now(),
                            interaction.askLocationFrom(),
                            interaction.askLocationTo(),
                            interaction.askDistance()
                    )
            );
            Console.println("Route added successfully");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {
            Console.printError("Failed to execute script.");
        }
        return false;
    }
}
