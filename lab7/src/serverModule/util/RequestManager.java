package serverModule.util;

import common.utility.Request;
import common.utility.Response;
import common.utility.ResponseCode;
import common.utility.User;

public class RequestManager {
    private CommandManager commandManager;

    public RequestManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response manage(Request request) {
        User hashUser;
        if (request.getUser() == null){
            hashUser = null;
        } else {
            hashUser = new User(request.getUser().getLogin(), DataHasher.hash(request.getUser().getPassword() + "!Hq78p@T"));
        }
            ResponseCode responseSignal = executeCommand(request.getCommandName(), request.getArgument(), request.getObjectArgument(), hashUser);
            return new Response(responseSignal, ResponseOutputer.getAndClear());
    }

    private synchronized ResponseCode executeCommand(String command, String argument, Object objectArgument, User user) {
        switch (command) {
            case "":
                break;
            case "help":
                if (!commandManager.help(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "info":
                if (!commandManager.info(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "show":
                if (!commandManager.show(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "add":
                if (!commandManager.add(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "update":
                if (!commandManager.update(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "clear":
                if (!commandManager.clear(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "execute_script":
                if (!commandManager.executeScript(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "exit":
                if (!commandManager.exit(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "add_if_max":
                if (!commandManager.addIfMax(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "remove_lower":
                if (!commandManager.removeLower(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "sort":
                if (!commandManager.sort(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "remove_all_by_distance":
                if (!commandManager.removeAllByDistance(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "group_counting_by_distance":
                if (!commandManager.groupCountingByDistance(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "filter_less_than_distance":
                if (!commandManager.filterLessThanDistance(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "sign_in":
                if (!commandManager.sign_in(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "sign_up":
                if (!commandManager.sign_up(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;
            case "sign_out":
                if (!commandManager.sign_out(argument, objectArgument, user)) return ResponseCode.ERROR;
                break;

            default:
                ResponseOutputer.append("Command '" + command + "' not found. Type 'help' for help.\n");
                return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }
}
