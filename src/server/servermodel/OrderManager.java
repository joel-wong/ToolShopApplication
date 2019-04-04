package server.servermodel;

import server.servermodel.database.*;

public class OrderManager {

    private SupplierDatabaseTableManager supplierDatabaseTableManager;
    private ToolDatabaseTableManager toolDatabaseTableManager;
    private OrderDatabaseTableManager orderDatabaseTableManager;
    private OrderlineDatabaseTableManager orderlineDatabaseTableManager;

    OrderManager(DatabaseConnectionManager databaseConnectionManager){
        this.supplierDatabaseTableManager = new SupplierDatabaseTableManager(databaseConnectionManager);
        this.toolDatabaseTableManager = new ToolDatabaseTableManager(databaseConnectionManager);
        this.orderDatabaseTableManager = new OrderDatabaseTableManager(databaseConnectionManager);
        this.orderlineDatabaseTableManager = new OrderlineDatabaseTableManager(databaseConnectionManager);
    }
}
