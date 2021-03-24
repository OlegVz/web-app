package app.repositry;

import app.entities.Product;
import app.entities.ProductStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.repositry.DBConstants.*;

public class ProductRepository {

    public ProductRepository() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "CREATE TABLE IF NOT EXISTS product (\n" +
                "    id          BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    product_name VARCHAR(50),\n" +
                "    price          BIGINT,\n" +
                "    product_status VARCHAR(50),\n" +
                "    created_at DATETIME\n" +
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

    public Product save(Product product) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO product (product_name, price, product_status, created_at)\n" +
                            "VALUES (?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, product.getName());
            ps.setLong(2, product.getPrice());
            ps.setString(3, product.getProductStatus().toString());
            ps.setString(4, product.getCreatedAt().toString());

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        product.setId(rs.getLong(1));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public Product deleteProductById(Long id) {
        String sql = "delete from product where id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setLong(1, id);
            Product byID = findByID(id);
            if (ps.executeUpdate() == 1) {
                return byID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Product findByID(Long id) {
        String sql = "select * from product where id = ?";
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

    public List<Product> list() {
        String sql = "select * from product ";
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

    private List<Product> rsToEntityList(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            Product product = rsToEntity(rs);
            products.add(product);
        }

        return products;
    }

    private Product rsToEntity(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setId(rs.getLong(1));
        product.setName(rs.getString(2));
        product.setPrice(rs.getLong(3));
        product.setProductStatus(ProductStatus.valueOf(rs.getString(4)));
        product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        return product;
    }
}
