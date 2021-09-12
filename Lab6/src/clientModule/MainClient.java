package clientModule;


import clientModule.util.Console;
import java.util.Scanner;

public class MainClient {
    private static String host = "localhost";
    private static int port = 10355;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        Client client = new Client(host, port, console);
        client.run();
        scanner.close();
    }
}
