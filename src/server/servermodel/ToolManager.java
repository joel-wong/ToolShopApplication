package server.servermodel;

import server.servermodel.database.DatabaseConnectionManager;
import server.servermodel.database.SupplierDatabaseTableManager;
import server.servermodel.database.ToolDatabaseTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToolManager implements Constants {

    private SupplierDatabaseTableManager supplierDatabaseTableManager;

    private ToolDatabaseTableManager toolDatabaseTableManager;

    ToolManager(DatabaseConnectionManager databaseConnectionManager){
        this.supplierDatabaseTableManager = new SupplierDatabaseTableManager(databaseConnectionManager);
        this.toolDatabaseTableManager = new ToolDatabaseTableManager(databaseConnectionManager);
    }

    String listTools(){
        String toolListString = "";

        try {
            ResultSet toolList = toolDatabaseTableManager.getAllTools();
            while(toolList.next()) {
                // This could be made much more efficient later, but for now it is okay ---------------------------------------------------------------------------
                toolListString += "Tool ID: " + toolList.getInt("supplier_id") +
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
                return "Quantity:" + toolReturned.getInt("quantity_in_stock") + "\n";
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
//                        CREATE ORDER HERE
                        return "New quantity is " + newQuantity + ". An order has been generated.";
                    }
                    else {
                        return "New quantity is " + newQuantity;
                    }
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
                response += "Tool ID: " + toolReturned.getInt("supplier_id") +
                        "\nTool Name: " + toolReturned.getString("tool_name") +
                        "\nQuantity in Stock: " + toolReturned.getInt("quantity_in_stock") +
                        "\nPrice: " + toolReturned.getDouble("price") +
                        "\nSupplier ID: " + toolReturned.getInt("supplier_id") + "\n\n";;
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
