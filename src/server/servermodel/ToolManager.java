package server.servermodel;

import server.servermodel.database.DatabaseConnectionManager;
import server.servermodel.database.SupplierDatabaseTableManager;
import server.servermodel.database.ToolDatabaseTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToolManager {

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
        ResultSet toolReturned = toolDatabaseTableManager.searchToolByID(toolID);
        return getToolDetailsAsString(toolReturned);
    }

    String searchToolByName(String toolName) {
        ResultSet toolReturned = toolDatabaseTableManager.searchToolByName(toolName);
        return getToolDetailsAsString(toolReturned);
    }

    String getToolQuantity(int toolID) {
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
