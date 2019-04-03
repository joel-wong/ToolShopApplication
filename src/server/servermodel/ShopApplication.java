package server.servermodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
     * Inventory of the Tool Shop
     */
    private Inventory inventory;
    /**
     * Supplier List of all the Suppliers of the Tools in the Tool Shop
     */
    private ArrayList<Supplier> supplierList;
    /**
     * Current Order of the day that is being edited by the application
     */
    private Order order;
    /**
     * Current date
     */
    private Date date;

    /**
     * Constructs a ShopApplication object assigned with the specified inventory, supplier list, order, and date.
     * Generates a new order for the day.
     *
     * @param inventory    is the ShopApplication's inventory
     * @param supplierList is the ShopApplication's supplier list
     * @param order        is the ShopApplication's order
     * @param date         is the ShopApplication's date
     */
    public ShopApplication() {
        setupShop();
    }

    public void setupShop(){
        ArrayList<Tool> toolList = new ArrayList<Tool>();
        Inventory inventory = new Inventory(toolList);
        ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
        Order order = new Order();
        Calendar javaDate = Calendar.getInstance();
        Format formatter = new SimpleDateFormat("MMMM");
        String currentMonth = formatter.format(javaDate.getTime());
        Date currentDate = new Date(currentMonth, javaDate.get(Calendar.DAY_OF_MONTH), javaDate.get(Calendar.YEAR));

        this.inventory = inventory;
        this.supplierList = supplierList;
        this.order = order;
        this.date = currentDate;

        generateNewOrder();

        //loading in the supplier and item information from the text files into the Application
        try {
            loadSuppliers("suppliers.txt");
            loadInventory("items.txt");
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found:" + e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Loads the inventory with Tool information found in the specified file.
     * Assumes that information in the file is in the following format:
     * toolID;toolName;quantity;price;supplierID\n.
     * If the quantity of the Tool is less than the threshold, an automatic orderline is generated for the tool.
     *
     * @param fileName is the name of the file containing the Tool information
     * @throws FileNotFoundException
     */
    public void loadInventory(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName)).useDelimiter("[;|\n\r]+");
        int toolID;
        String toolName;
        int quantity;
        double price;
        int supplierID;

        while (sc.hasNextInt()) {
            toolID = sc.nextInt();
            toolName = sc.next();
            quantity = sc.nextInt();
            price = sc.nextDouble();
            supplierID = sc.nextInt();
            if(sc.hasNextLine()) {
                sc.nextLine();
            }

            inventory.addNewTool(toolID, toolName, quantity, price, searchSupplier(supplierID));
            if (quantity < itemQuantityMinimum) {
                Tool t = inventory.searchInventory(toolID);
                generateDefaultOrderline(t, quantity);

            }
        }
    }

    /**
     * Loads the Supplier list with Supplier information found in the specified file.
     * Assumes that information in the file is in the following format:
     * supplierID;companyName;address;salesContact\n.
     *
     * @param fileName is the name of the file containing the Supplier information
     * @throws FileNotFoundException
     */
    public void loadSuppliers(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName)).useDelimiter("[;|\n\r]+");
        int supplierID;
        String companyName;
        String address;
        String salesContact;

        while (sc.hasNext()) {
            supplierID = sc.nextInt();
            companyName = sc.next();
            address = sc.next();
            salesContact = sc.next();
            if(sc.hasNextLine()) {
                sc.nextLine();
            }

            supplierList.add(new Supplier(supplierID, companyName, address, salesContact));
        }
    }

    /**
     * Searches the Supplier list using a given supplier ID number.
     * If the given supplier ID does not match a supplier ID in the Supplier list then returns null.
     *
     * @param supplierID is the specified supplier ID
     * @return the Supplier object that has the matching supplier ID
     */
    public Supplier searchSupplier(int supplierID) {
        for (Supplier s : supplierList) {
            if (s.getSupplierID() == supplierID)
                return s;
        }

        return null;
    }

    /**
     * Generates a new Order using the current Date of the Shop Application.
     */
    public void generateNewOrder() {

        order.newOrder(date.getMonth(), date.getDay(), date.getYear());

    }

    /**
     * Generates a default OrderLine for the current Order of the Shop Application for the given
     * Tool.
     *
     * @param tool            is the Tool to generate the orderline for
     * @param currentQuantity is the current quantity of the Tool
     */
    public void generateDefaultOrderline(Tool tool, int currentQuantity) {
        order.addOrderLine(tool, itemQuantityMaximum - currentQuantity);
//        System.out.println("Generated an orderline for item " + tool.getToolID());
        //The tool now has a pending order
        tool.setPendingOrder(true);
    }

    /**
     * Prints the current Order of the Shop Application to the specified file.
     *
     * @param fileName is the name of the specified file
     * @throws IOException
     */
    public void printOrderToFile(String fileName) {
        //call order toString method
        try {
            FileWriter f = new FileWriter(fileName, true);
            PrintWriter pr1 = new PrintWriter(f);
            pr1.println(order);
            pr1.close();
        }
        catch (IOException e) {
            System.err.println("Error, could not save orders to file");
            System.err.println("Error message: " + e.getMessage());
        }
    }

    //0.

    /**
     * Print a list of all orders and their order items
     */
    public String listOrders() {
        String stringToReturn = "\n\t\tList of Orders:\n\n";
        stringToReturn += lineOfEquals + "\n\n";
//        for (int i = 0; i < orders.size(); i++) {
            stringToReturn += order.toString() + "\n";
//        }
        stringToReturn += "\n" + lineOfEquals;
        return stringToReturn;
    }

    //1.

    /**
     * Prints a lists of all the Tools in the inventory of the Shop Application.
     */
    public String listTools() {
        return inventory.listTools();
    }

    //2.

    /**
     * Prints a list of all the Suppliers in the Supplier list of the Shop Application.
     */
    public String listSuppliers() {
        String supplierListString = "";
        for (Supplier s : supplierList) {
            supplierListString += s + "\n";
        }
        return supplierListString;
    }

    //3.

    /**
     * Prompts the user to enter a Tool name and then searches the inventory using the Tool name.
     * If the Tool is found, method prints the Tool information, otherwise prints that the Tool was not found.
     */
    public String searchInventoryByName(String toolName) {
        //use Inventory method searchInventory

        Tool t = inventory.searchInventory(toolName);
        if (t == null)
            return "Sorry, Tool was not found.";
        else {
            String response = "Tool found:\n\n";
            response += t;
            return response;
        }

    }

    //4.

    /**
     * Prompts the user to enter a Tool ID and then searches the inventory using the Tool ID.
     * If the Tool is found, method prints the Tool information, otherwise prints that the Tool was not found.
     */
    public String searchInventoryByID(int toolID) {
        Tool t = null;
        t = inventory.searchInventory(toolID);
        if (t == null)
            return "Sorry, Tool was not found.\n";
        else {
            String response = "Tool found: \n";
            response += t;
            return response;
        }
    }

    //5.

    /**
     * Prompts the user to enter a Tool ID and then prints the quantity of the tool corresponding to the Tool ID.
     * If the Tool ID does not corrospond to a Tool in the inventory the returned String indicates that.
     */
    public String checkToolQuantity(int toolID) {
        Tool t = null;
        t = inventory.searchInventory(toolID);
        if (t == null)
            return "Sorry, Tool was not found.\n";
        else {
            return "Quantity: " + inventory.checkToolQuantity(toolID);
        }
    }

    //6.

    /**
     * Prompts the uses to enter a Tool ID and the amount to decrease the quantity of the Tool by
     * and then decreases the quantity of the corresponding Tool in the inventory.
     * If quantity of the tool is successfully decreased and the new quantity of the tool is below the threshold and
     * if there is no pending order, a default orderline for the tool is generated for the current Order of the shop application.
     */
    public String removeTools(int toolID, int amountRemoved) {

        Tool t = inventory.searchInventory(toolID);
        //Checking if an orderline needs to be generated
        if (t != null){
            String response = inventory.removeTools(toolID, amountRemoved);
            int quantityLeft = inventory.checkToolQuantity(toolID);
            if (quantityLeft < itemQuantityMinimum && !t.checkAlreadyPendingOrder()) {
                generateDefaultOrderline(t, quantityLeft);
                response += "A new order has been created\n";
            }
            return response;
        }
        else {
            return "Sorry, that tool was not found\n";
        }
    }
    //7.

    /**
     * Prompts the uses to enter a Tool ID and the amount to increase the quantity of the Tool by
     * and then increases the quantity of the corresponding Tool in the inventory.
     */
    public String addTools(int toolID, int amountAdded) {
        //use Inventory method: addTools
        return inventory.addTools(toolID, amountAdded);
    }

    //8.

    /**
     * Prompts the user to enter a month, day, and year to set the new Date for the application and then
     * prints the current Order to a default file and creates a new Order with the user specified date.
     *
     * @throws IOException
     */
    public String setNewDate(String month, int day, int year) {
        printOrderToFile(ordersFile);

        //changes the date
        date.setMonth(month);
        date.setDay(day);
        date.setYear(year);

        //calls generateNewOrder
        generateNewOrder();
        String response = "Date changed to: " + date + "\n";
        response += "New Order has been generated and previous Order has been printed to file \"" + ordersFile + "\"\n";
        return response;
    }

    //9.

    /**
     * Prompts the user to enter a Tool ID and then deletes the corresponding Tool from the Inventory.
     */
    public String deleteTool(int toolID) {
        return inventory.deleteTool(toolID);
    }

    //10.

    /**
     * Prompts the user for all the necessary information to add a new Tool to the inventory and then
     * adds the Tool. Requires that the user enters a Supplier ID that corresponds to a Supplier in the Application's
     * list of Suppliers. If the quantity of the tool is below the threshold,
     * a default orderline for the tool is generated for the current Order of the shop application.
     */
    public String addNewToolToInventory(int toolID, String toolName, int quantity, double price, int supplierID) {
        //use supplierID to go through supplierList and find the Supplier object
        //call the Inventory method addNewTool
        Supplier s = searchSupplier(supplierID);
        if (s == null) {
            return "Supplier ID not recognized.\n";
        }
        inventory.addNewTool(toolID, toolName, quantity, price, s);
        String response = "Successfully added new tool to inventory:\n";
        Tool t = inventory.searchInventory(toolID);
        response += t;
        if (quantity < itemQuantityMinimum) {
            generateDefaultOrderline(t, quantity);
        }
        return response;
    }


    //11.

    /**
     * Prompts the user to enter all the necessary information to add a new Supplier to the list
     * of Suppliers in the Application and then adds the new Supplier to the list.
     */
    public String addNewSupplier(int supplierID, String companyName, String address, String salesContact) {
        //use Supplier constructor

        supplierList.add(new Supplier(supplierID, companyName, address, salesContact));
        String response = "Successfully added new supplier to List of Suppliers:\n";
        response += supplierList.get(supplierList.size() - 1);
        return response;

    }

    // 12.
    // Checks if a given username/password (hased with SHA512) is valid
    public String checkLogin(String username, String hashedPassword) {
        return Authenticator.authenticate(username, hashedPassword);
    }

    // 13.
}