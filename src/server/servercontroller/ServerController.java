package server.servercontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import server.servermodel.ShopManager;

/** Interfaces with a client through a socket.
 * Receives an existing connection from the Server class and communicates with the client on that socket.
 *
 *  @author Joel Wong
 *  @version 1.0
 *  @since April 4, 2019
 */
public class ServerController implements Runnable {

    /**
     * Socket for the client to read and write to to communicate with the ShopManager
	 */
    private Socket clientSocket;
    /**
     * Stream for the server to write to the socket
     */
    private PrintWriter out;
    /**
     * Stream to read from the socket
     */
    private BufferedReader in;
    /**
     * Used to retrieve data from the model
     */
    private ShopManager shopManagerInstance;

    /**
     * Constructs a Server object with the specified socket.
     * Receives an existing connection from the Server class and communicates with the client on that socket.
     * Creates the streams for this object to read and write from.
     * @param clientSocket is the socket
     * @param shopManagerInstance Used to interface with the shop
     */
    ServerController(Socket clientSocket, ShopManager shopManagerInstance) {
        this.clientSocket = clientSocket;
        this.shopManagerInstance = shopManagerInstance;
    }

    /** Sets up I/O streams to communicate with the client.
     *
     * @return true if the Thread has been able to connect to the client
     */
    private boolean connectToClient(){
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter((clientSocket.getOutputStream()), true);
        }
        catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** Receives user input, parses input into one string, and makes a call to the ShopManager to parse the input,
     * then outputs the ShopManager output
     */
    private void serviceClient(){
        String input = "";
        String response = "";
        String currentLine = "";
        try {
            while (true) {
                input = "";
                while(true) {
                    currentLine = in.readLine();
                    if(currentLine == null) {
                        return;
                    }
                    if(currentLine.contains("\0")) {
                        input += currentLine.replaceAll("\0", "") + "\n";
                        break;
                    }
                    input += currentLine + "\n";
                }
                response = shopManagerInstance.serviceRequest(input);
                out.println(response + "\0");
            }
        }
        catch (SocketException e) {
            System.out.println("A client has disconnected.");
            return;
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
    }

    /**
     * Closes the input and output streams used to communicate with the client.
     */
    private void closeStreams(){
        try {
            clientSocket.close();
            out.close();
            in.close();
            System.out.println("Successfully disconnected.");
        }
        catch(IOException e) {
            System.out.println("Could not close input and output streams");
        }
    }

    /**
     * Connects to the client, then continually services its requests
     */
    public void run() {
        System.err.println("Connecting to a client...");
        if(connectToClient()){
            // if able to connect
            System.err.println("Connected!");
            serviceClient();
            closeStreams();
        }
        else {
            System.err.println("Could not connect to client.");
            return;
        }
    }

}