package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDatabaseTableManager extends DatabaseTableManager {

    public OrderDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    public ResultSet getAllOrders() {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM ordertable ORDER BY order_id ASC");
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public ResultSet getOrderByDate(int day, String month, int year) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM ordertable WHERE order_day = ? AND order_month = ? AND order_year = ?");
            statement.setInt(1, day);
            statement.setString(2, month);
            statement.setInt(3, year);
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public void createOrder(int day, String month, int year) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "INSERT INTO `toolshopdatabase`.`ordertable` (`order_day`, `order_month`, `order_year`) VALUES (?, ?, ?)");
            statement.setInt(1, day);
            statement.setString(2, month);
            statement.setInt(3, year);
            insertQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
