package server.servermodel;

import server.servermodel.database.*;

import java.sql.ResultSet;
import java.sql.SQLException;

class ToolManager implements Constants {

    private SupplierDatabaseTableManager supplierDatabaseTableManager;

    private ToolDatabaseTableManager toolDatabaseTableManager;

    private OrderlineDatabaseTableManager orderlineDatabaseTableManager;

    private OrderDatabaseTableManager orderDatabaseTableManager;

    private Date date;

    ToolManager(DatabaseConnectionManager databaseConnectionManager, Date date){
        this.supplierDatabaseTableManager = new SupplierDatabaseTableManager(databaseConnectionManager);
        this.toolDatabaseTableManager = new ToolDatabaseTableManager(databaseConnectionManager);
        this.orderDatabaseTableManager = new OrderDatabaseTableManager(databaseConnectionManager);
        this.orderlineDatabaseTableManager = new OrderlineDatabaseTableManager(databaseConnectionManager);
        this.date = date;
    }

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

    String searchToolByID(int toolID){
        if (toolID < 1) {
            return "Tool ID must be positive.\n";
        }
        ResultSet toolReturned = toolDatabaseTableManager.searchToolByID(toolID);
        return getToolDetailsAsString(toolReturned);
    }

    String searchToolByName(String toolName) {
        ResultSet toolReturned = toolDatabaseTableManager.searchToolByName(toolName);
        return getToolDetailsAsString(toolReturned);
    }

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
