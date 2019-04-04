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
            ResultSet supplierOfTool;
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
        ResultSet resultSet = toolDatabaseTableManager.searchToolByID(toolID);
        try{
            if(resultSet.next()){
               return "A tool with that ID already exists.";
            }
            else {
                toolDatabaseTableManager.addTool(toolID, toolName, quantityInStock, price, already_pending_order, supplierID);
                return "Tool successfully added.";
            }
        }
        catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }
    }

}
