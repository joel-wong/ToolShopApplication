package client.clientcontroller;

public class ClientController {
	private Client client;
	
	public ClientController(Client client) {
		this.client = client;
	}
	
	String listTools() {
		String stringForRequest = "1";
		return request(stringForRequest);
	}
	
	String removeTools(int toolID, int amountRemoved) {
		String stringForRequest = "6\n" + toolID + "\n" + amountRemoved;
		return request(stringForRequest);
	}
	
	String searchInventory(String toolName) {
		String stringForRequest = "3\n" + toolName;
		return request(stringForRequest);
	}
	
	String searchInventory(int toolID) {
		String stringForRequest = "4\n" + toolID;
		return request(stringForRequest);
	}
	
	String checkToolQuantity(int toolID) {
		String stringForRequest = "5\n" + toolID;
		return request(stringForRequest);
	}

	String listSuppliers() {
		String stringForRequest = "2";
		return request(stringForRequest);
	}
	
	String addTools(int toolID, int amountAdded) {
		String stringForRequest = "7\n" + toolID + "\n" + amountAdded;
		return request(stringForRequest);
	}

	String setNewDate(String month, int day, int year) {
		String stringForRequest = "8\n" + month + "\n" + day + "\n" + year;
		return request(stringForRequest);
	}

	String deleteTool(int toolID) {
		String stringForRequest = "9\n" + toolID;
		return request(stringForRequest);
	}

	String addNewToolToInventory(int toolID, String toolName, int quantity, double price, int supplierID) {
		String stringForRequest = "10\n" + toolID + "\n" + toolName + "\n" + quantity + "\n" + price + "\n" + supplierID;
		return request(stringForRequest);
	}

	String addNewSupplier(int supplierID, String companyName, String address, String salesContact) {
		String stringForRequest = "11\n" + supplierID + "\n" + companyName + "\n" + address + "\n" + salesContact;
		return request(stringForRequest);
	}

	boolean login(String username, String password) {
		String stringForRequest = "12\n" + username + "\n" + password;
		return request(stringForRequest).equals("true");
	}

	String listOrders() {
		String stringForRequest = "0";
		return request(stringForRequest);
	}

	private String request(String s) {
		return client.request(s);
	}
	
}
