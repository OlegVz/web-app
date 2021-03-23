//package app.repositry;
//
//import app.entities.User;
//
//import java.sql.*;
//import java.util.List;
//
//import static app.repositry.DBConstants.*;
//
//public class UserRepository {
//    public UserRepository() {
//        try {
//            Class.forName(JDBC_DRIVER);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        String sql = "CREATE TABLE IF NOT EXISTS users\n" +
//                "(\n" +
//                "    id          BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
//                "    user_email        VARCHAR(254),\n" +
//                "    password     VARCHAR(23)\n" +
//                ");";
//        try (
//                Connection connection = getConnection();
//                Statement statement = connection.createStatement()
//        ) {
//            statement.execute(sql);
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    private Connection getConnection() throws SQLException {
////        try {
////            Class.forName("com.mysql.jdbc.Driver");
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        }
//
//        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
//    }
//
//    public List<User> save(List<User> users) {
//        for (User user : users) {
//            try (Connection connection = getConnection()) {
//                PreparedStatement ps = connection.prepareStatement(
//                        "insert into users (user_email, password)" +
//                                "VALUES (?, ?); ",
//                        Statement.RETURN_GENERATED_KEYS
//                );
//                ps.setString(1, user.getEmail() == null ? "-" : user.getEmail());
//                ps.setString(2, user.getPassword() == null ? "-" : user.getPassword());
//
//                if (ps.executeUpdate() > 0) {
//                    try (ResultSet rs = ps.getGeneratedKeys()) {
//                        if (rs.next()) {
//                            user.setId(rs.getLong(1));
//                        }
//                    } catch (SQLException ex) {
//                        System.out.println(ex.getMessage());
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return users;
//    }
//
//    public String doPrint() {
//        return "str";
//    }
//}
