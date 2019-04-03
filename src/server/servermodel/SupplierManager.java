package server.servermodel;

import server.servermodel.database.DatabaseConnectionManager;
import server.servermodel.database.SupplierDatabaseTableManager;
import server.servermodel.database.ToolDatabaseTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierManager {

    private SupplierDatabaseTableManager supplierDatabaseTableManager;

    private ToolDatabaseTableManager toolDatabaseTableManager;

    SupplierManager(DatabaseConnectionManager databaseConnectionManager){
        this.supplierDatabaseTableManager = new SupplierDatabaseTableManager(databaseConnectionManager);
        this.toolDatabaseTableManager = new ToolDatabaseTableManager(databaseConnectionManager);
    }

    String listSuppliers(){
        String supplierListString = "";

        try {
            ResultSet supplierList = supplierDatabaseTableManager.getAllSuppliers();

            while(supplierList.next()) {
                // This could be made much more efficient later, but for now it is okay ---------------------------------------------------------------------------
                supplierListString += "Supplier ID: " + supplierList.getInt("supplier_id") +
                        "\nCompany Name: " + supplierList.getString("company_name") +
                        "\nAddress: " + supplierList.getString("address") +
                        "\nSales Contact: " + supplierList.getString("sales_contact") +
                        "\nTools Supplied: \n\n";;
            }
        } catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }

        return supplierListString;
    }

}
