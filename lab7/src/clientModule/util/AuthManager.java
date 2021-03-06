package clientModule.util;

import common.utility.Request;
import common.utility.User;

import java.util.Scanner;

public class AuthManager {
    private final String loginCommand = "sign_in";
    private final String registerCommand = "sign_up";
    private Scanner scanner;

    public AuthManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public Request handle() {
        AuthAsker authAsker = new AuthAsker(scanner);
        String command = authAsker.askQuestion("Already have an account?") ? loginCommand : registerCommand;
        User user = new User(authAsker.askLogin(), authAsker.askPassword());
        return new Request(command, "", user);
    }
}
