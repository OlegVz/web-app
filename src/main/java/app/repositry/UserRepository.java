package app.repositry;

import app.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.repositry.DBConstants.*;

public class UserRepository {
    public UserRepository() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "CREATE TABLE IF NOT EXISTS users\n" +
                "(\n" +
                "    id          BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    user_email        VARCHAR(254),\n" +
                "    password     VARCHAR(23)\n" +
                ");";
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public User save(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into users (user_email, password)" +
                            "VALUES (?, ?); ",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, email == null ? "-" : email);
            ps.setString(2, password == null ? "-" : password);

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getLong(1));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }

    public User update(User user) {
        String sql = "UPDATE student\n" +
                "SET user_email = ?,\n" +
                "password = ?\n" +
                "WHERE id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setLong(3, user.getId());
            ps.executeUpdate();

            return findByID(user.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User deleteUserById(Long id) {
        String sql = "delete from users where id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setLong(1, id);
            User byID = findByID(id);
            if (ps.executeUpdate() == 1) {
                return byID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User findByID(Long id) {
        String sql = "select * from users where id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rsToEntity(rs);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return null;
    }

    public List<User> list() {
        String sql = "select * from users ";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet rs = statement.executeQuery();

            return rsToEntityList(rs);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<User> rsToEntityList(ResultSet rs) throws SQLException {
        List<User> students = new ArrayList<>();
        while (rs.next()) {
            User user = rsToEntity(rs);
            students.add(user);
        }

        return students;
    }

    private User rsToEntity(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getLong(1));
        user.setEmail(rs.getString(2));

        return user;
    }
}
