package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Creates and executes PreparedStatements related to searching, adding, deleting, or modifying orderlines in the ToolShop application
 *
 *  @author Joel Wong
 *  @version 1.0
 *  @since April 4, 2019
 */
public class OrderlineDatabaseTableManager extends DatabaseTableManager {

    /** Constructor, used to enforce aggregation relationship to the DatabaseConnectionManager
     *
     * @param databaseConnectionManager A non-null connection to the database
     */
    public OrderlineDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    /** Returns the orderlines that correspond to the tool with the given tool ID.
     *
     * @param toolID The ID of the tool to find an orderline for
     * @return The orderline(s) corresponding to the tool ID
     */
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

    /** Retrieves all orderlines for a given order.
     *
     * @param orderID The ID of the order that the orderlines correspond to
     * @return The orderlines that correspond to the given order
     */
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

    /** Creates a new orderline with the input data
     *
     * @param amount_ordered The amount of the tool ordered
     * @param tool_id The tool ID of the tool that is being ordered
     * @param order_id The ID of the corresponding order
     * @param price The price of the orderline
     */
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
