package serverModule.util;

import common.utility.Request;
import common.utility.Response;
import common.utility.ResponseCode;

public class RequestManager {
    private CommandManager commandManager;

    public RequestManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response manage(Request request) {
        try {
            ResponseCode responseSignal = executeCommand(request.getCommandName(), request.getArgument(), request.getObjectArgument());
            return new Response(responseSignal, ResponseOutputer.getAndClear());
        } catch (NullPointerException e) {
            return null;
        }
    }

    private ResponseCode executeCommand(String command, String argument, Object objectArgument) {
        switch (command) {
            case "":
                break;
            case "loadCollection":
                if (!commandManager.loadCollection(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "add_if_max":
                if (!commandManager.addIfMax(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "help":
                if (!commandManager.help(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "info":
                if (!commandManager.info(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "show":
                if (!commandManager.show(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "add":
                if (!commandManager.add(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "update":
                if (!commandManager.update(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "clear":
                if (!commandManager.clear(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "save":
                if (!commandManager.save(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "execute_script":
                if (!commandManager.executeScript(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "remove_lower":
                if (!commandManager.removeLower(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "sort":
                if (!commandManager.sort(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "remove_all_by_distance":
                if (!commandManager.removeAllByDistance(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "group_counting_by_distance":
                if (!commandManager.groupCountingByDistance(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "filter_less_than_distance":
                if (!commandManager.filterLessThanDistance(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            case "exit":
                if (!commandManager.exit(argument, objectArgument)) return ResponseCode.ERROR;
                break;
            default:
                ResponseOutputer.append("Command '" + command + "' not found. Type 'help' for help.\n");
                return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }
}
