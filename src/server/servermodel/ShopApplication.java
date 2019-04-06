package server.servermodel;

import server.servermodel.database.DatabaseConnectionManager;
import java.util.Calendar;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Provides data fields and methods to create a Tool Shop application.
 *
 * @author Wenjia Yang and Joel Wong
 * @version 1.1
 * @since April 5, 2019
 */
public class ShopApplication implements Constants {
    /**
     * Current date
     */
    private Date date;

    /**
     * Supplier manager
     */
    private SupplierManager supplierManager;
    /**
     * Order manager
     */
    private OrderManager orderManager;
    /**
     * Tool manager
     */
    private ToolManager toolManager;
    /**
     * Password manager
     */
    private Authenticator authenticator;


    /**
     * Constructs a ShopApplication object and sets it up.
     *
     */
    public ShopApplication() {
        setupShop();
    }

    /**
     * Sets up the shop with the current date and creates the supplier manager, order manager, tool manager and authenticator.
     */
    private void setupShop(){
        Calendar javaDate = Calendar.getInstance();
        Format formatter = new SimpleDateFormat("MMMM");
        String currentMonth = formatter.format(javaDate.getTime());
        this.date = new Date(currentMonth, javaDate.get(Calendar.DAY_OF_MONTH), javaDate.get(Calendar.YEAR));

        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager();
        this.supplierManager = new SupplierManager(databaseConnectionManager);
        this.orderManager = new OrderManager(databaseConnectionManager);
        this.toolManager = new ToolManager(databaseConnectionManager, date);
        this.authenticator = new Authenticator(databaseConnectionManager);
    }

    //0.
    
    /**
     * Gets the list of Orders.
     * @return the list of orders.
     */
    String listOrders() {
        return orderManager.listOrders();
    }

    //1.
    
    /**
     * Gets the list of Tools.
     * @return the list of Tools
     */
    String listTools() {
        return toolManager.listTools();
    }

    //2.
    
    /**
     * Gets the list of Suppliers.
     * @return the list of Suppliers
     */
    String listSuppliers() {
        return supplierManager.listSuppliers();
    }

    //3.

    /**
     * Searches the shop inventory with the specified Tool name.
     * @param toolName is the specified Tool name
     * @return the result of the search
     */
    String searchInventoryByName(String toolName) {
        return toolManager.searchToolByName(toolName);
    }

    //4.

    /**
     * Searches the shop inventory with the specified Tool ID.
     * @param toolID is the specified Tool ID
     * @return the result of the search
     */
    String searchInventoryByID(int toolID) {
        return toolManager.searchToolByID(toolID);
    }

    //5.

    /**
     * Checks the quantity of the specified Tool.
     * @param toolID is the ID of the specified Tool
     * @return the quantity of the Tool in String format
     */
    String checkToolQuantity(int toolID) {
        return toolManager.getToolQuantity(toolID);
    }

    //6.

    /**
     * Decreases the quantity of the specified Tool by the specified amount.
     * @param toolID is the ID of the specified Tool
     * @param amountRemoved is the amount to decrease the quantity by
     * @return the result of the request
     */
    String decreaseToolQuantity(int toolID, int amountRemoved) {
        return toolManager.decreaseToolQuantity(toolID, amountRemoved);
    }
    //7.

    /**
     * Increases the quantity of the specified Tool by the specified amount.
     * @param toolID is the ID of the specified Tool
     * @param amountAdded is the amount to increase the quantity by
     * @return the result of the request
     */
    String increaseToolQuantity(int toolID, int amountAdded) {
        return toolManager.increaseToolQuantity(toolID, amountAdded);
    }

    //8.

    
    /**
     * Set the new Date for the application.
     * @param month is the new month 
     * @param day is the new day
     * @param year is the new year
     * @return message indicating what the date was changed to
     */
    String setNewDate(String month, int day, int year) {
        date.setMonth(month);
        date.setDay(day);
        date.setYear(year);

        return "Date changed to: " + date + "\n";
    }

    //9.

    /**
     * Deletes the specified Tool from the shop inventory.
     * @param toolID is the ID of the specified Tool
     * @return the result of the request
     */
    String deleteTool(int toolID) {
        return toolManager.deleteTool(toolID);
    }

    //10.

    /**
     * Adds a new Tool to the shop inventory with the specified Tool ID, Tool name, quantity, price, and Supplier ID.
     * @param toolID is the specified Tool ID
     * @param toolName is the specified Tool name
     * @param quantity is the specified quantity
     * @param price is the specified price
     * @param supplierID is the specified SupplierID
     * @return the result of the request
     */
    String addNewToolToInventory(int toolID, String toolName, int quantity, double price, int supplierID) {
        return toolManager.addTool(toolID, toolName, quantity, price, false, supplierID);
    }


    //11.

    /**
     * Adds a new Supplier to the shop application with the specified Supplier ID, company name, address, and sales contact.
     * @param supplierID is the specified Supplier ID
     * @param companyName is the Supplier company name
     * @param address is the address of the Supplier
     * @param salesContact is the name of the Supplier sales contact
     * @return the result of the request
     */
    String addNewSupplier(int supplierID, String companyName, String address, String salesContact) {
        return supplierManager.addSupplier(supplierID, companyName, address, salesContact);

    }

    // 12.

    /**
     * Checks if a given username/password is valid
     * @param username is the given username
     * @param password is the given password
     * @return the true or false in String format
     */
    String checkLogin(String username, String password) {
        return authenticator.authenticate(username, password);
    }

}