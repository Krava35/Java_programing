package serverModule.commands;

import java.util.Objects;

/**
 * Abstract Command class contains name and description.
 */
public abstract class Command {
    private String name;
    private String description;

    /**
     * Constructs a new Command object
     * @param name Command name
     * @param description Description of the command
     */
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param argument after command name.
     * @return execute status
     */
    public abstract boolean execute(String argument, Object objectArgument);

    /**
     * Return a command name
     * @return Command name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return a description of the command
     * @return Description of the command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Compares this object to the specified object
     * @param obj an object
     * @return true if the arguments are equal to each other and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Command command = (Command) obj;
        return Objects.equals(name, command.name) &&
                Objects.equals(description, command.description);
    }

    /**
     * Returns a hash code for this Object
     * @return a hash code for this Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    /**
     * Returns a String object representing this Coordinates value
     * @return a String object representing this Coordinates value
     */
    @Override
    public String toString() {
        return name + " (" + description + ")";
    }
}
