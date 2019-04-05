package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/** Creates and executes PreparedStatements related to authentication in the ToolShop application
 *
 *  @author Joel Wong
 *  @version 1.0
 *  @since April 4, 2019
 */
public class AccountDatabaseTableManager extends DatabaseTableManager {

    /** Constructor, used to enforce aggregation relationship to the DatabaseConnectionManager
     *
     * @param databaseConnectionManager A non-null connection to the database
     */
    public AccountDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    /** Retrieves the list of users that have the given username and password
     *
     * @param username The username, as a String
     * @param password password of the user, input as a String
     * @return The user that corresponds to the username and password, if one exists, or an empty REsultSet otherwise
     */
    public ResultSet authenticate(String username, String password) {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement(
                    "SELECT * FROM accounttable WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
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
