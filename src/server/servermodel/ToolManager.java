package server.servermodel;

import server.servermodel.database.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to manage a Tool inventory in a Tool Shop application.
 * @author Wenjia Yang and Joel Wong
 * @version 1.0
 * @since April 5, 2019
 */
class ToolManager implements Constants {

	/**
	 * Supplier Database Table Manager
	 */
    private SupplierDatabaseTableManager supplierDatabaseTableManager;
    /**
     * Tool Database Table Manager
     */
    private ToolDatabaseTableManager toolDatabaseTableManager;
    /**
     * Orderline Database Table Manager
     */
    private OrderlineDatabaseTableManager orderlineDatabaseTableManager;
    /**
     * Order Database Table Manager
     */
    private OrderDatabaseTableManager orderDatabaseTableManager;
    /**
     * Date of the application
     */
    private Date date;

    /**
     * Constructs a ToolManager object with the specified DatabaseConnectionManager and Date.
     * @param databaseConnectionManager is the specified DatabaseConnectionManager
     * @param date is the specified Date
     */
    ToolManager(DatabaseConnectionManager databaseConnectionManager, Date date){
        this.supplierDatabaseTableManager = new SupplierDatabaseTableManager(databaseConnectionManager);
        this.toolDatabaseTableManager = new ToolDatabaseTableManager(databaseConnectionManager);
        this.orderDatabaseTableManager = new OrderDatabaseTableManager(databaseConnectionManager);
        this.orderlineDatabaseTableManager = new OrderlineDatabaseTableManager(databaseConnectionManager);
        this.date = date;
    }

    /**
     * Returns a list of the Tools and corresponding Tool details in the shop inventory in String form.
     * @return the list of Tools
     */
    String listTools(){
        String toolListString = "";

        try {
            ResultSet toolList = toolDatabaseTableManager.getAllTools();
            while(toolList.next()) {
                // This could be made much more efficient later, but for now it is okay ---------------------------------------------------------------------------
                toolListString += "Tool ID: " + toolList.getInt("tool_id") +
                        "\nTool Name: " + toolList.getString("tool_name") +
                        "\nQuantity in Stock: " + toolList.getInt("quantity_in_stock") +
                        "\nPrice: " + toolList.getDouble("price") +
                        "\nSupplier ID: " + toolList.getInt("supplier_id") + "\n\n";
            }
        } catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }

