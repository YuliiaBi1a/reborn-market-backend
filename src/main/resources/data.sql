--User
INSERT INTO users (id, email, username, password) VALUES (1, 'email@example.com', 'user', 'password');
--Category
INSERT INTO categories (id, name) VALUES (1, 'CLOTHING');
INSERT INTO categories (id, name) VALUES (2, 'FOOTWEAR');
INSERT INTO categories (id, name) VALUES (3, 'TOYS');
INSERT INTO categories (id, name) VALUES (4, 'ACCESSORIES');
INSERT INTO categories (id, name) VALUES (5, 'BABY GEAR');
INSERT INTO categories (id, name) VALUES (6, 'HEALTH AND SAFETY');
INSERT INTO categories (id, name) VALUES (7, 'FURNITURE');
INSERT INTO categories (id, name) VALUES (8, 'FEEDING');
INSERT INTO categories (id, name) VALUES (9, 'BOOKS');
INSERT INTO categories (id, name) VALUES (10, 'BEDDING');
INSERT INTO categories (id, name) VALUES (11, 'MATERNITY');

--Product
INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES (Default, 'Product 1', 'image1.jpg', 'Description 1', 19.99, 2, 'NUEVO', 1, 1);
INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES (Default, 'Product 2', 'image2.jpg', 'Description 2', 29.99, 3, 'USADO', 1, 2);
INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES (Default, 'Product 3', 'image3.jpg', 'Description 3', 39.99, 4, 'NUEVO', 1, 3);
