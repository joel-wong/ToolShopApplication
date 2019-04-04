package server.servermodel;

import server.servermodel.database.AccountDatabaseTableManager;
import server.servermodel.database.DatabaseConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;

class Authenticator {

    private AccountDatabaseTableManager accountDatabaseTableManager;

    Authenticator(DatabaseConnectionManager databaseConnectionManager){
        this.accountDatabaseTableManager = new AccountDatabaseTableManager(databaseConnectionManager);
    }

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