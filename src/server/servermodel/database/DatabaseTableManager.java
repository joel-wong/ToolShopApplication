package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DatabaseTableManager {

    DatabaseConnectionManager databaseConnectionManager;

    DatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        this.databaseConnectionManager = databaseConnectionManager;
    }

    ResultSet readQuery(PreparedStatement preparedStatement) {
        return databaseConnectionManager.readQuery(preparedStatement);
    }

    void writeQuery(PreparedStatement preparedStatement) {
        databaseConnectionManager.writeQuery(preparedStatement);
    }
}
