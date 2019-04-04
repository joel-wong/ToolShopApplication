package server.servermodel.database;

import com.mysql.cj.protocol.Resultset;
import server.servermodel.DatabaseCredentials;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class DatabaseConnectionManager implements DatabaseCredentials {

    // Attributes
    private Connection connection;

    public DatabaseConnectionManager(){
        setupDatabaseManager();
    }

    private void initializeConnection() {
        try {
            // Register JDBC driver
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            // Open a connectionection
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Problem");
            e.printStackTrace();
            System.exit(-1);
        }

    }

    ResultSet readQuery(PreparedStatement preparedStatement) {
        try {
            return preparedStatement.executeQuery();
        }
        catch(SQLException e) {
            System.err.println("Error when reading from database");
            System.err.println(e.getMessage());;
            System.exit(-1);
        }
        return null;
    }

    void insertQuery(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.err.println("Error when writing to database");
            System.err.println(e.getMessage());;
            System.exit(-1);
        }
    }

    void updateQuery(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.err.println("Error when updating database");
            System.err.println(e.getMessage());;
            System.exit(-1);
        }
    }

    private void close() {
        try {
            // rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setupDatabaseManager(){
        initializeConnection();
    }

    void closeDatabaseManager(){
        close();
    }
    
    Connection getConnection() {
        return connection;
    }
}
