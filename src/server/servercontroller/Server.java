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

    public Server(){
    }

    /**
     * Construct a Server on localhost:8000 and a create fixed thread pool.
     */
    public void connectToClients(){
        try {
            serverSocket = new ServerSocket(8000);

            if (serverSocket == null) {
                System.err.println("Could not create server socket, exiting...");
                System.exit(-1);
            }

            // up to 10 clients
            pool = Executors.newFixedThreadPool(10);

            if (pool == null) {
                System.err.println("Could not create thread pool, exiting...");
                System.exit(-1);
            }

        } catch (IOException e) {
            System.out.println("Error creating new socket");
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
                // wait for someone to connect, make a new thread when they do.
                ServerController newConnection = new ServerController(serverSocket.accept());
                System.out.println("Someone has connected.");

                pool.execute(newConnection);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            pool.shutdown();
        }
    }

    public void closeStreams(){
        try {
            serverSocket.close();
            pool.shutdown();
        }
        catch(IOException e) {
            System.err.println("Could not close server socket...");
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server serverInstance = new Server();
        serverInstance.connectToClients();
        serverInstance.runToolShopServer();
        serverInstance.closeStreams();
    }
}