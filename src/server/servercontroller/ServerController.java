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
    private Socket aSocket;
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
    public ServerController(Socket s) {
        this.aSocket = s;

        try {
            in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            out = new PrintWriter((aSocket.getOutputStream()), true);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Running");
        try {
            while (true) {
                System.out.println(in.readLine());
                out.println("Hello, World!\0");
            }
        }
        catch (IOException e) {
            System.out.println("Returned error: " + e.getMessage());
        }
        finally {
            try {
                aSocket.close();
                out.close();
                in.close();
                System.out.println("Successfully disconnected.");
            }
            catch(IOException e) {
                System.out.println("Could not close input and output streams");
            }
        }
    }

}