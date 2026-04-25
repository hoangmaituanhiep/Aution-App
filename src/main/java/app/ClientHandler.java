package app;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Client client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private boolean isRunning;

    public ClientHandler(Client client) {
        this.client = client;
        isRunning = true;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public boolean status() {
        return isRunning;
    }

    @Override
    public void run() {
        isRunning = true;
        Server server = Server.getInstance();

        try {
            objectInputStream = new ObjectInputStream(client.getSocket().getInputStream());
            objectOutputStream = new ObjectOutputStream(client.getSocket().getOutputStream());
        }
        catch (IOException e) {
            isRunning = false;
            e.printStackTrace();
        }

        while (isRunning) {
            
        }
    }
}