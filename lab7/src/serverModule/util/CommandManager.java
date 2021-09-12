package serverModule.util;


import common.utility.User;
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
    private final Command executeScript;
    private final Command exit;
    private final Command addIfMax;
    private final Command removeLower;
    private final Command sort;
    private final Command removeAllByDistance;
    private final Command groupCountingByDistance;
    private final Command filterLessThanDistance;
    private final Command signIn;
    private final Command signUp;
    private final Command signOut;

    public CommandManager(Command help, Command info, Command show, Command add,
                          Command update, Command removeById, Command clear,
                          Command executeScript, Command exit, Command addIfMax,
                          Command removeLower, Command sort, Command removeAllByDistance,
                          Command groupCountingByDistance, Command filterLessThanDistance,
                          Command signIn, Command signUp, Command signOut)
    {
        this.help = help;
        this.info = info;
        this.show = show;
        this.add = add;
        this.update = update;
        this.removeById = removeById;
        this.clear = clear;
        this.executeScript = executeScript;
        this.exit = exit;
        this.addIfMax = addIfMax;
        this.removeLower = removeLower;
        this.sort = sort;
        this.removeAllByDistance = removeAllByDistance;
        this.groupCountingByDistance = groupCountingByDistance;
        this.filterLessThanDistance = filterLessThanDistance;
        this.signIn = signIn;
        this.signUp = signUp;
        this.signOut = signOut;


        commands.add(help);
        commands.add(info);
        commands.add(show);
        commands.add(add);
        commands.add(update);
        commands.add(removeById);
        commands.add(clear);
        commands.add(executeScript);
        commands.add(exit);
        commands.add(addIfMax);
        commands.add(removeLower);
        commands.add(sort);
        commands.add(removeAllByDistance);
        commands.add(groupCountingByDistance);
        commands.add(filterLessThanDistance);
        commands.add(signIn);
        commands.add(signUp);
        commands.add(signOut);
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
    public boolean help(String argument, Object objectArgument, User user) {
        if (help.execute(argument, objectArgument, user)) {
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
    public boolean info(String argument, Object objectArgument, User user) {
        return info.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument, Object objectArgument, User user) {
        return show.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean add(String argument, Object objectArgument, User user) {
        return add.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument, Object objectArgument, User user) {
        return update.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeById(String argument, Object objectArgument, User user) {
        return removeById.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument, Object objectArgument, User user) {
        return clear.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument, Object objectArgument, User user) {
        return executeScript.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument, Object objectArgument, User user) {
        return exit.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean addIfMax(String argument, Object objectArgument, User user) {
        return addIfMax.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeLower(String argument, Object objectArgument, User user) {
        return removeLower.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean sort(String argument, Object objectArgument, User user) {
        return sort.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeAllByDistance(String argument, Object objectArgument, User user){
        return removeAllByDistance.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean groupCountingByDistance(String argument, Object objectArgument, User user){
        return groupCountingByDistance.execute(argument, objectArgument, user);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean filterLessThanDistance(String argument, Object objectArgument, User user){
        return filterLessThanDistance.execute(argument, objectArgument, user);
    }

    public boolean sign_in(String argument, Object objectArgument, User user) {
        return signIn.execute(argument, objectArgument, user);
    }

    public boolean sign_up(String argument, Object objectArgument, User user) {
        return signUp.execute(argument, objectArgument, user);
    }

    public boolean sign_out(String argument, Object objectArgument, User user) {
        return signOut.execute(argument, objectArgument, user);
    }
}
