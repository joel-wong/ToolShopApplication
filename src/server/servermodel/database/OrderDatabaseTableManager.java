package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Creates and executes PreparedStatements related to searching, adding, deleting, or modifying orders in the ToolShop application
 *
 *  @author Joel Wong
 *  @version 1.0
 *  @since April 4, 2019
 */

public class OrderDatabaseTableManager extends DatabaseTableManager {

    /** Constructor, used to enforce aggregation relationship to the DatabaseConnectionManager
     *
     * @param databaseConnectionManager A non-null connection to the database
     */
    public OrderDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    /** Retrieves all orders in the database
     *
     * @return All orders in the database as a ResultSet
     */
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

    /** Retrieves an order from the database based on the input day, month, and year
     *
     * @param day The day of the order
     * @param month The month of the order (case sensitive)
     * @param year The year of the order
     * @return The ResultSet containing the order if the order exists or an empty ResultSet otherwise
     */
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

    /** Creates a new order with the input day, month, and year.
     *
     * @param day The day of the new order
     * @param month The month of the new order
     * @param year The year of the new order
     */
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
