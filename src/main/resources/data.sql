--User
INSERT INTO users (email, username, password)
VALUES ('u@gmail.com','admin', '$2a$12$e.JYVZ./l.anff1gn455vetWFqFYP/7WOFVZRGUhMH7wugsj04Lsi');

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
INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES (Default, 'Camiseta', 'tshirt.jpg', 'Una cómoda camiseta de algodón', 15.99, 2, 'NUEVO', 1, 1);
INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES (Default, 'Jeans', 'jeans.jpg', 'Jeans clásicos de color azul', 25.99, 3, 'USADO', 1, 1);
INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES (Default, 'Zapatillas para correr', 'shoes.jpg', 'Zapatillas ligeras para correr', 50.00, 2, 'NUEVO', 1, 2);
INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES (Default, 'Botas', 'boots.jpg', 'Botas de cuero para invierno', 80.00, 5, 'USADO', 1, 2);
INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES (Default, 'Coche de juguete', 'car.jpg', 'Coche de juguete teledirigido', 30.00, 4, 'NUEVO', 1, 3);
INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES (Default, 'Muñeca', 'doll.jpg', 'Hermosa muñeca princesa', 20.00, 3, 'USADO', 1, 3);
