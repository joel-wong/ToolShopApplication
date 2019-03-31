package client.clientcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
     * Stream to read standard input from the user
     */
    private BufferedReader stdIn;
    /**
     * Stream to read from the socket
     */
    private BufferedReader socketIn;

    private String hostName;

    private int portNumber;

    /**
     * Constructs a Client object with the specified host name and the port number.
     * @param hostName is the name of the host
     * @param portNumber is the port number
     */
    public Client(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    public void connectToServer(){
        try {
            aSocket = new Socket(hostName, portNumber);
            if(aSocket == null) {
                System.out.println("Could not connect to server. Exiting...");;
                System.exit(-1);
            }
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter((aSocket.getOutputStream()), true);
        } catch (IOException e) {
            System.out.println("Could not connect to server. Exiting...");;
            System.err.println(e.getStackTrace());
            System.exit(-1);
        }
    }

    /**
     * Receives input and writes to socket,
     * then returns socket output.
     */
    public String request(String stringToSendToServer) {


        // send input to server
        socketOut.println(stringToSendToServer);
        socketOut.flush();

        // read server output

        String response = "";
        try {
            response = socketIn.readLine();
            return response;
        }
        catch (IOException e) {
            System.err.println("Sending error: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("An error has occurred: " + e.getMessage());
        }
        response = "Error communicating with server in Client.java";
        return response;
    }

    // Old code


//    String response = "";
//
//        try {
//        outerloop:
//        while (true) {
//
//            // check if closed here
//            // if(something.closed()) {
//            //   break;
//            // }
//
//            // read input
//            System.out.println("Please input:");
//            line = stdIn.readLine();
//            socketOut.println(line);
//            socketOut.flush();
//
//            // get output from server
//            while(true) {
//                response = socketIn.readLine();
//                if(response.contains("\0")) {
//                    response = response.replaceAll("\0", "");
//                    System.out.println(response);
//                    break;
//                }
//                System.out.println(response);
//            }
//            if(response.equals("QUIT")) {
//                break;
//            }
//        }
//
//        System.out.println("Program terminated...");
//
//    }
//        catch (IOException e) {
//        System.out.println("Sending error: " + e.getMessage());
//    }
//        catch (Exception e)
//    {
//        System.out.println("An error has occurred: " + e.getMessage());
//    }
//
//    public void closeConnections(){
//        try {
//            stdIn.close();
//            socketOut.close();
//        }
//        catch (IOException e) {
//            System.out.println("closing error: " + e.getMessage());
//        }
//    }

    /**
     * Main method that creates a Client and begins a game with the server.
     * @param args is unused
     */
    public static void main(String[] args) {
        Client clientInstance = new Client("localhost", 8000);
        clientInstance.connectToServer();

        System.out.println(clientInstance.request("Test 1"));
        System.out.println(clientInstance.request("Test 2"));



        // connections will be closed automatically
    }
}
