package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class Server {
    private static Map<Integer, ClientHandler> clientHandlers;
    private static boolean isListening;
    private static boolean isAutioning;
    private static ExecutorService executors = Executors.newFixedThreadPool(10);//currently a dead instance
    private static ServerSocket serverSocket;
    private static Server server;

    Server() {
        isAutioning=false;
        isListening=false;

        try {
            serverSocket = new ServerSocket(5000);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        clientHandlers = new HashMap<Integer,ClientHandler>();
    }

    public static Server getInstance() {
        if (server == null) {
            server = new server();
        }
        return server;
    }
    public static Server getInstance(int port) {
        if (server == null) {
            server = new server(port);
        }
        return server;
    }

    public void listen() throws IOException{
        isListening = true;
        isAutioning = true;

        while(isListening) {
            Socket clientSocket = serverSocket.accept();
            executors.execute(new ClientHandler(clientSocket));
        }
    }
}