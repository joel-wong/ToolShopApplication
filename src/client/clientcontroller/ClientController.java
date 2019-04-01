package client.clientcontroller;

public class ClientController {
	private Client client;
	
	public ClientController(Client client) {
		this.client = client;
	}
	
	public String listTools() {
		String stringForRequest = "1";
		return request(stringForRequest);
	}
	
	public String removeTools(int toolID, int amountRemoved) {
		String stringForRequest = "6\n" + toolID + "\n" + amountRemoved;
		return request(stringForRequest);
		
	}
	
	public String searchInventory(String toolName) {
		String stringForRequest = "3\n" + toolName;
		return request(stringForRequest);
	}
	
	public String searchInventory(int toolID) {
		String stringForRequest = "4\n" + toolID;
		return request(stringForRequest);
	}
	
	public String checkToolQuantity(int toolID) {
		String stringForRequest = "5\n" + toolID;
		return request(stringForRequest);
	}
	
	
	public String request(String s) {
		return client.request(s);
	}
	
}
