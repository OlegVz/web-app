package app.repositry;

import app.entities.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.repositry.DBConstants.*;

public class OrderItemRepository {
    public OrderItemRepository() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "CREATE TABLE IF NOT EXISTS order_items(\n" +
                "  product_id BIGINT,\n" +
                "  order_id BIGINT,\n" +
                "  quantity BIGINT,\n" +
                "  PRIMARY KEY (product_id, order_id),\n" +
                "  FOREIGN KEY (product_id) REFERENCES product(id),\n" +
                "  FOREIGN KEY (order_id) REFERENCES orders(id)\n" +
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

    public OrderItem save(OrderItem orderItem) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO order_items (product_id, order_id, quantity)\n" +
                            "VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setLong(1, orderItem.getProductId());
            ps.setLong(2, orderItem.getOrderId());
            ps.setInt(3, orderItem.getQuantity());
            if (ps.executeUpdate() > 0) {
                return orderItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public OrderItem deleteOrderItemById(Long productId, Long orderId) {
        String sql = "DELETE\n" +
                "FROM order_items\n" +
                "WHERE order_id = ?\n" +
                "  AND product_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setLong(1, orderId);
            ps.setLong(2, productId);
            OrderItem byID = findByID(productId, orderId);
            if (ps.executeUpdate() == 1) {
                return byID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public OrderItem findByID(Long productId, Long orderId) {
        String sql = "SELECT *\n" +
                "FROM order_items\n" +
                "WHERE product_id = ?\n" +
                "  AND order_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setLong(1, productId);
            ps.setLong(2, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rsToEntity(rs);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return null;
    }

    public List<OrderItem> list() {
        String sql = "select * from order_items ";
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

    private List<OrderItem> rsToEntityList(ResultSet rs) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        while (rs.next()) {
            OrderItem orderItem = rsToEntity(rs);
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    private OrderItem rsToEntity(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();

        orderItem.setProductId(rs.getLong(1));
        orderItem.setOrderId(rs.getLong(2));
        orderItem.setQuantity(rs.getInt(3));

        return orderItem;
    }
}
