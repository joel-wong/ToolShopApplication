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

            System.out.println(supplierID);
            System.out.println(companyName);
            System.out.println(address);
            System.out.println(salesContact);

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
        System.out.println("Generated an orderline for item " + tool.getToolID());
        //The tool now has a pending order
        tool.setPendingOrder(true);
    }

    /**
     * Prints the current Order of the Shop Application to the specified file.
     *
     * @param fileName is the name of the specified file
     * @throws IOException
     */
    public void printOrderToFile(String fileName) throws IOException {
        //call order toString method

        FileWriter f = new FileWriter(fileName, true);
        PrintWriter pr1 = new PrintWriter(f);
        pr1.println(order);
        pr1.close();
    }

    /**
     * Implements the interactive console-based menu that keeps presenting the user with the menu for the
     * shop application until the user quits.
     * The user enters numbers corresponding to tasks for the application.
     *
     * @throws IOException
     */
    public void menu() throws IOException {
        Scanner sc = new Scanner(System.in);
        int menuItem;
        boolean quit = false;
        while (quit == false) {
            printMenu();
            try {
                menuItem = sc.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("That is not a menu option.");
                sc.next();
                continue;
            }
            System.out.println(lineOfStars);
            switch (menuItem) {
                case 1:
                    listTools();
                    break;
                case 2:
                    listSuppliers();
                    break;
                case 3:
                    searchInventoryByName();
                    break;
                case 4:
                    searchInventoryByID();
                    break;
                case 5:
                    checkToolQuantity();
                    break;
                case 6:
                    removeTools();
                    break;
                case 7:
                    addTools();
                    break;
                case 8:
                    setNewDate();
                    break;
                case 9:
                    deleteTool();
                    break;
                case 10:
                    addNewToolToInventory();
                    break;
                case 11:
                    addNewSupplier();
                    break;
                case 12:
                    quit();
                    quit = true;
                    break;
                default:
                    System.out.println("Not a valid menu number. Please try again.");

            }
            System.out.println(lineOfStars);
        }
    }

    /**
     * Prints the menu choices for the Shop Application.
     */
    public void printMenu() {
        System.out.println(lineOfEquals);
        System.out.println(lineOfEqualsWithMenuInCenter);
        System.out.println(lineOfEquals);
        System.out.println("Current Order date: " + date);
        System.out.println("1.\tList all tools in inventory");
        System.out.println("2.\tList suppliers");
        System.out.println("3.\tSearch for tool by tool name");
        System.out.println("4.\tSearch for tool by tool id");
        System.out.println("5.\tCheck item quantity");
        System.out.println("6.\tDecrease item quantity");
        System.out.println("7.\tIncrease item quantity");
        System.out.println("8.\tChange the date. Prints current order to file " + ordersFile + " and generates new order with new date.");
        System.out.println("9.\tDelete tool from inventory");
        System.out.println("10.\tAdd new tool to inventory");
        System.out.println("11.\tAdd new supplier");
        System.out.println("12.\tQuit. Prints current order to file " + ordersFile + ".");
        System.out.println(lineOfEquals);
        System.out.println("Please enter a menu number to perform the corresponding task:");
    }

    //MENU TASKS

    //1.

    /**
     * Prints a lists of all the Tools in the inventory of the Shop Application.
     */
    public void listTools() {
        inventory.listTools();
    }

    //2.

    /**
     * Prints a list of all the Suppliers in the Supplier list of the Shop Application.
     */
    public void listSuppliers() {
        for (Supplier s : supplierList) {
            System.out.println(s);
        }
    }

    //3.

    /**
     * Prompts the user to enter a Tool name and then searches the inventory using the Tool name.
     * If the Tool is found, method prints the Tool information, otherwise prints that the Tool was not found.
     */
    public void searchInventoryByName() {
        //use Inventory method searchInventory
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("[;|\n\r]+");
        System.out.println("Please enter the tool name:");
        String toolName = sc.next();

        Tool t = inventory.searchInventory(toolName);
        if (t == null)
            System.out.println("\nSorry, Tool was not found.");
        else {
            System.out.println("\nTool found: ");
            System.out.println(t);
        }

    }

    //4.

    /**
     * Prompts the user to enter a Tool ID and then searches the inventory using the Tool ID.
     * If the Tool is found, method prints the Tool information, otherwise prints that the Tool was not found.
     */
    public void searchInventoryByID() {
        Scanner sc = new Scanner(System.in).useDelimiter("[;|\n\r]+");
        System.out.println("Please enter the Tool ID:");
        Tool t = null;
        try {
            int toolID = sc.nextInt();
            t = inventory.searchInventory(toolID);
            if (t == null)
                System.out.println("\nSorry, Tool was not found.");
            else {
                System.out.println("\nTool found: ");
                System.out.println(t);
            }
        } catch (NumberFormatException e) {
            System.out.println("That is not an integer.");
        }
    }

    //5.

    /**
     * Prompts the user to enter a Tool ID and then prints the quantity of the tool corresponding to the Tool ID.
     * If the Tool ID does not corrospond to a Tool in the inventory the quantity printed will be 0.
     */
    public void checkToolQuantity() {
        Scanner sc = new Scanner(System.in).useDelimiter("[;|\n\r]+");
        System.out.println("Please enter the tool ID:");
        try {
            int toolID = sc.nextInt();
            System.out.println("Quantity: " + inventory.checkToolQuantity(toolID));
        }
        catch (InputMismatchException e){
            System.out.println("That is not an integer.");
        }

    }

    //6.

    /**
     * Prompts the uses to enter a Tool ID and the amount to decrease the quantity of the Tool by
     * and then decreases the quantity of the corresponding Tool in the inventory.
     * If quantity of the tool is successfully decreased and the new quantity of the tool is below the threshold and
     * if there is no pending order, a default orderline for the tool is generated for the current Order of the shop application.
     */
    public void removeTools() {

        Scanner sc = new Scanner(System.in).useDelimiter("[;|\n\r]+");
        int toolID, amountRemoved;
        System.out.println("Please enter the tool ID:");
        toolID = sc.nextInt();
        System.out.println("Please enter the amount of items to be removed :");
        amountRemoved = sc.nextInt();

        int quantityLeft = inventory.removeTools(toolID, amountRemoved);
        Tool t = inventory.searchInventory(toolID);
        //Checking if an orderline needs to be generated
        if (t != null)
            if (quantityLeft < itemQuantityMinimum && !t.checkAlreadyPendingOrder()) {
                generateDefaultOrderline(t, quantityLeft);

            }
    }
    //7.

    /**
     * Prompts the uses to enter a Tool ID and the amount to increase the quantity of the Tool by
     * and then increases the quantity of the corresponding Tool in the inventory.
     */
    public void addTools() {
        Scanner sc = new Scanner(System.in).useDelimiter("[;|\n\r]+");
        System.out.println("Please enter the tool ID:");
        int toolID = sc.nextInt();
        System.out.println("Please enter the amount of items to be added :");
        int amountAdded = sc.nextInt();

        //use Inventory method: addTools
        inventory.addTools(toolID, amountAdded);
    }

    //8.

    /**
     * Prompts the user to enter a month, day, and year to set the new Date for the application and then
     * prints the current Order to a default file and creates a new Order with the user specified date.
     *
     * @throws IOException
     */
    public void setNewDate() throws IOException {
        Scanner sc = new Scanner(System.in).useDelimiter("[;|\n\r]+");
        printOrderToFile(ordersFile);
        System.out.println("Please enter the name of the month:");
        String month = sc.next();
        System.out.println("Please enter the day:");
        int day = sc.nextInt();
        System.out.println("Please enter the year:");
        int year = sc.nextInt();

        //changes the date
        date.setMonth(month);
        date.setDay(day);
        date.setYear(year);
        //calls generateNewOrder
        generateNewOrder();
        System.out.println("Date changed to: " + date);
        System.out.println("New Order has been generated and previous Order has been printed to file \"orders.txt\"");
    }

    //9.

    /**
     * Prompts the user to enter a Tool ID and then deletes the corresponding Tool from the Inventory.
     */
    public void deleteTool() {
        Scanner sc = new Scanner(System.in).useDelimiter("[;|\n\r]+");
        System.out.println("Please enter the tool ID:");
        int toolID = sc.nextInt();

        inventory.deleteTool(toolID);
    }

    //10.

    /**
     * Prompts the user for all the necessary information to add a new Tool to the inventory and then
     * adds the Tool. Requires that the user enters a Supplier ID that corresponds to a Supplier in the Application's
     * list of Suppliers. If the quantity of the tool is below the threshold,
     * a default orderline for the tool is generated for the current Order of the shop application.
     */
    public void addNewToolToInventory() {
        //use supplierID to go through supplierList and find the Supplier object
        //call the Inventory method addNewTool
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("[;|\n\r]+");
        int toolID;
        String toolName;
        int quantity;
        double price;
        int supplierID;
        System.out.println("Set Tool ID:");
        toolID = sc.nextInt();
        System.out.println("Set Tool Name:");
        toolName = sc.next();
        System.out.println("Set Tool Quantity:");
        quantity = sc.nextInt();
        System.out.println("Set Tool Price:");
        price = sc.nextDouble();
        System.out.println("Set Supplier ID:");
        supplierID = sc.nextInt();
        Supplier s = searchSupplier(supplierID);
        if (s == null) {
            System.out.println("Supplier ID not recognized.");
            return;
        }
        inventory.addNewTool(toolID, toolName, quantity, price, s);
        System.out.println("Successfully added new tool to inventory:");
        Tool t = inventory.searchInventory(toolID);
        System.out.println(t);
        if (quantity < itemQuantityMinimum) {
            generateDefaultOrderline(t, quantity);
        }
    }


    //11.

    /**
     * Prompts the user to enter all the necessary information to add a new Supplier to the list
     * of Suppliers in the Application and then adds the new Supplier to the list.
     */
    public void addNewSupplier() {
        //use Supplier constructor
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("[;|\n\r]+");
        int supplierID;
        String companyName;
        String address;
        String salesContact;
        System.out.println("Set Supplier ID:");
        supplierID = sc.nextInt();
        System.out.println("Set Company Name:");
        companyName = sc.next();
        System.out.println("Set Address:");
        address = sc.next();
        System.out.println("Set Sales Contact:");
        salesContact = sc.next();

        supplierList.add(new Supplier(supplierID, companyName, address, salesContact));
        System.out.println("Successfully added new supplier to List of Suppliers:");
        System.out.println(supplierList.get(supplierList.size() - 1));

    }
    //12.

    /**
     * Prints the current Order to the default file and then prints that it is
     * exiting the application.
     *
     * @throws IOException
     */
    public void quit() throws IOException {
        printOrderToFile(ordersFile);
        System.out.println("Exiting application...");
    }


}
