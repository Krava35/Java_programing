package serverModule.util;

import common.utility.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RequestReceivingThread extends Thread {
    private Request request;
    private InetAddress address;
    private int port;
    private DatagramSocket socket;

    public RequestReceivingThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            byte[] getBuffer = new byte[socket.getReceiveBufferSize()];
            DatagramPacket getPacket = new DatagramPacket(getBuffer, getBuffer.length);
            socket.receive(getPacket);
            address = getPacket.getAddress();
            port = getPacket.getPort();
            try {
                request = deserialize(getPacket, getBuffer);
                System.out.println("Command received: '" + request.getCommandName() + "' From: '" + request.getUser().getLogin() + "'");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Request getRequest() {
        return request;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    private Request deserialize(DatagramPacket getPacket, byte[] buffer) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getPacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return request;
    }
}
