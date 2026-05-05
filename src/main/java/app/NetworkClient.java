package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import app.packets.PacketMessage;

public class NetworkClient {
  private static final Logger logger = LoggerFactory.getLogger(NetworkClient.class);

  private static NetworkClient instance;
  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;

  public static NetworkClient getInstance() {
    if (instance == null) instance = new NetworkClient();
    return instance;
  }

  public void connect(String host, int port) throws IOException{
    socket = new Socket(host, port);
    out = new ObjectOutputStream(socket.getOutputStream());
    in = new ObjectInputStream(socket.getInputStream());
  }

  public void sendPacket(PacketMessage packet) throws IOException {
    if (out != null) {
      out.writeObject(packet);
      out.flush();
    }
  }

  public PacketMessage receivePacket() throws IOException, ClassNotFoundException {
    if (in != null) {
      return (PacketMessage) in.readObject();
    }
    return null;
  }

  public void close() {
    logger.debug("DEBUG: Closing client...");
    try {
      if (socket != null) socket.close();
      if (in != null) in.close();
      if (out != null) out.close();
    }
    catch (IOException e) {
      logger.error("ERROR: " + e.getMessage());
    }
  }
}
