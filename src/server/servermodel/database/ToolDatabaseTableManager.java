package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToolDatabaseTableManager extends DatabaseTableManager {

    public ToolDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    public ResultSet getAllTools() {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM tooltable ORDER BY tool_id ASC");
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public ResultSet getToolsForSupplier(int supplierID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement("SELECT * FROM tooltable WHERE supplier_id = ?");
            statement.setInt(1, supplierID);
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.exit(-1);
        }
        return null;
    }

    public void addTool(int toolID, String toolName, int quantityInStock, double price, boolean already_pending_order, int supplierID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "INSERT INTO `toolshopdatabase`.`tooltable` (`tool_id`, `tool_name`, `quantity_in_stock`, `price`, `already_pending_order`, `supplier_id`) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, toolID);
            statement.setString(2, toolName);
            statement.setInt(3, quantityInStock);
            statement.setDouble(4, price);
            statement.setBoolean(5, already_pending_order);
            statement.setInt(6, supplierID);
            writeQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public ResultSet searchToolByID(int toolID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM tooltable WHERE tool_id = ?");
            statement.setInt(1, toolID);
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public ResultSet searchToolByName(String toolName) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM tooltable WHERE tool_name = ?");
            statement.setString(1, toolName);
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }
}
