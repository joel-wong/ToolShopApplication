package server.servercontroller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import server.servermodel.*;

public class Server {

    /**
     * ServerSocket object for the Server
     */
    private ServerSocket serverSocket;
    /**
     * Thread Pool to Handle Communication.
     */
    private ExecutorService pool;

    /**
     * The instantiation of the shopApplication on the server. Loaded from the database on setup.
     */
    private ShopApplication shopApplicationInstance;

    /**
     * The instantiation of the shopManager on the server, used to decode message and call the appropriate ShopApplication functions
     */
    private ShopManager shopManagerInstance;

    /**
     * Construct a Server on localhost:8000 and a create fixed thread pool.
     */
    private void connectToClients(){
        try {
            serverSocket = new ServerSocket(51151);

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
    private void runToolShopServer() {
        try {

            while (true) {
                // wait for someone to connect, make a new thread when they do.
                ServerController newConnection = new ServerController(serverSocket.accept(), shopManagerInstance);
                System.out.println("Someone has connected.");

                pool.execute(newConnection);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            pool.shutdown();
        }
    }

    private void closeStreams(){
        try {
            serverSocket.close();
            pool.shutdown();
        }
        catch(IOException e) {
            System.err.println("Could not close server socket...");
            System.err.println(e.getMessage());
        }
    }

    private void setupShop(){
        shopApplicationInstance = new ShopApplication();
    }

    private void setupShopManager(){
        shopManagerInstance = new ShopManager(shopApplicationInstance);
    }

    private void setupAndRun() {
        // create server to connect to clients
        setupShop();
        setupShopManager();
        connectToClients();
        runToolShopServer();
        closeStreams();
    }

    public static void main(String[] args) {
        Server serverInstance = new Server();
        serverInstance.setupAndRun();
    }
}