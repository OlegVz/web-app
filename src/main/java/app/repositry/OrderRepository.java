package app.repositry;

import app.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.repositry.DBConstants.*;

public class OrderRepository {

    public OrderRepository() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "CREATE TABLE IF NOT EXISTS orders\n" +
                "(\n" +
                "    id         BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    user_id    BIGINT,\n" +
                "    status     VARCHAR(50),\n" +
                "    created_at VARCHAR(50),\n" +
                "    FOREIGN KEY (user_id) REFERENCES users (id)\n" +
                "\n" +
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

    public Order save(Order order) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO orders (user_id, status, created_at)\n" +
                            "VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setLong(1, order.getUserId());
            ps.setString(2, order.getStatus());
            ps.setString(3, order.getCreatedAt());

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        order.setId(rs.getLong(1));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return order;
    }

    public Order deleteOrderById(Long id) {
        String sql = "delete from orders where id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setLong(1, id);
            Order byID = findByID(id);
            if (ps.executeUpdate() == 1) {
                return byID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Order findByID(Long id) {
        String sql = "select * from orders where id = ?";
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

    public List<Order> list() {
        String sql = "select * from orders ";
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

    private List<Order> rsToEntityList(ResultSet rs) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            Order order = rsToEntity(rs);
            orders.add(order);
        }

        return orders;
    }

    private Order rsToEntity(ResultSet rs) throws SQLException {
        Order order = new Order();

        order.setId(rs.getLong(1));
        order.setUserId(rs.getLong(2));
        order.setStatus(rs.getString(3));
        order.setCreatedAt(rs.getString(3));

        return order;
    }
}
