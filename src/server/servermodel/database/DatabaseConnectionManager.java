package server.servermodel.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

/** Connects to the database and executes queries (PreparedStatements). Ensures all statements are valid before executing.
 *
 *  @author Joel Wong
 *  @version 1.0
 *  @since April 4, 2019
 */

public class DatabaseConnectionManager implements DatabaseCredentials {

    /**
     * Used to connect to the database
     */
    private Connection connection;

    /**
     * Constructor. Initializes connection to database based on values in DatabaseCredentials.java
     */
    public DatabaseConnectionManager(){
        initializeConnection();
    }

    /**
     * Initializes connection to database based on values in DatabaseCredentials.java. Registers the drivers and exits the program if there is an error connecting.
     */
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

    /** Performs a SELECT query on the database based on the input.
     *
     * @param preparedStatement A query that will be executed to read data from the table (must be a SELECT query)
     * @return The ResultSet returned by the query to the database
     */
    ResultSet readQuery(PreparedStatement preparedStatement) {
        try {
            return preparedStatement.executeQuery();
        }
        catch(SQLException e) {
            System.err.println("Error when reading from database");
            System.err.println(e.getMessage());;
        }
        return null;
    }

    /** Performs an INSERT query on the database based on the input.
     *
     * @param preparedStatement A query that will be executed to add a new row to a table (must be an INSERT query)
     */
    void insertQuery(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.err.println("Error when inserting into database");
            System.err.println(e.getMessage());;
        }
    }

    /** Performs a DELETE query on the database based on the input.
     *
     * @param preparedStatement A query that will be executed to delete a row from the table (must be a DELETE query)
     */
    void deleteQuery(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.err.println("Error when deleting from database");
            System.err.println(e.getMessage());;
        }
    }

    /** Performs an UPDATEquery on the database based on the input.
     *
     * @param preparedStatement A query that will be executed to update one or more rows in the table (must be an UPDATE query)
     */
    void updateQuery(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.err.println("Error when updating database");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Closes the connection to the database.
     */
    private void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Returns the connection to the database to be used when creating preparedStatements
     *
     * @return The connection to the database, which can be used to create preparedStatements
     */
    Connection getConnection() {
        return connection;
    }
}
