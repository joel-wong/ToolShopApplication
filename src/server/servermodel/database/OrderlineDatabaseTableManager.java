package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderlineDatabaseTableManager extends DatabaseTableManager {

    public OrderlineDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    public ResultSet searchOrderlineByToolID (int toolID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM orderlinetable WHERE tool_id = ?");
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

    public ResultSet searchOrderlineByOrderID(int orderID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM orderlinetable WHERE order_id = ? ORDER BY tool_id ASC");
            statement.setInt(1, orderID);
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public void createOrderLine(int amount_ordered, int tool_id, int order_id, double price){
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "INSERT INTO `toolshopdatabase`.`orderlinetable` (`amount_ordered`, `tool_id`, `order_id`, `price`) VALUES (?, ?, ?, ?)");
            statement.setInt(1, amount_ordered);
            statement.setInt(2, tool_id);
            statement.setInt(3, order_id);
            statement.setDouble(4, price);
            insertQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
