package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Creates and executes PreparedStatements related to searching, adding, deleting, or modifying suppliers in the ToolShop application
 *
 *  @author Joel Wong
 *  @version 1.0
 *  @since April 4, 2019
 */
public class SupplierDatabaseTableManager extends DatabaseTableManager {

    /** Constructor, used to enforce aggregation relationship to the DatabaseConnectionManager
     *
     * @param databaseConnectionManager A non-null connection to the database
     */
    public SupplierDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    /** Retrieves all supplier from the database
     *
     * @return The ResultSet containing all suppliers
     */
    public ResultSet getAllSuppliers() {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM toolshopdatabase.suppliertable ORDER BY supplier_id ASC");
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement in getAllSuppliers");
            System.err.println(e.getMessage());
        }
        return null;
    }

    /** Inserts a new supplier into the database with the given data fields
     *
     * @param supplierID The ID of the supplier
     * @param companyName The name of the supplier
     * @param address The address of the supplier
     * @param salesContact The name of the person to contact for the supplier
     */
    public void addSupplier(int supplierID, String companyName, String address, String salesContact) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "INSERT INTO `toolshopdatabase`.`suppliertable` (`supplier_id`, `company_name`, `address`, `sales_contact`) VALUES (?, ?, ?, ?)");
            statement.setInt(1, supplierID);
            statement.setString(2, companyName);
            statement.setString(3, address);
            statement.setString(4, salesContact);
            insertQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    /** Retrieves the supplier corresponding to the given supplier ID
     *
     * @param supplierID The ID of the supplier
     * @return The supplier corresponding to the supplier ID, or an empty ResultSet if there is no such supplier.
     */
    public ResultSet searchSupplier(int supplierID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM `toolshopdatabase`.`suppliertable` WHERE supplier_id = ?");
            statement.setInt(1, supplierID);
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
