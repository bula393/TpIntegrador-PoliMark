-- Tablas SIN auto_increment: lugar, pelicula, rango, usuario, articulo, butaca, 
-- metodoPago, producto, promociones, sala, funcion, entradaAuto

-- Tablas CON auto_increment: compra, notificaciones

-- Insert para tabla lugar (10 lugares - 5 AMBA + 5 nuevos)
INSERT INTO `lugar` (`idLugar`, `nombre`, `direccion`) VALUES
(1, 'Polimark Avellaneda', 'Av. Güemes 123, Avellaneda'),
(2, 'Polimark Quilmes', 'Calle 12 de Octubre 456, Quilmes'),
(3, 'Polimark Lomas', 'Av. Hipólito Yrigoyen 789, Lomas de Zamora'),
(4, 'Polimark Morón', 'Belgrano 321, Morón'),
(5, 'Polimark San Justo', 'Marcos Paz 654, San Justo'),
(6, 'Polimark Santa Teresita', 'Av. Costanera 234, Santa Teresita'),
(7, 'Polimark San Bernardo', 'Calle 35 567, San Bernardo'),
(8, 'Polimark Mar del Plata', 'Av. Colón 890, Mar del Plata'),
(9, 'Polimark Córdoba Centro', 'Av. Vélez Sársfield 1234, Córdoba'),
(10, 'Polimark Córdoba Nueva', 'Av. Fuerza Aérea 567, Córdoba');

-- Insert para tabla rango
INSERT INTO `rango` (`idRango`, `precio`, `nombre`) VALUES
(1, 0, 'regular'),
(2, 10, 'car pro+'),
(3, 15, 'car premium');

-- Insert para tabla pelicula
INSERT INTO `pelicula` (`nombre`, `productor`, `duracionMin`, `fotoCartelera`, `linkTrailer`) VALUES
('El Señor de los Anillos', 'Peter Jackson', 178, 'lotr_poster.jpg', 'https://youtube.com/lotr_trailer'),
('Matrix', 'Lana Wachowski', 136, 'matrix_poster.jpg', 'https://youtube.com/matrix_trailer'),
('Avatar', 'James Cameron', 162, 'avatar_poster.jpg', 'https://youtube.com/avatar_trailer'),
('Toy Story', 'John Lasseter', 81, 'toystory_poster.jpg', 'https://youtube.com/toystory_trailer'),
('Jurassic Park', 'Steven Spielberg', 127, 'jurassic_poster.jpg', 'https://youtube.com/jurassic_trailer'),
('Star Wars: Una Nueva Esperanza', 'George Lucas', 121, 'starwars_poster.jpg', 'https://youtube.com/starwars_trailer'),
('Harry Potter y la Piedra Filosofal', 'Chris Columbus', 152, 'harrypotter_poster.jpg', 'https://youtube.com/harrypotter_trailer'),
('The Dark Knight', 'Christopher Nolan', 152, 'batman_poster.jpg', 'https://youtube.com/batman_trailer'),
('Forrest Gump', 'Robert Zemeckis', 142, 'forrest_poster.jpg', 'https://youtube.com/forrest_trailer'),
('Pulp Fiction', 'Quentin Tarantino', 154, 'pulp_poster.jpg', 'https://youtube.com/pulp_trailer');

-- Insert para tabla sala
INSERT INTO `sala` (`idSala`, `capacidad`, `lugarIdLugar`, `tipo`) VALUES
(1, '150', 1, 'NORMAL'),
(2, '100', 1, 'CUARTROD'),
(3, '50', 1, 'AUTOCINE'),
(4, '120', 2, 'NORMAL'),
(5, '80', 2, 'CUARTROD'),
(6, '200', 3, 'NORMAL'),
(7, '90', 3, 'CUARTROD'),
(8, '60', 4, 'AUTOCINE'),
(9, '110', 4, 'NORMAL'),
(10, '130', 5, 'NORMAL'),
(11, '70', 6, 'CUARTROD'),
(12, '40', 7, 'AUTOCINE'),
(13, '100', 8, 'NORMAL'),
(14, '85', 9, 'CUARTROD'),
(15, '55', 10, 'AUTOCINE');

