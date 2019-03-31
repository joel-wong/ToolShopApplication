package client.clientcontroller;

public class ClientController {
	private Client client;
	
	public ClientController(Client client) {
		this.client = client;
	}
	
	public String listTools() {
		return "Listing Tools...";
		
	}
	
	public String removeTools(int toolID, int amountRemoved) {
		return "Removing Tools...";
		
	}
	
	public String searchInventory(String toolName) {
		return "Searching Inventory...";
	}
	
	public String searchInventory(int toolID) {
		return "Searching Inventory...";
	}
	
	public String checkToolQuantity(int toolID) {
		return "Checking Quantity...";
	}
	
	
	public String request(String s) {
		return client.request(s);
	}
	
}
