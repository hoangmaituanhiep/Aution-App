import java.io.*;
import java.net.*;
import java.util.concurrent.*;

import com.sun.net.httpserver.*;

public class server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        server.createContext("/", new clientHandler());

        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();

        System.out.println("Multi-thread server is running at: http://localhost:5000");
    }
}