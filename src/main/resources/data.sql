--User
INSERT INTO users (email, username, password)
VALUES ('u@gmail.com','admin', '$2a$12$e.JYVZ./l.anff1gn455vetWFqFYP/7WOFVZRGUhMH7wugsj04Lsi');
--Category
INSERT INTO categories (name) VALUES
('ROPA'),
('ALZADO'),
('JUGUETES'),
('ACCESORIOS'),
('ARTÍCULOS PARA BEBÉ'),
('SALUD Y SEGURIDAD'),
('MUEBLES'),
('ALIMENTACIÓN'),
('LIBROS'),
('ROPA DE CAMA'),
('MATERNIDAD');

INSERT INTO products (id, name, image, description, price, age, condition, user_id, category_id) VALUES
(Default, 'Bebé de Juguete', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467340/hackaton/ai8zzyxwflrwzw6jauxk.jpg', 'Muñeco bebé con accesorios, ideal para niños pequeños.', 19.99, 3, 'NUEVO', 1, 3),
(Default, 'Canasta para Bebé', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467340/hackaton/cf9kvwwvyejisxgrb0ua.jpg', 'Canasta con artículos esenciales para recién nacidos.', 34.99, 0, 'NUEVO', 1, 5),
(Default, 'Carrito de Bebé Clásico Negro', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467340/hackaton/ry423nsfsyfd6fcjtbzm.jpg', 'Carrito de bebé de estilo clásico con capota ajustable.', 199.99, 0, 'NUEVO', 1, 4),
(Default, 'Carrito de Bebé Moderno Beige', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467340/hackaton/q365zkdntnzcxlejrm6j.jpg', 'Carrito de paseo con diseño ergonómico y suspensión.', 249.99, 0, 'NUEVO', 1, 4),
(Default, 'Carrito de Bebé Gris', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467340/hackaton/vxr29dzzeprcchhcip2u.jpg', 'Carrito de bebé compacto y ligero para viajes.', 179.99, 0, 'NUEVO', 1, 4),
(Default, 'Carrito de Paseo Verde', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467340/hackaton/rcyoleyu1zrq85gvaghs.jpg', 'Carrito de paseo plegable con capota y cinturón de seguridad.', 139.99, 0, 'NUEVO', 1, 4),
(Default, 'Cuna de Viaje Grande', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467341/hackaton/obziexursia5xrnq3qof.jpg', 'Cuna portátil de viaje con colchón incluido.', 89.99, 0, 'NUEVO', 1, 7),
(Default, 'Cuna de Madera', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467341/hackaton/ejlwjxo517pn93kkd6l4.jpg', 'Cuna de madera maciza con barandilla de seguridad.', 229.99, 0, 'NUEVO', 1, 7),
(Default, 'Cuna con Dosel', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467341/hackaton/h3v9g7jtrhg0vv4zhlnf.jpg', 'Cuna con diseño de nido para mayor comodidad del bebé.', 179.99, 0, 'NUEVO', 1, 7),
(Default, 'Cuna Rosa', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467341/hackaton/mooywhepb2h8veaxn21g.jpg', 'Cuna en color rosa con diseño elegante para princesas.', 149.99, 0, 'NUEVO', 1, 7),
(Default, 'Escucha Bebés Amarillo', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467341/hackaton/fd7lvkt0uyvnrthjgjee.jpg', 'Monitor de audio para bebés con largo alcance.', 39.99, 0, 'NUEVO', 1, 6),
(Default, 'Escucha Bebés Verde+', 'ehttps://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467341/hackaton/dgrkwmea2n88q7x420w9.jpg', 'Monitor de bebé con función de intercomunicador.', 49.99, 0, 'NUEVO', 1, 6),
(Default, 'Juego de Figuras para Bebé', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467341/hackaton/rycjcwdgkk2jxqeadrcw.jpg', 'Set de figuras coloridas para estimular la creatividad.', 14.99, 2, 'NUEVO', 1, 3),
(Default, 'Carrito de Juguete Verde', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467341/hackaton/xwju1v7taro73cxefynx.jpg', 'Carrito de juguete resistente para los más pequeños.', 24.99, 3, 'NUEVO', 1, 3),
(Default, 'Juego de Herramientas de Juguete', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467342/hackaton/hcbvz4pavis2gzaqvuhf.jpg', 'Set de herramientas de juguete para pequeños constructores.', 18.50, 4, 'NUEVO', 1, 3),
(Default, 'Lote de ropa', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467342/hackaton/shnqbpfwbvfkqkk9mnau.jpg', 'Lote de ropa usada.', 9.99, 5, 'USADO', 1, 1),
(Default, 'Mecedora para Bebé', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467342/hackaton/gmrpqtzy7vzvznkhum9i.jpg', 'Mecedora con vibración y música para calmar a los bebés.', 49.99, 1, 'USADO', 1, 3),
(Default, 'Moto de Bebé Rosa', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467342/hackaton/mors7d25askhgneuhedz.jpg', 'Moto de juguete de color rosa para aprender a equilibrarse.', 29.99, 2, 'NUEVO', 1, 3),
(Default, 'Peluche Elefante Azul', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467342/hackaton/foehgqli6d3gfg2kxmn5.jpg', 'Elefante de peluche suave y tierno.', 12.99, 1, 'NUEVO', 1, 3),
(Default, 'Peluche Pequeño Dinosaurio', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467342/hackaton/fr9ljcwq4gq6prpvhrft.jpg', 'Dinosaurio de peluche con textura suave.', 13.99, 3, 'USADO', 1, 3),
(Default, 'Peluche Pluto', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/afiyxp2hh7wmazkg1jmm.jpg', 'Peluche de Pluto con diseño clásico de Disney.', 15.99, 3, 'NUEVO', 1, 1),
(Default, 'Ropa de Segunda Mano Vestidos', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/jqxuhcbcihbhthseurx7.jpg', 'Vestidos para bebés de segunda mano en buen estado.', 24.99, 1, 'USADO', 1, 1),
(Default, 'Ropa Usada Variada', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/o0xqhmw6fn4ai7bklbt0.jpg', 'Lote de ropa usada para bebés en diferentes tallas.', 19.99, 1, 'USADO', 1, 1),
(Default, 'Sacaleches Morado', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/hzbxtu7a2penjmx44kcw.jpg', 'Extractor de leche manual con diseño ergonómico.', 29.99, 0, 'NUEVO', 1, 11),
(Default, 'Silla Comedor Gris y Blanco', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/nviv1qatatxcigr2hkmk.png', 'Silla alta de comedor para bebés con bandeja extraíble.', 79.99, 1, 'NUEVO', 1, 8),
(Default, 'Silla Comedor Verde', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/p6h83hkl2x7xgzgp3ww5.jpg', 'Silla de bebé ajustable con cinturón de seguridad.', 69.99, 1, 'NUEVO', 1, 8),
(Default, 'Silla Comedor Multicolor', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/kh4egolaoglv4fq9vlcn.jpg', 'Silla de bebé con diseño colorido y seguro.', 74.99, 1, 'NUEVO', 1, 8),
(Default, 'Silla Comedor Estampada', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467340/hackaton/ew1j7yz7x9lk750hftij.jpg', 'Silla de bebé acolchada con diseño moderno.', 84.99, 1, 'NUEVO', 1, 8),
(Default, 'Silla de Comida Amarilla', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/zrhqmkxfhrabgoizdjtm.jpg', 'Silla de alimentación para bebés con estructura estable.', 89.99, 1, 'NUEVO', 1, 8),
(Default, 'Silla Mecedora Gris', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467340/hackaton/fvuf4vy00bi2agjjyzeq.jpg', 'Mecedora ergonómica para bebés con vibración.', 119.99, 0, 'NUEVO', 1, 4),
(Default, 'Silla Mecedora Roja', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467340/hackaton/vswconubfoljzpchbm2f.jpg', 'Mecedora portátil para bebés con juguete incluido.', 109.99, 0, 'NUEVO', 1, 3),
(Default, 'Sacaleches Amarillo', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/ndqhzef4drhi4tx9akrt.jpg', 'Sacaleches eléctrico con control de succión.', 39.99, 0, 'NUEVO', 1, 11),
(Default, 'Sacaleches Amarillo Doble', 'https://res.cloudinary.com/dtxf1xjmd/image/upload/v1742467339/hackaton/ohpvakzbte7auebwkvaa.jpg', 'Sacaleches doble con ajuste de velocidad.', 59.99, 0, 'NUEVO', 1, 11);

