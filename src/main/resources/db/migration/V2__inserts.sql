INSERT INTO recompensa (id, categoria, descripcion, nombre, puntos_requeridos)
VALUES (2, 'Tecno', 'Iphone chimba', 'iPhone 15 ', 50);
VALUES (3, 'Tecno', 'Iphone viejito', 'iPhone 11 ', 15);
INSERT INTO cliente (id, nombre, puntos)
VALUES (3, 'Julian', 5),
(2, 'Batman', 5);
INSERT INTO transaccion (id, id_cliente, id_recompensa)
VALUES (2, 3, 2),
(3, 2, 2),
(4, 2, 3);


