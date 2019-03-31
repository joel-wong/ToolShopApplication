package server.servercontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerController implements Runnable {

    /**
     * Socket for the player to read and write to to communicate with the server
	 */
    private Socket clientSocket;
    /**
     * Stream for the player to write to the socket
     */
    private PrintWriter out;
    /**
     * Stream to read from the socket
     */
    private BufferedReader in;

    /**
     * Constructs a Player object with the specified socket and mark. Creates the streams for
     * the Player to read and write from.
     * @param s is the socket
     * @param mark is the mark that the Player object will play with
     */
    public ServerController(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     *
     * @return true is the Thread has been able to connect to the client
     */
    public boolean connectToClient(){
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

    public void serviceClient(){
        try {
            while (true) {
                System.out.println(in.readLine());




                // Placeholder - to be passed to shop application
                out.println("Hello, World!\0");



            }
        }
        catch (IOException e) {
            System.out.println("Returned error: " + e.getMessage());
        }
    }

    public void closeStreams(){
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