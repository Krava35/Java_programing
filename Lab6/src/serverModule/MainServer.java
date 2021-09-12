package serverModule;


import serverModule.commands.*;
import serverModule.util.CollectionManager;
import serverModule.util.CommandManager;
import serverModule.util.FileManager;
import serverModule.util.RequestManager;

import java.io.IOException;

public class MainServer {
    public static final int PORT = 10355;

    public static void main(String[] args) throws IOException {
        final String envVariable = "LAB5";
        FileManager fileManager = new FileManager(envVariable);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager(
                new Help(),
                new Info(collectionManager),
                new Show(collectionManager),
                new Add(collectionManager, fileManager),
                new Update(collectionManager, fileManager),
                new RemoveByID(collectionManager, fileManager),
                new Clear(collectionManager, fileManager),
                new Save(collectionManager),
                new ExecuteScript(),
                new Exit(),
                new AddIfMax(collectionManager, fileManager),
                new RemoveLower(collectionManager, fileManager),
                new Sort(collectionManager, fileManager),
                new RemoveAllByDistance(collectionManager, fileManager),
                new GroupCountingByDistance(collectionManager),
                new FilterLessThanDistance(collectionManager),
                new LoadCollection(collectionManager));
        RequestManager requestManager = new RequestManager(commandManager);
        Server server = new Server(PORT, requestManager);
        server.run();
        collectionManager.saveCollection();
    }
}
