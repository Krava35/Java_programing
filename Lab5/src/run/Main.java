package run;

import commands.*;
import util.*;

import java.util.Scanner;

/**
 * Main application class. Creates all instances and runs the program.
 */
public class Main {
    public static final String INPUT_COMMAND = "$ ";
    public static final String INPUT_INFO = "> ";

    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "LAB5";

            Interactions interaction = new Interactions(userScanner);
            FileManager fileManager = new FileManager(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new Help(),
                    new Info(collectionManager),
                    new Show(collectionManager),
                    new Add(collectionManager, interaction),
                    new Update(collectionManager, interaction),
                    new RemoveByID(collectionManager),
                    new Clear(collectionManager),
                    new Save(collectionManager),
                    new ExecuteScript(),
                    new Exit(),
                    new AddIfMax(collectionManager, interaction),
                    new RemoveLower(collectionManager, interaction),
                    new Sort(collectionManager),
                    new RemoveAllByDistance(collectionManager),
                    new GroupCountingByDistance(collectionManager, interaction),
                    new FilterLessThanDistance(collectionManager, interaction));
            Console console = new Console(commandManager, userScanner, interaction);
            console.interactiveMode();
        }
    }
}