-- Insert para tabla metodoPago
INSERT INTO `metodoPago` (`idMetodoPago`, `nombre`, `descuentoPorcentaje`) VALUES
(1, 'Efectivo', 10),
(2, 'Tarjeta de Crédito', 0),
(3, 'Tarjeta de Débito', 5),
(4, 'Mercado Pago', 2),
(5, 'Transferencia', 3);

-- Insert para tabla articulo
INSERT INTO `articulo` (`idArticulo`, `precio`) VALUES
(1, 15),
(2, 18),
(3, 20),
(4, 12),
(5, 25),
(6, 8),
(7, 10),
(8, 22),
(9, 30),
(10, 35);

-- Insert para tabla producto
INSERT INTO `producto` (`nombre`, `descripcion`, `articuloIdArticulo`,`categoria`, `stock`) VALUES
('Coca Cola 500ml', 'Gaseosa Coca Cola 500ml', 1, 'Bebida', 100),
('Pochoclos Grande', 'Pochoclos tamaño grande', 2, 'Snack', 50),
('Agua Mineral', 'Agua sin gas 500ml', 4, 'Bebida', 80),
('Sandwich Jamón y Queso', 'Sandwich fresco', 5, 'Comida', 25),
('Gomitas', 'Gomitas surtidas 100gr', 6, 'Snack', 60),
('Chocolate', 'Tableta de chocolate', 7, 'Snack', 45),
('Nachos con Queso', 'Nachos con salsa de queso', 9, 'Snack', 35),
('Helado', 'Helado en pote 250ml', 10, 'Snack', 40);

-- Insert para tabla promociones
INSERT INTO `promociones` (`idPromociones`, `tipo`, `caracteristicas`, `rangoIdRango`) VALUES
(1, 'DESCUENTO', '20-combos-martes', 1),
(2, 'COMBO', 'Pochoclos+Bebida-3000', 2),
(3, 'DOSXUNO', '2x1-entrada-miércoles', 3),
(4, 'DESCUENTO', '15-producto', 2),
(5, 'COMBO', 'Nachos+Nachos+Bebida+Bebida+Helado-12000', 3),
(6, 'DOSXUNO', '3x2-Bebida', 1),
(7, 'DESCUENTO', '10-Snack', 2),
(8, 'COMBO', 'Nachos+Nachos+Bebida-7000', 3),
(9, 'DOSXUNO', '2x1-pochoclos-jueves', 1),
(10, 'DESCUENTO', '5-entrada-viernes', 2);

-- Insert para tabla funcion
INSERT INTO `funcion` (`salaIdSala`, `peliculaNombre`, `horario`, `idFuncion`) VALUES
(1, 'El Señor de los Anillos', '2024-11-20 18:00:00', 1),
(2, 'Matrix', '2024-11-20 20:30:00', 2),
(3, 'Avatar', '2024-11-20 22:00:00', 3),
(4, 'Toy Story', '2024-11-21 15:00:00', 4),
(5, 'Jurassic Park', '2024-11-21 17:30:00', 5),
(6, 'Star Wars: Una Nueva Esperanza', '2024-11-21 20:00:00', 6),
(7, 'Harry Potter y la Piedra Filosofal', '2024-11-22 16:00:00', 7),
(8, 'The Dark Knight', '2024-11-22 18:30:00', 8),
(9, 'Forrest Gump', '2024-11-22 21:00:00', 9),
(10, 'Pulp Fiction', '2024-11-23 19:00:00', 10);
-- Insert para tabla usuario (10 usuarios)
INSERT INTO `usuario` (`identificador`, `nombre`, `apellido`, `mail`, `contrasenaHash`, `rangoIdRango`, `puntos`) VALUES
(1, 'Juan', 'Pérez', 'juan.perez@mail.com', UNHEX(SHA2('password123', 256)), 1, 100),
(2, 'María', 'Gómez', 'maria.gomez@mail.com', UNHEX(SHA2('securepass', 256)), 2, 250),
(3, 'Carlos', 'López', 'carlos.lopez@mail.com', UNHEX(SHA2('mypass123', 256)), 1, 75),
(4, 'Ana', 'Martínez', 'ana.martinez@mail.com', UNHEX(SHA2('annapass', 256)), 3, 500),
(5, 'Pedro', 'Rodríguez', 'pedro.rodriguez@mail.com', UNHEX(SHA2('pedro123', 256)), 2, 180),
(6, 'Laura', 'García', 'laura.garcia@mail.com', UNHEX(SHA2('laurapass', 256)), 1, 60),
(7, 'Diego', 'Fernández', 'diego.fernandez@mail.com', UNHEX(SHA2('diego456', 256)), 3, 420),
(8, 'Sofía', 'Hernández', 'sofia.hernandez@mail.com', UNHEX(SHA2('sofia789', 256)), 2, 210),
(9, 'Miguel', 'Díaz', 'miguel.diaz@mail.com', UNHEX(SHA2('miguelpass', 256)), 1, 90),
(10, 'Elena', 'Torres', 'elena.torres@mail.com', UNHEX(SHA2('elena321', 256)), 3, 380);

