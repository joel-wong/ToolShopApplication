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

	/* FUNCTIONS THAT WENDY NEEDS TO ADD TO GUI -------------------------------------------------------------------------------*/

	public String listSuppliers() {
		String stringForRequest = "2";
		return request(stringForRequest);
	}

	public String addTools(int toolID, int amountAdded) {
		String stringForRequest = "7\n" + toolID + "\n" + amountAdded;
		return request(stringForRequest);
	}

	public String setNewDate(String month, int day, int year) {
		String stringForRequest = "8\n" + month + "\n" + day + "\n" + year;
		return request(stringForRequest);
	}

	public String deleteTool(int toolID) {
		String stringForRequest = "9\n" + toolID;
		return request(stringForRequest);
	}

	public String addNewToolToInventory(int toolID, String toolName, int quantity, double price, int supplierID) {
		String stringForRequest = "10\n" + toolID + "\n" + toolName + "\n" + quantity + "\n" + price + "\n" + supplierID;
		return request(stringForRequest);
	}

	public String addNewSupplier(int supplierID, String companyName, String address, String salesContact) {
		String stringForRequest = "11\n" + supplierID + "\n" + companyName + "\n" + address + "\n" + salesContact;
		return request(stringForRequest);
	}

	/* END FO FUNCTIONS THAT WENDY NEEDS TO ADD TO GUI ----------------------------------------------------------------------- */






	
	public String request(String s) {
		return client.request(s);
	}
	
}
