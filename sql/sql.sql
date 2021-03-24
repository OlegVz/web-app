CREATE TABLE IF NOT EXISTS product
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name   VARCHAR(50),
    price          BIGINT,
    product_status VARCHAR(50),
    created_at     DATETIME
);

CREATE TABLE IF NOT EXISTS orders
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT,
    status     VARCHAR(50),
    created_at VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users (id)

);

INSERT INTO orders (user_id, status, created_at)
VALUES ();


INSERT INTO product (id, product_name, price, product_status, created_at)
VALUES ();

CREATE TABLE IF NOT EXISTS order_items(
  product_id BIGINT,
  order_id BIGINT,
  quantity BIGINT,
  PRIMARY KEY (product_id, order_id),
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (order_id) REFERENCES orders(id)
);

INSERT INTO order_items (product_id, order_id, quantity)
VALUES ();







