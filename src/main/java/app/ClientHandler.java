package app;

import java.io.*;
import java.net.Socket;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import app.packets.Message;
import app.packets.PacketMessage;
import app.payload.RegisterClientPayload;

public class ClientHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

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
        Message inputMessage = null;
        PacketMessage inpuPacketMessage = null;

        try {
            objectOutputStream = new ObjectOutputStream(client.getSocket().getOutputStream());
            objectOutputStream.flush(); // Flush the output stream immediately
            objectInputStream = new ObjectInputStream(client.getSocket().getInputStream());
            sendPacket((new PacketMessage(Message.WELCOME, null)));
        } catch (IOException e) {
            isRunning = false;
            e.printStackTrace();
        }

        while (isRunning) {
            try {
                PacketMessage packetMessage = (PacketMessage) objectInputStream.readObject();

                switch (packetMessage.getType()) {
                    case JOIN_AUCTION:
                        try {
                            joinAution(packetMessage);
                        }
                        catch (Exception e) {
                            logger.error("ERROR: Unrecognized Packet");
                        }
                        break;
                    
                    case CANCLE_AUCTION:
                        try {
                            // TODO: Add cancellation logic
                        }
                        catch (Exception e) {
                            logger.error("ERROR: Unrecognized Packet");
                        }
                        break;
                    
                    case SEND_AUCTION:
                        try {
                            // TODO: Add send auction logic
                        }
                        catch (Exception e) {
                            logger.error("ERROR: Unrecognized Packet");
                        }
                        break;
                    
                    case SEND_AUCTION_ID:
                        try {
                            // TODO: Add auction ID logic
                        }
                        catch (Exception e) {
                            logger.error("ERROR: Unrecognized Packet");
                        }
                        break;
                
                    default:
                        break;
                }
            }
            catch (ClassNotFoundException | IOException e) {
                logger.error("ERROR: " + e.getMessage());
            }
        }
    }

    public void stopRunning() throws IOException {
        isRunning = false;

        client.getSocket().close();

        Server server = Server.getInstance();
        server.getClientHanlders().remove(getClient().getSocket().getInetAddress().getHostAddress());
    }

    public void sendPacket(PacketMessage message) throws IOException {
        objectOutputStream.writeObject(message);
    }

    public void joinAution(PacketMessage packetMessage) throws IOException{
        if (packetMessage.getPayload() instanceof RegisterClientPayload) {
            Server server = Server.getInstance();
            RegisterClientPayload registerClientPayload = (RegisterClientPayload) packetMessage.getPayload();

            server.joinAution(registerClientPayload.getAuctionId(), client);
        }
        else {
            logger.error("ERROR: Expected RegisterClientPayload");
        }
    }
}