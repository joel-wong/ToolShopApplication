package server.servermodel;


public interface DatabaseCredentials {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/toolshopdatabase";

    //  Database credentials
    static final String USERNAME = "root";
    static final String PASSWORD = "admin";

}
