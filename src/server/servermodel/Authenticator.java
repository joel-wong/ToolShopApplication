package server.servermodel;

import server.servermodel.database.AccountDatabaseTableManager;
import server.servermodel.database.DatabaseConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Authenticates users using the AccountDatabaseTableManager based on the input username and password.
 *
 *  @author Joel Wong
 *  @version 1.0
 *  @since April 4, 2019
 */

class Authenticator {

    /**
     * Used to authenticate via the database
     */
    private AccountDatabaseTableManager accountDatabaseTableManager;

    /** Constructor, takes in the databaseConnectionManager and creates an accountDatabaseTableManager
     *
     * @param databaseConnectionManager Used to create the accountDatabaseTableManager
     */
    Authenticator(DatabaseConnectionManager databaseConnectionManager){
        this.accountDatabaseTableManager = new AccountDatabaseTableManager(databaseConnectionManager);
    }

    /** Takes in a username and password and checks if that username and password is in the database
     *
     * @param username The username of the user
     * @param password The password of the user
     * @return The result of the authentication as a String ("true") or ("false")
     */
    String authenticate(String username, String password) {
        ResultSet account = accountDatabaseTableManager.authenticate(username, password);
        try{
            if(account.next()){
                return "true";
            }
            else {
                return "false";
            }
        }
        catch(SQLException e) {
            return "Error in SQL. Please contact the developers for more details";
        }
    }
}