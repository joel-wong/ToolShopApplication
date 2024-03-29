package server.servermodel;

import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Decodes strings sent from the client, calls the appropriate model functionality, and returns the expected values.
 *
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 */
public class ShopManager {

    /**
     * ShopManager will make function calls to the shopApplicationInstance based on input
     */
    private ShopApplication shopApplicationInstance;

    /** Constructor that creates the interface to communicate with the shop application
     *
     * @param shopApplicationInstance The shop application
     */
    public ShopManager(ShopApplication shopApplicationInstance){
        this.shopApplicationInstance = shopApplicationInstance;
    }

    /** Receives a String with multiple lines and calls the appropriate function with the appropriate inputs.
     * The first line must be an integer corresponding to one of the functions in the toolShop.
     * The remaining lines will be arguments relating to the the specific functionality
     *
     * @param input The input String received from the client
     * @return A String with relevant information to return to the client, based on function calls
     */
    public synchronized String serviceRequest(String input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("[;|\n\r]+");
        int menuItem;
        try {
            try {
                menuItem = sc.nextInt();
            }
            catch(InputMismatchException e) {
                return "String received by Shop Interface did not start with a number";
            }
            switch (menuItem) {
                case 0:
                    return shopApplicationInstance.listOrders();

                case 1:
                    return shopApplicationInstance.listTools();

                case 2:
                    return shopApplicationInstance.listSuppliers();

                case 3:
                    String toolNameCase3;
                    toolNameCase3 = sc.next();
                    return shopApplicationInstance.searchInventoryByName(toolNameCase3);

                case 4:
                    int toolIDCase4;
                    try {
                        toolIDCase4 = sc.nextInt();
                    }
                    catch(InputMismatchException e) {
                        return "Search by tool ID was called, but an integer was not input";
                    }
                    return shopApplicationInstance.searchInventoryByID(toolIDCase4);

                case 5:
                    int toolIDCase5;
                    try {
                        toolIDCase5 = sc.nextInt();
                    }
                    catch(InputMismatchException e) {
                        return "Check Tool Quantity by tool ID was called, but an integer was not input";
                    }
                    return shopApplicationInstance.checkToolQuantity(toolIDCase5);

                case 6:
                    int toolIDCase6, amountRemoved;
                    try {
                        toolIDCase6 = sc.nextInt();
                        amountRemoved = sc.nextInt();
                    }
                    catch (InputMismatchException e) {
                        return "The String sent by the client to the server to remove tools was in an invalid format.";
                    }
                    return shopApplicationInstance.decreaseToolQuantity(toolIDCase6, amountRemoved);

                case 7:
                    int toolIDCase7, amountAdded;
                    try {
                        toolIDCase7 = sc.nextInt();
                        amountAdded = sc.nextInt();
                    }
                    catch (InputMismatchException e) {
                        return "The String sent by the client to the server to add tools was in an invalid format.";
                    }
                    return shopApplicationInstance.increaseToolQuantity(toolIDCase7, amountAdded);

                case 8:
                    String month;
                    int day;
                    int year;
                    try {
                        month = sc.next();
                        day = sc.nextInt();
                        year = sc.nextInt();
                    }
                    catch (InputMismatchException e) {
                        return "Received an unexpected input when attempting to set a new date";
                    }
                    return shopApplicationInstance.setNewDate(month, day, year);

                case 9:
                    int toolIDCase9;
                    try {
                        toolIDCase9 = sc.nextInt();
                    }
                    catch(InputMismatchException e) {
                        return "Delete tool by tool ID was called, but an integer was not input";
                    }
                    return shopApplicationInstance.deleteTool(toolIDCase9);

                case 10:
                    int toolIDCase10;
                    String toolNameCase10;
                    int quantity;
                    double price;
                    int supplierIDCase10;
                    try {
                        toolIDCase10 = sc.nextInt();
                        toolNameCase10 = sc.next();
                        quantity = sc.nextInt();
                        price = sc.nextDouble();
                        supplierIDCase10 = sc.nextInt();
                    }
                    catch (InputMismatchException e) {
                        return "Numbers passed to add new tool was invalid.";
                    }
                    return shopApplicationInstance.addNewToolToInventory(toolIDCase10, toolNameCase10, quantity, price, supplierIDCase10);

                case 11:
                    int supplierIDCase11;
                    String companyName;
                    String address;
                    String salesContact;
                    try {
                        supplierIDCase11 = sc.nextInt();
                        companyName = sc.next();
                        address = sc.next();
                        salesContact = sc.next();
                    }
                    catch(InputMismatchException e) {
                        return "Invalid inputs to add a new supplier";
                    }
                    return shopApplicationInstance.addNewSupplier(supplierIDCase11, companyName, address, salesContact);

                case 12:
                    String username;
                    String hashedPassword;
                    username = sc.next();
                    hashedPassword = sc.next();
                    return shopApplicationInstance.checkLogin(username, hashedPassword);
                default:
                    return "Invalid menu number passed by the client to the server. Please try again.";
            }
        }
        catch(NoSuchElementException e) {
            return "Error when receiving input";
        }
        catch (Exception e) {
            return e.getMessage();
        }
        finally {
            sc.close();
        }
    }

}
