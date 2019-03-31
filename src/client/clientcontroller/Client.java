package client.clientcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	public Client(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println("Connection error: " + e.getMessage());
		}
	}
	
	
	public String request(String s) {
		
		String read = "";
		String response = "";
		try {
			
			socketOut.println(s);
			socketOut.flush();
				
			while(true) {
				read = socketIn.readLine();
				if(read.contains("\0")) {
					response += read.replaceAll("\0", "");
					System.out.println(read);
					break;
				}
				response += read;
				System.out.println(read);
			}
			
			
		}
		catch (IOException e) {
			System.err.println("Sending error: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.err.println("An error has occurred: " + e.getMessage());
		}
		
		return response;
		
	}
}
