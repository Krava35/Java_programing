package serverModule.commands;


import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.User;
import serverModule.util.CollectionManager;
import serverModule.util.ResponseOutputer;

import javax.jws.soap.SOAPBinding;
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
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (user == null) throw new NonAuthorizedUserException();
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfArgumentsException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "no initialization has occurred in this session yet" :
                    lastInitTime.toString();

            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "no save has occurred in this session yet" :
                    lastSaveTime.toString();

            ResponseOutputer.append("Collection details:");
            ResponseOutputer.append(" Type: " + collectionManager.collectionType());
            ResponseOutputer.append(" Amount of elements: " + collectionManager.collectionSize());
            ResponseOutputer.append(" Last save date: " + lastSaveTimeString);
            ResponseOutputer.append(" Last initialization date: " + lastInitTimeString + "\n");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        }
        return false;
    }
}