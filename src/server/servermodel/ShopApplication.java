package server.servermodel;

import server.servermodel.database.DatabaseConnectionManager;
import server.servermodel.database.SupplierDatabaseTableManager;

import javax.xml.transform.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Calendar;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Provides data fields and methods to create a Tool Shop application with a console based menu.
 *
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 */
public class ShopApplication implements Constants {
    /**
     * Current date
     */
    private Date date;

    private SupplierManager supplierManager;
    private OrderManager orderManager;
    private ToolManager toolManager;
    private Authenticator authenticator;


    /**
     * Constructs a ShopApplication object assigned with the specified inventory, supplier list, order, and date.
     * Generates a new order for the day.
     *
     */
    public ShopApplication() {
        setupShop();
    }

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
     * Print a list of all orders and their order items
     */
    String listOrders() {
        return orderManager.listOrders();
    }

    //1.

    /**
     * Prints a lists of all the Tools in the inventory of the Shop Application.
     */
    String listTools() {
        return toolManager.listTools();
    }

    //2.

    /**
     * Prints a list of all the Suppliers in the Supplier list of the Shop Application.
     */
    String listSuppliers() {
        return supplierManager.listSuppliers();
    }

    //3.

    /**
     * Prompts the user to enter a Tool name and then searches the inventory using the Tool name.
     * If the Tool is found, method prints the Tool information, otherwise prints that the Tool was not found.
     */
    String searchInventoryByName(String toolName) {
        //use Inventory method searchInventory
        return toolManager.searchToolByName(toolName);
    }

    //4.

    /**
     * Prompts the user to enter a Tool ID and then searches the inventory using the Tool ID.
     * If the Tool is found, method prints the Tool information, otherwise prints that the Tool was not found.
     */
    String searchInventoryByID(int toolID) {
        return toolManager.searchToolByID(toolID);
    }

    //5.

    /**
     * Prompts the user to enter a Tool ID and then prints the quantity of the tool corresponding to the Tool ID.
     * If the Tool ID does not corrospond to a Tool in the inventory the returned String indicates that.
     */
    String checkToolQuantity(int toolID) {
        return toolManager.getToolQuantity(toolID);
    }

    //6.

    /**
     * Prompts the uses to enter a Tool ID and the amount to decrease the quantity of the Tool by
     * and then decreases the quantity of the corresponding Tool in the inventory.
     * If quantity of the tool is successfully decreased and the new quantity of the tool is below the threshold and
     * if there is no pending order, a default orderline for the tool is generated for the current Order of the shop application.
     */
    String decreaseToolQuantity(int toolID, int amountRemoved) {
        return toolManager.decreaseToolQuantity(toolID, amountRemoved);
    }
    //7.

    /**
     * Prompts the uses to enter a Tool ID and the amount to increase the quantity of the Tool by
     * and then increases the quantity of the corresponding Tool in the inventory.
     */
    String increaseToolQuantity(int toolID, int amountAdded) {
        //use Inventory method: addTools
        return toolManager.increaseToolQuantity(toolID, amountAdded);
    }

    //8.

    /**
     * Prompts the user to enter a month, day, and year to set the new Date for the application and then
     * prints the current Order to a default file and creates a new Order with the user specified date.
     */
    String setNewDate(String month, int day, int year) {
        //changes the date
        date.setMonth(month);
        date.setDay(day);
        date.setYear(year);

        //calls generateNewOrder
        return "Date changed to: " + date + "\n";
    }

    //9.

    /**
     * Prompts the user to enter a Tool ID and then deletes the corresponding Tool from the Inventory.
     */
    String deleteTool(int toolID) {
        return toolManager.deleteTool(toolID);
    }

    //10.

    /**
     * Prompts the user for all the necessary information to add a new Tool to the inventory and then
     * adds the Tool. Requires that the user enters a Supplier ID that corresponds to a Supplier in the Application's
     * list of Suppliers. If the quantity of the tool is below the threshold,
     * a default orderline for the tool is generated for the current Order of the shop application.
     */
    String addNewToolToInventory(int toolID, String toolName, int quantity, double price, int supplierID) {
        //use supplierID to go through supplierList and find the Supplier object
        //call the Inventory method addNewTool
        return toolManager.addTool(toolID, toolName, quantity, price, false, supplierID);
    }


    //11.

    /**
     * Prompts the user to enter all the necessary information to add a new Supplier to the list
     * of Suppliers in the Application and then adds the new Supplier to the list.
     */
    String addNewSupplier(int supplierID, String companyName, String address, String salesContact) {
        return supplierManager.addSupplier(supplierID, companyName, address, salesContact);

    }

    // 12.
    // Checks if a given username/password (hased with SHA512) is valid
    String checkLogin(String username, String hashedPassword) {
        return authenticator.authenticate(username, hashedPassword);
    }

}