package serverModule;


import common.utility.Request;
import common.utility.Response;
import serverModule.util.RequestManager;
import serverModule.util.ResponseOutputer;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Server {
    private int port;
    private RequestManager requestManager;
    private SocketAddress address;
    private InetSocketAddress inetSocketAddress;
    private DatagramSocket datagramSocket;
    private Scanner scanner;
    private ByteBuffer readBuffer = ByteBuffer.allocate(16384);
    private Response response;


    public Server(int port, RequestManager requestManager) throws IOException {
        this.port = port;
        this.requestManager = requestManager;
        inetSocketAddress = new InetSocketAddress(port);
        datagramSocket = new DatagramSocket(port);
        checkInput();
    }

    private void checkInput() {
        scanner = new Scanner(System.in);
        Runnable userInput = () -> {
            try {
                while (true) {
                    String[] userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    if (!userCommand[0].equals("save") && !userCommand[0].equals("exit")) {

                        System.out.println("The server cannot accept such a command itself!");
                        return;
                    }
                    if (userCommand[0].equals("exit")) {
                        System.out.println("The server is shutting down!");
                        System.exit(0);
                    }
                    Response response = executeRequest(new Request(userCommand[0], userCommand[1]));
                    System.out.println(response.getResponseBody());
                }
            } catch (Exception ignored) {
            }
        };
        Thread thread = new Thread(userInput);
        thread.start();
    }


    private Response executeRequest(Request request) {
        return requestManager.manage(request);
    }

    public void run() throws IOException {
        ResponseOutputer.clear();
        System.out.println("The server is running!");
        while (true) {
            read();
            write();
        }
    }


    private void read() {
        try {
            Request request;
            DatagramPacket datagramPacket = new DatagramPacket(readBuffer.array(), readBuffer.array().length);
            datagramSocket.receive(datagramPacket);
            address = datagramPacket.getSocketAddress();
            readBuffer.put(datagramPacket.getData());
            ByteArrayInputStream bais = new ByteArrayInputStream(readBuffer.array());
            ObjectInputStream ois = new ObjectInputStream(bais);
            request = (Request) ois.readObject();
            System.out.println("Command received: " + request.getCommandName());
            System.out.println("Command executed: " + request.getCommandName());
            System.out.println();
            bais.close();
            ois.close();
            this.response = executeRequest(request);
            readBuffer.clear();

        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    private void write() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, address);
        datagramSocket.send(datagramPacket);

    }
}
