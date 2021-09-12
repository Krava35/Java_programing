package serverModule;


import serverModule.commands.*;
import serverModule.util.*;

public class MainServer {
    public static final int PORT = 20004;

    public static void main(String[] args){
        DatabaseManager databaseManager = new DatabaseManager();
        DatabaseUserManager databaseUserManager = new DatabaseUserManager(databaseManager);
        DatabaseCollectionManager databaseCollectionManager = new DatabaseCollectionManager(databaseManager, databaseUserManager);
        CollectionManager collectionManager = new CollectionManager(databaseCollectionManager);
        CommandManager commandManager = new CommandManager(
                new Help(),
                new Info(collectionManager),
                new Show(collectionManager),
                new Add(collectionManager, databaseCollectionManager),
                new Update(collectionManager, databaseCollectionManager),
                new RemoveByID(collectionManager, databaseCollectionManager),
                new Clear(collectionManager, databaseCollectionManager),
                new ExecuteScript(),
                new Exit(databaseUserManager),
                new AddIfMax(collectionManager, databaseCollectionManager),
                new RemoveLower(collectionManager, databaseCollectionManager),
                new Sort(collectionManager),
                new RemoveAllByDistance(collectionManager, databaseCollectionManager),
                new GroupCountingByDistance(collectionManager),
                new FilterLessThanDistance(collectionManager),
                new SignIn(databaseUserManager),
                new SignUp(databaseUserManager),
                new SignOut(databaseUserManager)
                );
        RequestManager requestManager = new RequestManager(commandManager);
        Server server = new Server(PORT, requestManager);
        server.run();
        databaseManager.closeConnection();
    }
}