        return toolListString;
    }

    /**
     * Adds a new Tool with the specified parameters to the shop inventory.
     * @param toolID is the new Tool ID number
     * @param toolName is the new Tool name
     * @param quantityInStock is the new Tool quantity
     * @param price is the new Tool price
     * @param already_pending_order is true if the Tool already has a pending order
     * @param supplierID is the new Tool's Supplier's ID number
     * @return the request's resulting message
     */
    String addTool(int toolID, String toolName, int quantityInStock, double price, boolean already_pending_order, int supplierID) {
        if (toolID < 1) {
            return "Tool ID must be positive.\n";
        }
        ResultSet existingTool = toolDatabaseTableManager.searchToolByID(toolID);
        try{
            if(existingTool.next()){
               return "A tool with that ID already exists.\n";
            }
            else {
                ResultSet supplier = supplierDatabaseTableManager.searchSupplier(supplierID);
                if (!supplier.next()) {
                    return "Sorry, a supplier with that ID does not exist\n";
                }
                toolDatabaseTableManager.addTool(toolID, toolName, quantityInStock, price, already_pending_order, supplierID);
                return "Tool successfully added.\n";
            }
        }
        catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details\n";
        }
    }

    /**
     * Searches the shop inventory for a Tool by Tool ID.
     * @param toolID is the ID of the Tool to be searched
     * @return the request's resulting message
     */
    String searchToolByID(int toolID){
        if (toolID < 1) {
            return "Tool ID must be positive.\n";
        }
        ResultSet toolReturned = toolDatabaseTableManager.searchToolByID(toolID);
        return getToolDetailsAsString(toolReturned);
    }

    /**
     * Searches the shop inventory for a Tool by Tool name.
     * @param toolName is the name of the Tool to be searched
     * @return the request's resulting message
     */
    String searchToolByName(String toolName) {
        ResultSet toolReturned = toolDatabaseTableManager.searchToolByName(toolName);
        return getToolDetailsAsString(toolReturned);
    }

    /**
     * Checks the quantity of the specified Tool.
     * @param toolID is the ID number of the specified Tool
     * @return the request's resulting message
     */
    String getToolQuantity(int toolID) {
        if (toolID < 1) {
            return "Tool ID must be positive.\n";
        }
        ResultSet toolReturned = toolDatabaseTableManager.searchToolByID(toolID);
        try{
            if(toolReturned.next()){
                return "Quantity: " + toolReturned.getInt("quantity_in_stock") + "\n";
            }
            else {
                return "Sorry, tool was not found.\n";
            }
        }
        catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }
    }

    /**
     * Deletes a specified Tool from the shop inventory.
     * @param toolID is the ID number of the specified Tool
     * @return the request's resulting message
     */
    String deleteTool(int toolID) {
        if (toolID < 1) {
            return "Tool ID must be positive.\n";
        }
        ResultSet existingTool = toolDatabaseTableManager.searchToolByID(toolID);
        try{
            if(!existingTool.next()){
                return "That tool does not exist\n";
            }
            else {
                ResultSet orderlinesForTool = orderlineDatabaseTableManager.searchOrderlineByToolID(toolID);
                if (orderlinesForTool.next()) {
                    return "Tool cannot be deleted as there is an orderline with the tool.";
                }
                toolDatabaseTableManager.deleteTool(toolID);
                return "Tool successfully deleted.\n";
            }
        }
        catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details\n";
        }
    }

    /**
     * Increases the quantity of a specified Tool in the shop inventory by the specified amount.
     * @param toolID is the ID number of the specified Tool
     * @param amountAdded is the specified amount to be added
     * @return the request's resulting message
     */
    String increaseToolQuantity(int toolID, int amountAdded) {
        if (toolID < 1) {
            return "Tool ID must be positive.\n";
        }
        if (amountAdded < 1) {
            return "You must remove at least one item";
        }
        ResultSet toolReturned = toolDatabaseTableManager.searchToolByID(toolID);
        try{
            if(toolReturned.next()){
                int quantityLeft = toolReturned.getInt("quantity_in_stock");
                int newQuantity = quantityLeft + amountAdded;
                toolDatabaseTableManager.changeToolQuantity(toolID, newQuantity);
                return "New quantity is " + newQuantity + ".\n";
            }
            else {
                return "Sorry, tool was not found.\n";
            }
        }
        catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }
    }

    /**
     * Decreases the quantity of a specified Tool in the shop inventory by the specified amount.
     * @param toolID is the ID number of the specified Tool
     * @param amountRemoved is the specified amount to be removed
     * @return the request's resulting message
     */
    String decreaseToolQuantity(int toolID, int amountRemoved) {
        if (toolID < 1) {
            return "Tool ID must be positive.\n";
        }
        if (amountRemoved < 1) {
            return "You must remove at least one item";
        }
        ResultSet toolReturned = toolDatabaseTableManager.searchToolByID(toolID);
        try{
            if(toolReturned.next()){
                int quantityLeft = toolReturned.getInt("quantity_in_stock");
                if (quantityLeft < amountRemoved) {
                    return "Sorry, there is only " + quantityLeft + " " + toolReturned.getString("tool_name") + "s remaining";
                }
                else {
                    int newQuantity = quantityLeft - amountRemoved;
                    toolDatabaseTableManager.changeToolQuantity(toolID, newQuantity);
                    if (newQuantity < itemQuantityMinimum) {
                        if(!(orderlineDatabaseTableManager.searchOrderlineByToolID(toolID).next())) {
                            // no current order for this tool
                            ResultSet orderForDate = orderDatabaseTableManager.getOrderByDate(date.getDay(), date.getMonth(), date.getYear());

                            if(!orderForDate.next()) {
                                // no current order for given date
                                orderDatabaseTableManager.createOrder(date.getDay(), date.getMonth(), date.getYear());
                                orderForDate = orderDatabaseTableManager.getOrderByDate(date.getDay(), date.getMonth(), date.getYear());
                                orderForDate.next();
                            }

                            int amountToOrder = itemQuantityMaximum - newQuantity;
                            double costOfOrder = amountToOrder*(toolReturned.getDouble("price"));
                            orderlineDatabaseTableManager.createOrderLine(amountToOrder, toolID, orderForDate.getInt("order_id"), costOfOrder);

                            return "New quantity is " + newQuantity + ". An order has been generated.";
                        }
                        return "New quantity is " + newQuantity + ". No new order has been generated since an order already exists for this item";
                    }
                        return "New quantity is " + newQuantity;
                }
            }
            else {
                return "Sorry, tool was not found.\n";
            }
        }
        catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }
    }

    /**
     * Converts the Tool details to String format from the result of a Database query.
     * @param toolReturned is the result of the Database query
     * @return the String format of the Tool details
     */
    private String getToolDetailsAsString(ResultSet toolReturned) {
        try{
            if(toolReturned.next()){
                String response = "Tool found: \n\n";
                response += "Tool ID: " + toolReturned.getInt("tool_id") +
                        "\nTool Name: " + toolReturned.getString("tool_name") +
                        "\nQuantity in Stock: " + toolReturned.getInt("quantity_in_stock") +
                        "\nPrice: " + toolReturned.getDouble("price") +
                        "\nSupplier ID: " + toolReturned.getInt("supplier_id") + "\n\n";
                return response;
            }
            else {
                return "Sorry, tool was not found.\n";
            }
        }
        catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }
    }
}
