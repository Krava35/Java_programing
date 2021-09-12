package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;

import java.time.LocalDateTime;

/**
 * 'info' command. Prints information about the collection.
 */
public class Info extends Command {
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "output information about a collection");
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
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "no initialization has occurred in this session yet" :
                    lastInitTime.toString();

            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "no save has occurred in this session yet" :
                    lastSaveTime.toString();

            Console.println("Collection details:");
            Console.println(" Type: " + collectionManager.collectionType());
            Console.println(" Amount of elements: " + collectionManager.collectionSize());
            Console.println(" Last save date: " + lastSaveTimeString);
            Console.println(" Last initialization date: " + lastInitTimeString);
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        }
        return false;
    }
}