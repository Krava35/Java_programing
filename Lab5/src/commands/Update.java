package commands;

import data.Coordinates;
import data.LocationFrom;
import data.LocationTo;
import data.Route;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.RouteNotFoundException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactions;

import java.time.LocalDateTime;

/**
 * 'update' command. Updates the information about selected Route.
 */
public class Update extends Command {
    private final CollectionManager collectionManager;
    private final Interactions interaction;

    public Update(CollectionManager collectionManager, Interactions interaction) {
        super("update id {element}", "update the value of the collection element by ID");
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
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Integer id = Integer.parseInt(argument);
            Route route = collectionManager.getById(id);
            if (route == null) throw new RouteNotFoundException();

            String name = route.getName();
            Coordinates coordinates = route.getCoordinates();
            LocalDateTime creationDate = route.getCreationDate();
            LocationFrom locationFrom = route.getLocationFrom();
            LocationTo locationTo = route.getLocationTo();
            Long distance = route.getDistance();

            collectionManager.removeFromCollection(route);

            if (interaction.askQuestion("Do you want to change the name of the route?"))
                name = interaction.askName("Enter route name ");
            if (interaction.askQuestion("Do you want to change the coordinates of the route?"))
                coordinates = interaction.askCoordinates();
            if (interaction.askQuestion("Want to change LocationFrom of the route?"))
                locationFrom = interaction.askLocationFrom();
            if (interaction.askQuestion("Want to change LocationTo of the route?"))
                locationTo = interaction.askLocationTo();
            if (interaction.askQuestion("Want to change distance of the route?"))
                distance = interaction.askDistance();

            collectionManager.addToCollection(new Route(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    locationFrom,
                    locationTo,
                    distance
            ));
            Console.println("Route changed successfully!");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Using: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("The collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printError("ID must be represented by a number!");
        } catch (RouteNotFoundException exception) {
            Console.printError("There is no route with this ID in the collection!");
        } catch (IncorrectInputInScriptException exception) {
            Console.printError("Failed to execute script.");
        }
        return false;
    }
}
