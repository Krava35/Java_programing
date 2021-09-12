package clientModule;

import clientModule.util.Console;
import common.exceptions.IncorrectInputInScriptException;
import common.utility.Request;
import common.utility.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class Client {
    private final static String path = System.getenv().get("LAB5");
    private String host;
    private int port;
    private Console console;
    private DatagramSocket datagramSocket;
    private InetAddress address;
    private ByteBuffer byteBufferIn = ByteBuffer.allocate(16384);
    private ByteBuffer byteBufferOut = ByteBuffer.allocate(16384);

    public Client(String host, int port, Console console) {
        this.host = host;
        this.port = port;
        this.console = console;
    }

    private void makeByteBufferToRequest(Request request) throws IOException {
        byteBufferOut.put(serialize(request));
        byteBufferOut.flip();
    }

    private byte[] serialize(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return buffer;
    }

    private Response deserialize() throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBufferIn.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Response response = (Response) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        byteBufferIn.clear();
        return response;
    }

    private void read() throws IOException, ClassNotFoundException{
        try {
            Response serverResponse = null;
            DatagramPacket packetIn = new DatagramPacket(byteBufferIn.array(), byteBufferIn.array().length);
            datagramSocket.setSoTimeout(3000);
            datagramSocket.receive(packetIn);
            byteBufferIn.put(packetIn.getData());
            serverResponse = deserialize();
            System.out.print(serverResponse.getResponseBody());
        } catch (SocketTimeoutException exception){
            System.out.println("Server is unavailable. Try later.");
        }
    }

    private void write(Request requestToServer) throws IOException {
        makeByteBufferToRequest(requestToServer);
        DatagramPacket packetOut = new DatagramPacket(byteBufferOut.array(), byteBufferOut.array().length, address, port);
        datagramSocket.send(packetOut);
        byteBufferOut.clear();
    }

    public void run(){
        try {
            datagramSocket= new DatagramSocket();
            address = InetAddress.getByName("localhost");
            makeByteBufferToRequest(new Request("loadCollection", path));
            datagramSocket.send(new DatagramPacket(byteBufferOut.array(), byteBufferOut.array().length, address, port));
            byteBufferOut.clear();
            DatagramPacket datagramPacket = new DatagramPacket(byteBufferIn.array(), byteBufferIn.array().length);
            datagramSocket.receive(datagramPacket);
            byteBufferIn.put(datagramPacket.getData());
            Response responseLoading = deserialize();
            System.out.println(responseLoading.getResponseBody());
            Request requestToServer = null;
            Response serverResponse = null;
            do {
                requestToServer = serverResponse != null ? console.interactiveMode(serverResponse.getResponseCode()) :
                        console.interactiveMode(null);
                if (requestToServer.isEmpty()) continue;
                write(requestToServer);
                read();
            } while(!requestToServer.getCommandName().equals("exit"));
        } catch (IOException | ClassNotFoundException | IncorrectInputInScriptException exception) {
            System.out.println("An error occurred while working with the server!");
            System.exit(0);
        }
    }
}
