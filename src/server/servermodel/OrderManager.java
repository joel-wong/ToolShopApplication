package server.servermodel;

import server.servermodel.database.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to manage an Order list in a Tool Shop application.
 * @author Wenjia Yang and Joel Wong
 * @version 1.0
 * @since April 5, 2019
 */
class OrderManager implements Constants {
	/**
	 * Supplier Database Table Manager
	 */
    private SupplierDatabaseTableManager supplierDatabaseTableManager;
    /**
     * Tool Database Table Manager
     */
    private ToolDatabaseTableManager toolDatabaseTableManager;
    /**
     * Order Database Table Manager
     */
    private OrderDatabaseTableManager orderDatabaseTableManager;
    /**
     * Orderline Database Table Manager
     */
    private OrderlineDatabaseTableManager orderlineDatabaseTableManager;
    /**
     * Constructs a OrderManager object with the specified DatabaseConnectionManager.
     * @param databaseConnectionManager is the specified DatabaseConnectionManager
     */
    OrderManager(DatabaseConnectionManager databaseConnectionManager){
        this.supplierDatabaseTableManager = new SupplierDatabaseTableManager(databaseConnectionManager);
        this.toolDatabaseTableManager = new ToolDatabaseTableManager(databaseConnectionManager);
        this.orderDatabaseTableManager = new OrderDatabaseTableManager(databaseConnectionManager);
        this.orderlineDatabaseTableManager = new OrderlineDatabaseTableManager(databaseConnectionManager);
    }
    /**
     * Returns a list of the Orders and corresponding Order details in the shop application in String form.
     * @return the list of Orders
     */
    String listOrders() {
        ResultSet orderList = orderDatabaseTableManager.getAllOrders();

        String stringToReturn = "List of Orders:\n\n";

        try {
            ResultSet orderlinesForOrder;
            ResultSet tool;
            ResultSet supplier;
            int orderID, toolID, supplierID;
            String toolName, suppliereName;
            double total_order_price;

            while (orderList.next()) {
                orderID = orderList.getInt("order_id");
                orderlinesForOrder = orderlineDatabaseTableManager.searchOrderlineByOrderID(orderID);
                String orderListAsString = "";

                stringToReturn += "ORDER ID: " + orderID + "\n";
                stringToReturn += "Date Ordered: " + orderList.getString("order_month") + " " + orderList.getInt("order_day") + ", " + orderList.getInt("order_year") + "\n";

                total_order_price = 0;
                while (orderlinesForOrder.next()) {
                    toolID = orderlinesForOrder.getInt("tool_id");
                    tool = toolDatabaseTableManager.searchToolByID(toolID);
                    tool.next();
                    supplierID = tool.getInt("supplier_id");
                    supplier = supplierDatabaseTableManager.searchSupplier(supplierID);
                    supplier.next();

                    orderListAsString += "Item Description: " + tool.getString("tool_name") + "\n";
                    orderListAsString += "Amount Ordered: " + orderlinesForOrder.getInt("amount_ordered") + "\n";
                    orderListAsString += "Supplier Name: " + supplier.getString("company_name") + "\n\n";

                    total_order_price += orderlinesForOrder.getDouble("price");
                }

                stringToReturn += "Total price: " + String.format("%.2f", total_order_price) + "\n\n";

                stringToReturn += orderListAsString;

                stringToReturn += lineOfEquals + "\n\n";
            }
        } catch (SQLException e) {
            System.err.println("Error parsing orders");
            System.err.println(e.getMessage());
            return "Error! Please contact the developers.";
        }
        return stringToReturn;
    }
}
