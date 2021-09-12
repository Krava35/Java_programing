package util;

import commands.Command;

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

    public CommandManager(Command help, Command info, Command show, Command add,
                          Command update, Command removeById, Command clear,
                          Command save, Command executeScript, Command exit,
                          Command addIfMax, Command removeLower, Command sort,
                          Command removeAllByDistance, Command groupCountingByDistance,
                          Command filterLessThanDistance)
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
    }

    /**
     * Returns the command's list
     * @return The command's list.
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Prints that command is not found.
     * @param command Command, which is not found.
     * @return Command exit status.
     */
    public boolean noSuchCommand(String command) {
        Console.println("Command '" + command + "' not founded. Input 'help' for help.");
        return false;
    }

    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean help(String argument) {
        if (help.execute(argument)) {
            for (Command command : commands) {
                Console.printTable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean info(String argument) {
        return info.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument) {
        return show.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean add(String argument) {
        return add.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument) {
        return update.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeById(String argument) {
        return removeById.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument) {
        return clear.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean save(String argument) {
        return save.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument) {
        return executeScript.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument) {
        return exit.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean addIfMax(String argument) {
        return addIfMax.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeLower(String argument) {
        return removeLower.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean sort(String argument) {
        return sort.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeAllByDistance(String argument){
        return removeAllByDistance.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean groupCountingByDistance(String argument){
        return groupCountingByDistance.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean filterLessThanDistance(String argument){
        return filterLessThanDistance.execute(argument);
    }
}
