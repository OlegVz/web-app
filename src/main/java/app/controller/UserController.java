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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
