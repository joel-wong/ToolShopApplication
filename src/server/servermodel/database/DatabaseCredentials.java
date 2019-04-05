package server.servermodel.database;

/**
 * Interface with database credentials
 * @author Wenjia Yang and Joel Wong
 * @version 1.0
 * @since April 5, 2019
 */
public interface DatabaseCredentials {

    /** database URL */
    static final String DB_URL = "jdbc:mysql://localhost/toolshopdatabase";
    /**
     * USERNAME of database root user
     */
    static final String USERNAME = "root";
    /**
     * PASSWORD of database root user
     */
    static final String PASSWORD = "admin";

}