-- Insert para tabla butaca (100 butacas distribuidas en las salas)
INSERT INTO `butaca` (`idButaca`, `columna`, `fila`, `salaIdSala`) VALUES
-- Butacas existentes (1-15)
(1, 1, 'A', 1), (2, 2, 'A', 1), (3, 3, 'A', 1), (4, 1, 'B', 1), (5, 2, 'B', 1),
(6, 1, 'A', 2), (7, 2, 'A', 2), (8, 1, 'B', 2), (9, 1, 'A', 3), (10, 2, 'A', 3),
(11, 1, 'A', 4), (12, 2, 'A', 4), (13, 1, 'B', 4), (14, 1, 'A', 5), (15, 2, 'A', 5),

-- Sala 1 - 20 butacas adicionales (16-35)
(16, 4, 'A', 1), (17, 5, 'A', 1), (18, 6, 'A', 1), (19, 7, 'A', 1), (20, 8, 'A', 1),
(21, 1, 'C', 1), (22, 2, 'C', 1), (23, 3, 'C', 1), (24, 4, 'C', 1), (25, 5, 'C', 1),
(26, 6, 'C', 1), (27, 7, 'C', 1), (28, 8, 'C', 1), (29, 1, 'D', 1), (30, 2, 'D', 1),
(31, 3, 'D', 1), (32, 4, 'D', 1), (33, 5, 'D', 1), (34, 6, 'D', 1), (35, 7, 'D', 1),

-- Sala 2 - 15 butacas adicionales (36-50)
(36, 3, 'A', 2), (37, 4, 'A', 2), (38, 5, 'A', 2), (39, 6, 'A', 2), (40, 7, 'A', 2),
(41, 1, 'C', 2), (42, 2, 'C', 2), (43, 3, 'C', 2), (44, 4, 'C', 2), (45, 5, 'C', 2),
(46, 2, 'B', 2), (47, 3, 'B', 2), (48, 4, 'B', 2), (49, 5, 'B', 2), (50, 6, 'B', 2),

-- Sala 3 - 10 butacas adicionales (51-60)
(51, 3, 'A', 3), (52, 4, 'A', 3), (53, 5, 'A', 3), (54, 1, 'B', 3), (55, 2, 'B', 3),
(56, 3, 'B', 3), (57, 4, 'B', 3), (58, 5, 'B', 3), (59, 1, 'C', 3), (60, 2, 'C', 3),

-- Sala 4 - 15 butacas adicionales (61-75)
(61, 3, 'A', 4), (62, 4, 'A', 4), (63, 5, 'A', 4), (64, 6, 'A', 4), (65, 7, 'A', 4),
(66, 2, 'B', 4), (67, 3, 'B', 4), (68, 4, 'B', 4), (69, 5, 'B', 4), (70, 6, 'B', 4),
(71, 1, 'C', 4), (72, 2, 'C', 4), (73, 3, 'C', 4), (74, 4, 'C', 4), (75, 5, 'C', 4),

-- Sala 5 - 10 butacas adicionales (76-85)
(76, 3, 'A', 5), (77, 4, 'A', 5), (78, 5, 'A', 5), (79, 6, 'A', 5), (80, 7, 'A', 5),
(81, 1, 'B', 5), (82, 2, 'B', 5), (83, 3, 'B', 5), (84, 4, 'B', 5), (85, 5, 'B', 5),

-- Sala 6 - 5 butacas (86-90)
(86, 1, 'A', 6), (87, 2, 'A', 6), (88, 3, 'A', 6), (89, 4, 'A', 6), (90, 5, 'A', 6),

