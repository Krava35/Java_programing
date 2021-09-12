package clientModule.util;

import common.exceptions.NotDeclaredPasswordException;
import common.exceptions.NotDeclaredValueException;

import java.io.Console;
import java.util.Scanner;

public class AuthAsker {
    private Scanner scanner;
    String pattern = "(?=\\S+$).{3,}";

    public AuthAsker(Scanner scanner) {
        this.scanner = scanner;
    }

    public String askLogin() {
        String login;
        while (true) {
            try {
                System.out.println("Enter login:");
                System.out.print("> ");
                login = scanner.nextLine().trim();
                if (login.equals("")) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException e) {
                System.out.println("Login cannot be empty!");
            }
        }
        return login;
    }

    public String askPassword() {
        String password;
        while (true) {
            try {
                System.out.println("Enter password:");
                System.out.print("> ");
                Console console = System.console();
                password = String.valueOf(console.readPassword());
                if (password.equals("")) throw new NotDeclaredValueException();
                if (!password.matches(pattern)) throw new NotDeclaredPasswordException();
                break;
            } catch (NotDeclaredValueException e) {
                System.out.println("Password cannot be empty!");
            } catch (NotDeclaredPasswordException e) {
                System.out.println("The password cannot contain spaces and must have at least 3 characters!");
            }
        }
        return password;
    }

    public boolean askQuestion(String question) {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                System.out.println(finalQuestion);
                System.out.print("> ");
                answer = scanner.nextLine().trim();
                if (!answer.equals("+") && !answer.equals("-")) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException e) {
                System.out.println("The answer must be represented by signs '+' or '-'!");
            }
        }
        return answer.equals("+");
    }
}
