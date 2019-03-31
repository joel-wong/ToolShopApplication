import java.util.ArrayList;

package server.servermodel;

/**
 * Provides data fields and methods to create a Java data-type, representing an Inventory
 * in the Tool Shop application.
 *
 * @author Wenjia Yang
 * @version 1.0
 * @since February 6, 2019
 */
public class Inventory {
    /**
     * List of Tools available in the inventory
     */
    private ArrayList<Tool> toolList;

    /**
     * Constructs a new Inventory object and assigns the provided Tool list.
     *
     * @param toolList is the specified Tool list to be assigned
     */
    public Inventory(ArrayList<Tool> toolList) {
        this.toolList = toolList;
    }

    /**
     * Prints out a list of the Tools in the inventory.
     */
    public void listTools() {
        for (Tool t : toolList) {
            System.out.println(t);
        }
    }

    /**
     * Increases the quantity of a Tool in the inventory by a specified amount.
     * If the Tool ID does not correspond to a Tool, nothing is done and method prints that the Tool ID is not valid.
     *
     * @param toolID      is the ID number of the Tool to have its quantity increased
     * @param amountAdded is the amount to increase the quantity by
     */
    public void addTools(int toolID, int amountAdded) {
        //use Tool method: increaseQuantity
        Tool t = searchInventory(toolID);
        if (t == null) {
            System.out.println("Invalid Tool ID");
            return;
        }

        int currentQuantity = checkToolQuantity(toolID);
        t.increaseQuantity(amountAdded);

        int quantityLeft = checkToolQuantity(toolID);

        System.out.println("Successfully added " + amountAdded + " items with toolID " + toolID + ".");
        System.out.println("New tool quantity: " + quantityLeft);


    }

    /**
     * Decreases the quantity of a Tool in the inventory by a specified amount.
     * If the Tool ID does not correspond to a Tool, nothing is done and method prints that the Tool ID is not valid and returns -1.
     * If there is not enough current quantity to remove the amount that the user requests, nothing is done and method prints
     * that removal was unsuccessful.
     *
     * @param toolID        is the ID number of the Tool to have its quantity increased
     * @param amountRemoved is the amount to decrease the quantity by
     * @return the remaining quantity of the tool
     */
    public int removeTools(int toolID, int amountRemoved) {
        //use Tool method: decreaseQuantity

        int currentQuantity = checkToolQuantity(toolID);

        Tool t = searchInventory(toolID);
        if (t == null) {
            System.out.println("Invalid Tool ID");
            return -1;
        }
        t.decreaseQuantity(amountRemoved);

        int quantityLeft = checkToolQuantity(toolID);

        //If no tools were removed. ie: not enough quantity to remove the desired amount
        if (currentQuantity == quantityLeft)
            System.out.println("Unsuccessful. Not enough quantity in stock to remove that amount.");
        else {
            System.out.println("Successfully removed " + amountRemoved + " items with toolID " + toolID + ".");
            System.out.println("New tool quantity: " + quantityLeft);
        }
        return quantityLeft;
    }

    /**
     * Adds a new Tool with the specified parameters to the list of Tools in the inventory.
     *
     * @param toolID   is the new Tool ID number
     * @param toolName is the new Tool name
     * @param quantity is the new Tool quantity
     * @param price    is the new Tool price
     * @param supplier is the Supplier object that the Tool is associated with
     */
    public void addNewTool(int toolID, String toolName, int quantity, double price, Supplier supplier) {
        toolList.add(new Tool(toolID, toolName, quantity, price));
        toolList.get(toolList.size() - 1).assignSupplier(supplier);
    }

    /**
     * Deletes a Tool in the inventory Tool list.
     * If the ToolID does not correspond to a Tool in the inventory then nothing is done and method prints
     * that it is an invalid tool id. This method does not remove the tool from the Supplier object's list of tools
     * as it is assumed that the Supplier continues to supply the tool regardless of whether or not the Tool is in the
     * inventory.
     *
     * @param toolID is the ID number of the Tool to be deleted
     */
    public void deleteTool(int toolID) {
        Tool t = searchInventory(toolID);

        if (t == null) {
            System.out.println("Invalid Tool ID.");
            return;
        } else {
            toolList.remove(t);
            System.out.println("Successfully deleted Tool " + toolID);
        }

    }

    /**
     * Searches the Inventory for a Tool by tool name.
     *
     * @param toolName is the name of the Tool to be searched
     * @return the Tool object that was found or returns null if the Tool is not found
     */
    public Tool searchInventory(String toolName) {
        //use Tool method: getToolName
        toolName = toolName.toLowerCase();
        for (Tool t : toolList) {
            if (t.getToolName().toLowerCase().equals(toolName))
                return t;
        }
        return null;
    }

    /**
     * Searches the Inventory for a Tool by tool ID number.
     *
     * @param toolID is the ID number of the Tool to be searched
     * @return the Tool object that was found or returns null if the Tool is not found
     */
    public Tool searchInventory(int toolID) {
        //use Tool method: getToolID
        for (Tool t : toolList) {
            if (t.getToolID() == toolID)
                return t;
        }
        return null;
    }

    /**
     * Checks the quantity of the Tool specified by Tool ID number.
     *
     * @param toolID is the ID number for the Tool that is being checked
     * @return the quantity of the Tool in the inventory or returns 0 if the tool ID number
     * does not belong to a Tool in the inventory
     */
    public int checkToolQuantity(int toolID) {

        Tool t = searchInventory(toolID);
        if (t == null)
            return 0;
        return t.getToolQuantity();
    }
}
