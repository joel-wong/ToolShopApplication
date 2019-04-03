package server.servermodel.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDatabaseTableManager extends DatabaseTableManager {

    public SupplierDatabaseTableManager(DatabaseConnectionManager databaseConnectionManager) {
        super(databaseConnectionManager);
    }

    public ResultSet getAllSuppliers() {
        try {
            PreparedStatement statement = databaseConnectionManager.getConnection().prepareStatement("SELECT * FROM suppliertable");
            return readQuery(statement);
        }
        catch(SQLException e){
            System.err.println("Invalid statement");
            System.exit(-1);
        }
        return null;
    }

    //    public void insertUser() {
//        try {
//            Statement stmt = conn.createStatement();
//            String insert = "INSERT INTO STUDENT (ID, first, last) values (12, 'Joe','Jones')";
//            int rowCount = stmt.executeUpdate(insert);
//            System.out.println("row Count = " + rowCount);
//            stmt.close();
//        } catch (SQLException e) {
//            System.out.println("problem inserting user");
//            e.printStackTrace();
//        }
//    }
//
//    public void insertUserPreparedStatement(int id, String fName, String lName) {
//        try {
//            String query = "INSERT INTO STUDENT (ID,first , last) values(?,?,?)";
//            PreparedStatement pStat = conn.prepareStatement(query);
//            pStat.setInt(1, id);
//            pStat.setString(2, fName);
//            pStat.setString(3, lName);
//            int rowCount = pStat.executeUpdate();
//            System.out.println("row Count = " + rowCount);
//            pStat.close();
//        } catch (SQLException e) {
//            System.out.println("problem inserting user");
//            e.printStackTrace();
//        }
//    }
//
//    public void createTable() {
//        String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
//                + " last VARCHAR(255), " + " PRIMARY KEY ( id ))";
//
//        try {
//            Statement stmt = conn.createStatement(); // construct a statement
//            stmt.executeUpdate(sql); // execute my query (i.e. sql)
//            stmt.close();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            System.out.println("Table can NOT be created!");
//        }
//        System.out.println("Created table in given database...");

}
