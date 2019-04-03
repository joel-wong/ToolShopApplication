package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToolDatabaseTableManager extends DatabaseTableManager {

    public ToolDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    public ResultSet getToolsForSupplier(int supplierID) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement("SELECT * FROM tooltable WHERE supplier_id = ?");
            statement.setInt(1, supplierID);
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.exit(-1);
        }
        return null;
    }

}
