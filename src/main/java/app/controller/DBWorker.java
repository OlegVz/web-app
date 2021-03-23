package app.controller;

import app.entities.ErrorObj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/hybrisDB";
    public static final String USER = "hybris";
    public static final String PASSWORD = "Hybris1#";

    ErrorObj eo = new ErrorObj();

    public DBWorker() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hybrisDB?user=hybris&password=Hybris1#");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);;
        } catch (SQLException ex) {
             eo.setConSQLError(true);
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
