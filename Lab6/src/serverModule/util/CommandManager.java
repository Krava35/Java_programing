package serverModule.util;


import serverModule.commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages commands
 */
public class CommandManager {
    private final List<Command> commands = new ArrayList<>();
    private final Command help;
    private final Command info;
    private final Command show;
    private final Command add;
    private final Command update;
    private final Command removeById;
    private final Command clear;
    private final Command save;
    private final Command executeScript;
    private final Command exit;
    private final Command addIfMax;
    private final Command removeLower;
    private final Command sort;
    private final Command removeAllByDistance;
    private final Command groupCountingByDistance;
    private final Command filterLessThanDistance;
    private final Command loadCollection;

    public CommandManager(Command help, Command info, Command show, Command add,
                          Command update, Command removeById, Command clear,
                          Command save, Command executeScript, Command exit,
                          Command addIfMax, Command removeLower, Command sort,
                          Command removeAllByDistance, Command groupCountingByDistance,
                          Command filterLessThanDistance, Command loadCollection)
    {
        this.help = help;
        this.info = info;
        this.show = show;
        this.add = add;
        this.update = update;
        this.removeById = removeById;
        this.clear = clear;
        this.save = save;
        this.executeScript = executeScript;
        this.exit = exit;
        this.addIfMax = addIfMax;
        this.removeLower = removeLower;
        this.sort = sort;
        this.removeAllByDistance = removeAllByDistance;
        this.groupCountingByDistance = groupCountingByDistance;
        this.filterLessThanDistance = filterLessThanDistance;
        this.loadCollection = loadCollection;


        commands.add(help);
        commands.add(info);
        commands.add(show);
        commands.add(add);
        commands.add(update);
        commands.add(removeById);
        commands.add(clear);
        commands.add(save);
        commands.add(executeScript);
        commands.add(exit);
        commands.add(addIfMax);
        commands.add(removeLower);
        commands.add(sort);
        commands.add(removeAllByDistance);
        commands.add(groupCountingByDistance);
        commands.add(filterLessThanDistance);
        commands.add(loadCollection);
    }

    /**
     * Returns the command's list
     * @return The command's list.
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean help(String argument, Object objectArgument) {
        if (help.execute(argument, objectArgument)) {
            for (Command command : commands) {
                ResponseOutputer.appendTable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean info(String argument, Object objectArgument) {
        return info.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument, Object objectArgument) {
        return show.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean add(String argument, Object objectArgument) {
        return add.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument, Object objectArgument) {
        return update.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeById(String argument, Object objectArgument) {
        return removeById.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument, Object objectArgument) {
        return clear.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean save(String argument, Object objectArgument) {
        return save.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument, Object objectArgument) {
        return executeScript.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument, Object objectArgument) {
        return exit.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean addIfMax(String argument, Object objectArgument) {
        return addIfMax.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeLower(String argument, Object objectArgument) {
        return removeLower.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean sort(String argument, Object objectArgument) {
        return sort.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeAllByDistance(String argument, Object objectArgument){
        return removeAllByDistance.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean groupCountingByDistance(String argument, Object objectArgument){
        return groupCountingByDistance.execute(argument, objectArgument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean filterLessThanDistance(String argument, Object objectArgument){
        return filterLessThanDistance.execute(argument, objectArgument);
    }
    public boolean loadCollection(String argument, Object objectArgument) {
        return loadCollection.execute(argument, objectArgument);
    }
}
