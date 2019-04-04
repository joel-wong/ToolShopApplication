package client.clientcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import client.clientview.LoginFrame;

/**
 * Provides data fields and methods a create a client in a client-server
 * ToolShop application.
 *
 * @author Joel Wong
 * @version 1.0
 * @since March 22, 2019
 */
public class Client {
    /**
     * Stream for the client to write to the socket
     */
    private PrintWriter socketOut;
    /**
     * Socket for the client to read and write to to communicate with the server
     */
    private Socket aSocket;
    /**
     * Stream to read from the socket
     */
    private BufferedReader socketIn;

    /**
     * The host name
     */
    private String hostName;
    /**
     * The connection port number
     */
    private int portNumber;

    /**
     * Constructs a Client object with the specified host name and the port number.
     *
     * @param hostName   is the name of the host
     * @param portNumber is the port number
     */
    public Client(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    /**
     * Connects to the server using the host name and port number.
     */
    private void connectToServer() {
        try {
            aSocket = new Socket(hostName, portNumber);
            if (aSocket == null) {
                System.out.println("Could not connect to server. Exiting...");
                System.exit(-1);
            }
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter((aSocket.getOutputStream()), true);
        } catch (IOException e) {
            System.out.println("Could not connect to server. Exiting...");
            System.err.println(e.getStackTrace());
            System.exit(-1);
        }
    }

    /**
     * Receives input and writes to socket,
     * then returns socket output.
     */
    String request(String stringToSendToServer) {

        // send input to server
        socketOut.println(stringToSendToServer + "\0");
        socketOut.flush();

        // read server output
        String response = "";
        String currentLine = "";
        try {
            while(true) {
                currentLine = socketIn.readLine();
                if(currentLine.contains("\0")) {
                    response += currentLine.replaceAll("\0", "");
                    return response;
                }
                response += currentLine + "\n";

            }
        } catch (IOException e) {
            System.err.println("Sending error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error has occurred: " + e.getMessage());
        }
        response = "Error communicating with server in Client.java";
        return response;
    }

    /**
     * Main method that creates a Client runs the Tool Shop Application.
     *
     * @param args is unused
     */
    public static void main(String[] args) {
        Client clientInstance = new Client("localhost", 51151);
        ClientController clientController = new ClientController(clientInstance);
        
        LoginFrame loginView = new LoginFrame("Frame 1");
        
        LoginListener loginListener = new LoginListener(loginView,clientController);
        
        loginView.setVisible(true);

        clientInstance.connectToServer();
        System.out.println(clientController.listOrders());
        // connections will be closed automatically
    }
}