package server.servermodel;

import server.servermodel.database.DatabaseConnectionManager;
import server.servermodel.database.SupplierDatabaseTableManager;
import server.servermodel.database.ToolDatabaseTableManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to manage a list of Suppliers in a Tool Shop application.
 * @author Wenjia Yang and Joel Wong
 * @version 1.0
 * @since April 5, 2019
 */
class SupplierManager {
	/**
	 * Supplier Database Table Manager
	 */
    private SupplierDatabaseTableManager supplierDatabaseTableManager;
    /**
     * Tool Database Table Manager
     */
    private ToolDatabaseTableManager toolDatabaseTableManager;

    /**
     * Constructs a SupplierManager object with the specified DatabaseConnectionManager.
     * @param databaseConnectionManager is the specified DatabaseConnectionManager
     */
    SupplierManager(DatabaseConnectionManager databaseConnectionManager){
        this.supplierDatabaseTableManager = new SupplierDatabaseTableManager(databaseConnectionManager);
        this.toolDatabaseTableManager = new ToolDatabaseTableManager(databaseConnectionManager);
    }

    /**
     * Returns a list of the Suppliers and corresponding Supplier details in the shop application in String form.
     * @return the list of Suppliers
     */
    String listSuppliers(){
        String supplierListString = "";

        try {
            ResultSet supplierList = supplierDatabaseTableManager.getAllSuppliers();
            ResultSet toolsForSupplier;
            while(supplierList.next()) {
                // This could be made much more efficient later, but for now it is okay ---------------------------------------------------------------------------
                supplierListString += "Supplier ID: " + supplierList.getInt("supplier_id") +
                        "\nCompany Name: " + supplierList.getString("company_name") +
                        "\nAddress: " + supplierList.getString("address") +
                        "\nSales Contact: " + supplierList.getString("sales_contact") +
                        "\nTools Supplied: ";

                toolsForSupplier = toolDatabaseTableManager.getToolsForSupplier(supplierList.getInt("supplier_id"));
                while (toolsForSupplier.next()) {
                    supplierListString += "-" + toolsForSupplier.getString("tool_name") + " ";
                }
                supplierListString += "\n\n";
            }
        } catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }

        return supplierListString;
    }

    /**
     * Adds a new Supplier with the specified parameters to the shop application.
     * @param supplierID is the new Supplier ID number
     * @param companyName is the new Supplier's company name
     * @param address is the new Supplier's address
     * @param salesContact is the new Supplier's sales contact name
     * @return the request's resulting message
     */
    String addSupplier(int supplierID, String companyName, String address, String salesContact) {
        if (supplierID < 1) {
            return "The supplier ID must be positive.\n";
        }
        ResultSet resultSet = supplierDatabaseTableManager.searchSupplier(supplierID);
        try{
            if(resultSet.next()){
               return "A supplier with that ID already exists.";
            }
            else {
                supplierDatabaseTableManager.addSupplier(supplierID, companyName, address, salesContact);
                return "Supplier successfully added.";
            }
        }
        catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }
    }

}
