package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Creates and executes PreparedStatements related to searching, adding, deleting, or modifying tools in the ToolShop application
 *
 *  @author Joel Wong
 *  @version 1.0
 *  @since April 4, 2019
 */

public class ToolDatabaseTableManager extends DatabaseTableManager {

    /** Constructor, used to enforce aggregation relationship to the DatabaseConnectionManager
     *
     * @param databaseConnectionManager A non-null connection to the database
     */
    public ToolDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    /** Retrieves all tools from the database
     *
     * @return A list of all tools in the database
     */
    public ResultSet getAllTools() {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM toolshopdatabase.tooltable ORDER BY tool_id ASC");
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
        }
        return null;
    }

    /** Returns the tools correesponding to a given Supplier
     *
     * @param supplierID The ID of the supplier that has the desired tools
     * @return The tools corresponding to the supplierID, or none if there is no such supplier
     */
    public ResultSet getToolsForSupplier(int supplierID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement("SELECT * FROM tooltable WHERE supplier_id = ?");
            statement.setInt(1, supplierID);
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
        }
        return null;
    }

    /** Inserts a new tool into the database. Assumes that the tool ID does not already correspond to a tool.
     *
     * @param toolID The ID of the tool
     * @param toolName The name of the tool
     * @param quantityInStock The quantity of the tool in stock
     * @param price The price of the tool as a double
     * @param already_pending_order Whether there is an orderline corresponding to the tool
     * @param supplierID The ID of the supplier
     */
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
            insertQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
        }
    }

    /** Deletes a tool from the database based on the tool ID.
     *
     * @param toolID The ID of the tool to be deleted.
     */
    public void deleteTool(int toolID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "DELETE FROM tooltable WHERE tool_id = ?");
            statement.setInt(1, toolID);
            deleteQuery(statement);
        }
        catch(SQLException e){
            System.err.print("Invalid statement");
            System.err.println(e.getMessage());
        }
    }

    /** Retrieves a tool corresponding to the input tool ID from the database.
     *
     * @param toolID The ID of the tool
     * @return The tool that corresponds to the tool ID
     */
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

    /** Updates the quantity of a tool given its tool ID and updated quantity.
     *
     * @param toolID The ID of the tool to update
     * @param newQuantity The quantity of the tool after the update
     */
    public void changeToolQuantity(int toolID, int newQuantity) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "UPDATE `toolshopdatabase`.`tooltable` SET `quantity_in_stock` = ? WHERE (`tool_id` = ?);");
            statement.setInt(1, newQuantity);
            statement.setInt(2, toolID);
            updateQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    /** Retrieves tools that have a given name. Case sensitive.
     *
     * @param toolName The name of the tool to search for
     * @return The tool or tools that have the given tool name
     */
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
