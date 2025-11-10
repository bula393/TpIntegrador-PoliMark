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

-- Insert para tabla butaca
INSERT INTO `butaca` (`idButaca`, `columna`, `fila`, `salaIdSala`) VALUES
(1, 1, 'A', 1),
(2, 2, 'A', 1),
(3, 3, 'A', 1),
(4, 1, 'B', 1),
(5, 2, 'B', 1),
(6, 1, 'A', 2),
(7, 2, 'A', 2),
(8, 1, 'B', 2),
(9, 1, 'A', 3),
(10, 2, 'A', 3),
(11, 1, 'A', 4),
(12, 2, 'A', 4),
(13, 1, 'B', 4),
(14, 1, 'A', 5),
(15, 2, 'A', 5);

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