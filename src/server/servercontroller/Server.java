package server.servercontroller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    /**
     * ServerSocket object for the Server
     */
    ServerSocket serverSocket;
    /**
     * Thread Pool to Handle Communication.
     */
    private ExecutorService pool;

    /**
     * Construct a Server on localhost:8000 and a create fixed thread pool.
     */
    public Server(){
        try {
            serverSocket = new ServerSocket(8000);
            pool = Executors.newFixedThreadPool(10);

        } catch (IOException e) {
            System.out.println("Create new socket error");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        System.out.println("Server is running");
    }

    /**
     * Runs a thread for every client that connects with the server.
     */
    public void runToolShopServer() {
        try {

            while (true) {
                ServerController newConnection = new ServerController(serverSocket.accept());
                System.out.println("Someone has connected.");
                pool.execute(newConnection);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            pool.shutdown();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.runToolShopServer();
    }
}