-- Sala 7 - 5 butacas (91-95)
(91, 1, 'A', 7), (92, 2, 'A', 7), (93, 3, 'A', 7), (94, 4, 'A', 7), (95, 5, 'A', 7),

-- Sala 8 - 5 butacas (96-100)
(96, 1, 'A', 8), (97, 2, 'A', 8), (98, 3, 'A', 8), (99, 4, 'A', 8), (100, 5, 'A', 8);

-- Insert para tabla compra (10 compras)
INSERT INTO `compra` (`idCompra`, `pagado`, `metodoPagoIdMetodoPago`, `usuarioIdentificador`, `monto`, `fecha`) VALUES
(1, 1, 1, 1, 4500, '2024-11-15'),
(2, 1, 2, 2, 6800, '2024-11-16'),
(3, 0, 3, 3, 3200, '2024-11-17'),
(4, 1, 4, 4, 8900, '2024-11-18'),
(5, 1, 1, 5, 5400, '2024-11-19'),
(6, 1, 2, 6, 7200, '2024-11-20'),
(7, 0, 3, 7, 4100, '2024-11-21'),
(8, 1, 4, 8, 6300, '2024-11-22'),
(9, 1, 1, 9, 2900, '2024-11-23'),
(10, 1, 2, 10, 8500, '2024-11-24');

-- Insert para tabla entrada (usando butacas que existen)
INSERT INTO `entrada` (`compraIdCompra`, `funcionIdFuncion`, `butacaIdButaca`, `articuloIdArticulo`) VALUES
(1, 1, 1, 1),   -- Butaca 1 (Sala 1)
(1, 1, 2, 2),   -- Butaca 2 (Sala 1)
(2, 2, 6, 3),   -- Butaca 6 (Sala 2)
(3, 3, 9, 4),   -- Butaca 9 (Sala 3)
(4, 4, 11, 5),  -- Butaca 11 (Sala 4)
(5, 5, 14, 6),  -- Butaca 14 (Sala 5)
(6, 6, 16, 7),  -- Butaca 16 (Sala 1)
(7, 7, 36, 8),  -- Butaca 36 (Sala 2)
(8, 8, 51, 9),  -- Butaca 51 (Sala 3)
(9, 9, 61, 10); -- Butaca 61 (Sala 4)

-- Insert para tabla producto_has_compra (productos comprados)
INSERT INTO `producto_has_compra` (`compraIdCompra`, `cantidad`, `productoArticuloIdArticulo`) VALUES
(1, 2, 1),
(1, 1, 2),
(2, 3, 4),
(3, 1, 5),
(4, 2, 6),
(5, 1, 7),
(6, 2, 9),
(7, 1, 10),
(8, 3, 1),
(9, 2, 2);

-- Insert para tabla compraHasPromocion (promociones aplicadas)
INSERT INTO `compraHasPromocion` (`compraIdCompra`, `promocionesIdPromociones`, `cantidadAplicada`) VALUES
(1, 1, 1),
(2, 2, 1),
(4, 3, 2),
(5, 4, 1),
(8, 5, 1),
(10, 6, 3);

-- Insert para tabla notificaciones
INSERT INTO `notificaciones` (`idNotificacion`, `usuarioIdentificador`, `funcionIdFuncion`, `mensaje`, `fechaCreacion`, `leida`, `fechaEnvio`) VALUES
(1, 1, 1, 'Recordatorio: Tu función de El Señor de los Anillos es hoy a las 18:00', NOW(), 0, '2024-11-20'),
(2, 2, 2, 'Promoción especial para Matrix: 20% de descuento en combos', NOW(), 1, '2024-11-20'),
(3, 4, 4, 'No te pierdas Toy Story esta tarde a las 15:00', NOW(), 0, '2024-11-21'),
(4, 5, 5, 'Recordatorio: Jurassic Park en 1 hora', NOW(), 1, '2024-11-21'),
(5, 7, 7, 'Harry Potter: Lleva 2x1 en pochoclos hoy', NOW(), 0, '2024-11-22');

-- Insert para tabla entradaAuto (para entradas de autocine)
INSERT INTO `entradaAuto` (`entradaArticuloIdArticulo`, `patente`, `cantidadAuto`) VALUES
(4, 'ABC123', 1),  -- Entrada 4 (Sala 3 - autocine)
(9, 'DEF456', 2);  -- Entrada 9 (Sala 3 - autocine)