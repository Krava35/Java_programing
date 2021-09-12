package serverModule.commands;


import common.exceptions.DatabaseManagerException;
import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfArgumentsException;
import common.utility.RouteLite;
import common.utility.User;
import serverModule.util.CollectionManager;
import serverModule.util.DatabaseCollectionManager;
import serverModule.util.ResponseOutputer;

/**
 * 'add' command. Adds a new element to the collection.
 */
public class Add extends Command{
    private final CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public Add(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("add {element}", "add new element to collection");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (user == null) throw new NonAuthorizedUserException();
            if (!argument.isEmpty() || objectArgument == null) throw new WrongAmountOfArgumentsException();
            RouteLite routeLite = (RouteLite) objectArgument;
            collectionManager.addToCollection(databaseCollectionManager.insertRoute(routeLite, user));
            ResponseOutputer.append("Route added successfully.\n");
            return true;
        } catch (WrongAmountOfArgumentsException exception){
            ResponseOutputer.append("Using: '" + getName() + "'\n");
        } catch (NonAuthorizedUserException e){
            ResponseOutputer.append("You must log in!\n");
        } catch (DatabaseManagerException e){
            ResponseOutputer.append("An error occurred while accessing the database!\n");
        }
        return false;
    }
}
