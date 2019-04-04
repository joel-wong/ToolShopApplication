package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDatabaseTableManager extends DatabaseTableManager {

    public SupplierDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    public ResultSet getAllSuppliers() {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM suppliertable");
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public void addSupplier(int supplierID, String companyName, String address, String salesContact) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "INSERT INTO `toolshopdatabase`.`suppliertable` (`supplier_id`, `company_name`, `address`, `sales_contact`) VALUES (?, ?, ?, ?)");
            statement.setInt(1, supplierID);
            statement.setString(2, companyName);
            statement.setString(3, address);
            statement.setString(4, salesContact);
            writeQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public ResultSet searchSupplier(int supplierID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM suppliertable WHERE supplier_id = ?");
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
