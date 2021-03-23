package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {
    //    private final static String CREATE_USER = "INSERT INTO users (`user_email`, 'password') VALUE (?,?)";
    private final static String CREATE_USER = "insert into users (user_email, password) VALUES (?, ?); ";

    public void createUser(String email, String password, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into users (user_email, password)" +
                            "VALUES (?, ?); ",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, email == null ? "-" : email);
            ps.setString(2, password == null ? "-" : password);
            int i = ps.executeUpdate();
//                if (ps.executeUpdate() > 0) {
//                    try (ResultSet rs = ps.getGeneratedKeys()) {
//                        if (rs.next()) {
//                            user.setId(rs.getLong(1));
//                        }
//                    } catch (SQLException ex) {
//                        System.out.println(ex.getMessage());
//                    }
//                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//        PreparedStatement prepStat = null;
//        try {
//            prepStat = conn.prepareStatement(CREATE_USER);
//            prepStat.setString(1, email);
//            prepStat.setString(2, password);
//            prepStat.execute();
////            prepStat = conn.prepareStatement("SELECT LAST_INSERT_ID()");
//            prepStat.executeQuery();
//        } catch (SQLException ex) {
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
//    }
}
