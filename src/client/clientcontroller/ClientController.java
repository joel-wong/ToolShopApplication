package client.clientcontroller;

/**
 * This class is responsible for requesting information from the server in a Tool Shop Application.
 * @author Wenjia Yang and Joel Wong
 * @version 1.0
 * @since April 4, 2019
 */
public class ClientController {
	/**
	 * The Client that communicates with the Tool Shop Application Server
	 */
	private Client client;
	
	/**
	 * Constructs a ClientController object and assigns the specified client.
	 * @param client is the specified client
	 */
	public ClientController(Client client) {
		this.client = client;
	}

	/**
	 * Requests the list of Tools from the server.
	 * @return the list of Tools in String format.
	 */
	String listTools() {
		String stringForRequest = "1";
		return request(stringForRequest);
	}

	/**
	 * Requests to decrease the quantity of a specified Tool.
	 * @param toolID is the ID of the specified Tool
	 * @param amountRemoved is the amount to decrease the quantity by
	 * @return the response from the server
	 */
	String removeTools(int toolID, int amountRemoved) {
		String stringForRequest = "6\n" + toolID + "\n" + amountRemoved;
		return request(stringForRequest);
	}

	/**
	 * Requests to search for a specified Tool.
	 * @param toolName is the name of the specified Tool
	 * @return the response from the server
	 */
	String searchInventory(String toolName) {
		String stringForRequest = "3\n" + toolName;
		return request(stringForRequest);
	}
	
	/**
	 * Requests to search for a specified Tool.
	 * @param toolID is the ID of the specified Tool
	 * @return the response from the server
	 */
	String searchInventory(int toolID) {
		String stringForRequest = "4\n" + toolID;
		return request(stringForRequest);
	}

	/**
	 * Requests to check the quantity of a specified Tool.
	 * @param toolID is the ID of the specified Tool
	 * @return the response from the server
	 */
	String checkToolQuantity(int toolID) {
		String stringForRequest = "5\n" + toolID;
		return request(stringForRequest);
	}

	/**
	 * Requests the list of Supplier from the server.
	 * @return the list of Suppliers in String format.
	 */
	String listSuppliers() {
		String stringForRequest = "2";
		return request(stringForRequest);
	}
	
	/**
	 * Requests to increase the quantity of a specified Tool.
	 * @param toolID is the ID of the specified Tool
	 * @param amountAdded is the amount to increase the quantity by
	 * @return the response from the server
	 */
	String addTools(int toolID, int amountAdded) {
		String stringForRequest = "7\n" + toolID + "\n" + amountAdded;
		return request(stringForRequest);
	}

	/**
	 * Requests to set a new Date for the application.
	 * @param month is the new month
	 * @param day is the new day
	 * @param year is the new year
	 * @return the response from the server
	 */
	String setNewDate(String month, int day, int year) {
		String stringForRequest = "8\n" + month + "\n" + day + "\n" + year;
		return request(stringForRequest);
	}

	/**
	 * Requests to delete a specified Tool.
	 * @param toolID is the ID of the specified Tool
	 * @return the response from the server
	 */
	String deleteTool(int toolID) {
		String stringForRequest = "9\n" + toolID;
		return request(stringForRequest);
	}

	/**
	 * Requests to add a new Tool to the shop Inventory with the specified parameters.
	 * @param toolID is the new Tool ID
	 * @param toolName is the new Tool name
	 * @param quantity is the new Tool quantity
	 * @param price is the new Tool price
	 * @param supplierID is the new Tool supplier ID 
	 * @return the response from the server
	 */
	String addNewToolToInventory(int toolID, String toolName, int quantity, double price, int supplierID) {
		String stringForRequest = "10\n" + toolID + "\n" + toolName + "\n" + quantity + "\n" + price + "\n" + supplierID;
		return request(stringForRequest);
	}

	/**
	 * Requests to add a new Supplier to the shop with the specified parameters.
	 * @param supplierID is the new Supplier ID
	 * @param companyName is the new Supplier company name
	 * @param address is the new Supplier address
	 * @param salesContact is the new Supplier sales contact name
	 * @return the response from the server
	 */
	String addNewSupplier(int supplierID, String companyName, String address, String salesContact) {
		String stringForRequest = "11\n" + supplierID + "\n" + companyName + "\n" + address + "\n" + salesContact;
		return request(stringForRequest);
	}

	/**
	 * Checks with the server if a login with the specified username and password is correct.
	 * @param username is the specified username
	 * @param password is the password
	 * @return true is the username and password are correct, otherwise return false
	 */
	boolean login(String username, String password) {
		String stringForRequest = "12\n" + username + "\n" + password;
		return request(stringForRequest).equals("true");
	}

	/**
	 * Requests the list of Orders from the server.
	 * @return the list of Orders in String format.
	 */
	String listOrders() {
		String stringForRequest = "0";
		return request(stringForRequest);
	}

	/**
	 * Makes a request from the client to the server.
	 * @param s is the request sent to the server
	 * @return the response from the server
	 */
	private String request(String s) {
		return client.request(s);
	}
	
}
