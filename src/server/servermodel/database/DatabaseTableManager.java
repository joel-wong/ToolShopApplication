package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** Parent class of various other databaseTableManagers. Has helper functions that can be used to communicate with the database.
 *
 *  @author Joel Wong
 *  @version 1.0
 *  @since April 4, 2019
 */

abstract class DatabaseTableManager {

    /**
     * The databaseConnectionManager used to connect to the database
     */
    DatabaseConnectionManager databaseConnectionManager;

    /** Constructor, used to enforce aggregation relationship to the DatabaseConnectionManager
     *
     * @param databaseConnectionManager A non-null connection to the database
     */
    DatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        this.databaseConnectionManager = databaseConnectionManager;
    }

    /** Makes a SELECT query through the databaseConnectionManager
     *
     * @param preparedStatement A SELECT query written as a prepared statement with no wildcards
     * @return The ResultSet returned by the database
     */
    ResultSet readQuery(PreparedStatement preparedStatement) {
        return databaseConnectionManager.readQuery(preparedStatement);
    }

    /** Makes an INSERT query through the databaseConnectionManager
     *
     * @param preparedStatement An INSERT query written as a prepared statement with no wildcards
     */
    void insertQuery(PreparedStatement preparedStatement) {
        databaseConnectionManager.insertQuery(preparedStatement);
    }

    /** Makes a DELETE query through the databaseConnectionManager
     *
     * @param preparedStatement A DELETE query written as a prepared statement with no wildcards
     */
    void deleteQuery(PreparedStatement preparedStatement) {
        databaseConnectionManager.deleteQuery(preparedStatement);
    }

    /** Makes an UPDATE query through the databaseConnectionManager
     *
     * @param preparedStatement An UPDATE query written as a prepared statement with no wildcards
     */
    void updateQuery(PreparedStatement preparedStatement){
        databaseConnectionManager.updateQuery(preparedStatement);
    }
}
