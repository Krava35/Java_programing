package serverModule;

import common.utility.Request;
import common.utility.Response;
import serverModule.util.RequestManager;
import serverModule.util.RequestProcessingThread;
import serverModule.util.RequestReceivingThread;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.*;

public class Server {
    private int port;
    private RequestManager requestManager;
    private DatagramSocket socket;
    private InetAddress address;
    private ForkJoinPool forkJoinPool = new ForkJoinPool();
    private Request request;
    private Response response;
    private Scanner scanner;

    public Server(int port, RequestManager requestManager) {
        this.port = port;
        this.requestManager = requestManager;
    }

    public void run() {
        do_CTRL_C_Thread();
        checkInput();
        try {
            System.out.println("Server started.");
            socket = new DatagramSocket(this.port);
            while (true) {
                RequestReceivingThread readThread = new RequestReceivingThread(socket);
                readThread.start();
                try {
                    readThread.join();
                } catch (InterruptedException e) {
                    System.out.println("A multithreading error occurred while reading the request!");
                }
                request = readThread.getRequest();
                address = readThread.getAddress();
                port = readThread.getPort();

                RequestProcessingThread processingThread = new RequestProcessingThread(requestManager, request, address, port, socket);
                processingThread.start();
                try {
                    processingThread.join();
                } catch (InterruptedException e) {
                    System.out.println("A multithreading error occurred while reading the request!");
                }
                response = processingThread.getResponse();

                Runnable taskToSend = () -> {
                    try {
                        sendResponse(response);
                    } catch (IOException exception) {
                        System.out.println("An error occured while sending your reply!");
                    }
                };
                forkJoinPool.execute(ForkJoinTask.adapt(taskToSend));

            }
        } catch (SocketException e) {
            System.out.println("An error occurred while working with a socket!");
        }
    }

    private void sendResponse(Response response) throws IOException {
        byte[] sendBuffer = serialize(response);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        socket.send(sendPacket);
    }

    private byte[] serialize(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return buffer;
    }

    private void checkInput() {
        scanner = new Scanner(System.in);
        Runnable userInput = () -> {
            try {
                while (true) {
                    String[] userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    if (userCommand[0].equals("exit")) {
                        System.out.println("Server is shutting down!");
                        System.exit(0);
                    }

                }
            } catch (Exception ignored) {
            }
        };
        Thread thread = new Thread(userInput);
        thread.start();
    }

    private void do_CTRL_C_Thread() {
        scanner = new Scanner(System.in);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server is shutting down.");
        }));
    }
}
