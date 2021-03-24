CREATE TABLE IF NOT EXISTS product (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(50),
    price          BIGINT,
    product_status VARCHAR(50),
    created_at DATETIME
);

INSERT INTO product (id, product_name, price, product_status, created_at)
VALUES ();
