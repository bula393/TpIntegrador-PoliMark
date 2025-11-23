-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: Polimark
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.24.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `idArticulo` int NOT NULL AUTO_INCREMENT,
  `precio` int DEFAULT NULL,
  PRIMARY KEY (`idArticulo`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (1,15),(2,18),(3,20),(4,12),(5,25),(6,8),(7,10),(8,22),(9,30),(10,35),(11,16),(12,19),(13,21),(14,13),(15,26),(16,9),(17,11),(18,23),(19,31),(20,36),(21,17),(22,20),(23,22),(24,14),(25,27),(26,10),(27,12),(28,24),(29,32),(30,37),(31,18),(32,21),(33,23);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `butaca`
--

DROP TABLE IF EXISTS `butaca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `butaca` (
  `idButaca` int NOT NULL AUTO_INCREMENT,
  `columna` int DEFAULT NULL,
  `fila` varchar(45) DEFAULT NULL,
  `salaIdSala` int NOT NULL,
  PRIMARY KEY (`idButaca`),
  KEY `fk_butaca_sala_idx` (`salaIdSala`),
  CONSTRAINT `fk_butaca_sala` FOREIGN KEY (`salaIdSala`) REFERENCES `sala` (`idSala`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `butaca`
--

LOCK TABLES `butaca` WRITE;
/*!40000 ALTER TABLE `butaca` DISABLE KEYS */;
INSERT INTO `butaca` VALUES (1,1,'A',1),(2,2,'A',1),(3,3,'A',1),(4,1,'B',1),(5,2,'B',1),(6,1,'A',2),(7,2,'A',2),(8,1,'B',2),(9,1,'A',3),(10,2,'A',3),(11,1,'A',4),(12,2,'A',4),(13,1,'B',4),(14,1,'A',5),(15,2,'A',5),(16,4,'A',1),(17,5,'A',1),(18,6,'A',1),(19,7,'A',1),(20,8,'A',1),(21,1,'C',1),(22,2,'C',1),(23,3,'C',1),(24,4,'C',1),(25,5,'C',1),(26,6,'C',1),(27,7,'C',1),(28,8,'C',1),(29,1,'D',1),(30,2,'D',1),(31,3,'D',1),(32,4,'D',1),(33,5,'D',1),(34,6,'D',1),(35,7,'D',1),(36,3,'A',2),(37,4,'A',2),(38,5,'A',2),(39,6,'A',2),(40,7,'A',2),(41,1,'C',2),(42,2,'C',2),(43,3,'C',2),(44,4,'C',2),(45,5,'C',2),(46,2,'B',2),(47,3,'B',2),(48,4,'B',2),(49,5,'B',2),(50,6,'B',2),(51,3,'A',3),(52,4,'A',3),(53,5,'A',3),(54,1,'B',3),(55,2,'B',3),(56,3,'B',3),(57,4,'B',3),(58,5,'B',3),(59,1,'C',3),(60,2,'C',3),(61,3,'A',4),(62,4,'A',4),(63,5,'A',4),(64,6,'A',4),(65,7,'A',4),(66,2,'B',4),(67,3,'B',4),(68,4,'B',4),(69,5,'B',4),(70,6,'B',4),(71,1,'C',4),(72,2,'C',4),(73,3,'C',4),(74,4,'C',4),(75,5,'C',4),(76,3,'A',5),(77,4,'A',5),(78,5,'A',5),(79,6,'A',5),(80,7,'A',5),(81,1,'B',5),(82,2,'B',5),(83,3,'B',5),(84,4,'B',5),(85,5,'B',5),(86,1,'A',6),(87,2,'A',6),(88,3,'A',6),(89,4,'A',6),(90,5,'A',6),(91,1,'A',7),(92,2,'A',7),(93,3,'A',7),(94,4,'A',7),(95,5,'A',7),(96,1,'A',8),(97,2,'A',8),(98,3,'A',8),(99,4,'A',8),(100,5,'A',8);
/*!40000 ALTER TABLE `butaca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `idCompra` int NOT NULL AUTO_INCREMENT,
  `pagado` tinyint DEFAULT NULL,
  `metodoPagoIdMetodoPago` int DEFAULT NULL,
  `usuarioIdentificador` int NOT NULL,
  `monto` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `fk_compra_metodoPago1_idx` (`metodoPagoIdMetodoPago`),
  KEY `fk_compra_usuario1_idx` (`usuarioIdentificador`),
  CONSTRAINT `fk_compra_metodoPago1` FOREIGN KEY (`metodoPagoIdMetodoPago`) REFERENCES `metodoPago` (`idMetodoPago`),
  CONSTRAINT `fk_compra_usuario1` FOREIGN KEY (`usuarioIdentificador`) REFERENCES `usuario` (`identificador`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (11,1,1,1,5200,'2025-01-10'),(12,1,2,2,4800,'2025-01-15'),(13,1,3,3,6100,'2025-02-05'),(14,1,4,4,7300,'2025-02-20'),(15,1,1,5,3900,'2025-03-08'),(16,1,2,6,8500,'2025-03-25'),(17,1,3,7,4200,'2025-04-12'),(18,1,4,8,6700,'2025-04-18'),(19,1,1,9,5400,'2025-05-03'),(20,1,2,10,7800,'2025-05-22'),(21,1,3,1,3200,'2025-06-07'),(22,1,4,2,8900,'2025-06-30'),(23,1,1,3,4500,'2025-07-14'),(24,1,2,4,6100,'2025-07-28'),(25,1,3,5,7200,'2025-08-09'),(26,1,4,6,3800,'2025-08-17'),(27,1,1,7,5600,'2025-09-11'),(28,1,2,8,4900,'2025-09-26'),(29,1,3,9,8300,'2025-10-05'),(30,1,4,10,6700,'2025-10-19'),(31,1,1,1,4100,'2025-12-03'),(32,1,2,2,7500,'2025-12-15'),(33,1,3,3,5200,'2025-12-22');
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compraHasPromocion`
--

DROP TABLE IF EXISTS `compraHasPromocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compraHasPromocion` (
  `compraIdCompra` int NOT NULL,
  `promocionesIdPromociones` int NOT NULL,
  `cantidadAplicada` int DEFAULT NULL,
  PRIMARY KEY (`compraIdCompra`,`promocionesIdPromociones`),
  KEY `fk_compra_has_promociones_compra1_idx` (`compraIdCompra`),
  KEY `fk_compra_has_promociones_promociones1_idx` (`promocionesIdPromociones`),
  CONSTRAINT `fk_compra_has_promociones_compra1` FOREIGN KEY (`compraIdCompra`) REFERENCES `compra` (`idCompra`),
  CONSTRAINT `fk_compra_has_promociones_promociones1` FOREIGN KEY (`promocionesIdPromociones`) REFERENCES `promociones` (`idPromociones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compraHasPromocion`
--

LOCK TABLES `compraHasPromocion` WRITE;
/*!40000 ALTER TABLE `compraHasPromocion` DISABLE KEYS */;
INSERT INTO `compraHasPromocion` VALUES (11,1,1),(12,2,1),(14,3,2),(15,4,1),(18,5,1),(20,6,3);
/*!40000 ALTER TABLE `compraHasPromocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada`
--

DROP TABLE IF EXISTS `entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada` (
  `compraIdCompra` int NOT NULL,
  `funcionIdFuncion` int NOT NULL,
  `butacaIdButaca` int NOT NULL,
  `articuloIdArticulo` int NOT NULL,
  PRIMARY KEY (`articuloIdArticulo`),
  KEY `fk_entrada_compra1_idx` (`compraIdCompra`),
  KEY `fk_entrada_funcion1_idx` (`funcionIdFuncion`),
  KEY `fk_entrada_butaca1_idx` (`butacaIdButaca`),
  CONSTRAINT `fk_entrada_articulo1` FOREIGN KEY (`articuloIdArticulo`) REFERENCES `articulo` (`idArticulo`),
  CONSTRAINT `fk_entrada_butaca1` FOREIGN KEY (`butacaIdButaca`) REFERENCES `butaca` (`idButaca`),
  CONSTRAINT `fk_entrada_compra1` FOREIGN KEY (`compraIdCompra`) REFERENCES `compra` (`idCompra`),
  CONSTRAINT `fk_entrada_funcion1` FOREIGN KEY (`funcionIdFuncion`) REFERENCES `funcion` (`idFuncion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada`
--

LOCK TABLES `entrada` WRITE;
/*!40000 ALTER TABLE `entrada` DISABLE KEYS */;
INSERT INTO `entrada` VALUES (11,1,17,11),(12,2,37,12),(13,3,52,13),(14,4,62,14),(15,5,76,15),(16,6,86,16),(17,7,91,17),(18,8,96,18),(19,9,21,19),(20,10,41,20),(21,1,53,21),(22,2,63,22),(23,3,77,23),(24,4,87,24),(25,5,92,25),(26,6,97,26),(27,7,22,27),(28,8,38,28),(29,9,54,29),(30,10,64,30),(31,1,78,31),(32,2,88,32),(33,3,93,33);
/*!40000 ALTER TABLE `entrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entradaAuto`
--

DROP TABLE IF EXISTS `entradaAuto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entradaAuto` (
  `entradaArticuloIdArticulo` int NOT NULL,
  `patente` varchar(7) NOT NULL,
  `cantidadAuto` int DEFAULT NULL,
  PRIMARY KEY (`entradaArticuloIdArticulo`),
  CONSTRAINT `fkEntradaAutoEntrada` FOREIGN KEY (`entradaArticuloIdArticulo`) REFERENCES `entrada` (`articuloIdArticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entradaAuto`
--

LOCK TABLES `entradaAuto` WRITE;
/*!40000 ALTER TABLE `entradaAuto` DISABLE KEYS */;
INSERT INTO `entradaAuto` VALUES (13,'ABC123',1),(18,'DEF456',2),(33,'GHI789',1);
/*!40000 ALTER TABLE `entradaAuto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcion`
--

DROP TABLE IF EXISTS `funcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcion` (
  `salaIdSala` int NOT NULL,
  `peliculaNombre` varchar(45) NOT NULL,
  `horario` datetime DEFAULT NULL,
  `idFuncion` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idFuncion`),
  KEY `fk_sala_has_pelicula_pelicula1_idx` (`peliculaNombre`),
  KEY `fk_sala_has_pelicula_sala1_idx` (`salaIdSala`),
  CONSTRAINT `fk_sala_has_pelicula_pelicula1` FOREIGN KEY (`peliculaNombre`) REFERENCES `pelicula` (`nombre`),
  CONSTRAINT `fk_sala_has_pelicula_sala1` FOREIGN KEY (`salaIdSala`) REFERENCES `sala` (`idSala`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcion`
--

LOCK TABLES `funcion` WRITE;
/*!40000 ALTER TABLE `funcion` DISABLE KEYS */;

INSERT INTO `funcion` (salaIdSala, peliculaNombre, horario, idFuncion) VALUES
(1,'El Señor de los Anillos','2025-11-20 18:00:00',1),
(2,'Matrix','2025-11-20 20:30:00',2),
(3,'Avatar','2025-11-20 22:00:00',3),
(4,'Toy Story','2025-11-21 15:00:00',4),
(5,'Jurassic Park','2025-11-21 17:30:00',5),
(6,'Star Wars: Una Nueva Esperanza','2025-11-21 20:00:00',6),
(7,'Harry Potter y la Piedra Filosofal','2025-11-30 16:00:00',7),
(8,'The Dark Knight','2025-11-29 18:30:00',8),
(9,'Forrest Gump','2025-11-28 21:00:00',9),
(10,'Pulp Fiction','2025-11-23 19:00:00',10),

-- Nuevos para Harry Potter (sala 7)
(7,'Harry Potter y la Piedra Filosofal','2025-12-01 14:00:00',11),
(7,'Harry Potter y la Piedra Filosofal','2025-12-01 18:00:00',12),
(7,'Harry Potter y la Piedra Filosofal','2025-12-02 16:00:00',13),
(7,'Harry Potter y la Piedra Filosofal','2025-12-03 20:00:00',14),
(7,'Harry Potter y la Piedra Filosofal','2025-12-04 15:30:00',15),

-- Nuevos para Matrix (sala 2)
(2,'Matrix','2025-12-05 18:00:00',16),
(2,'Matrix','2025-12-05 21:00:00',17),
(2,'Matrix','2025-12-06 19:30:00',18),
(2,'Matrix','2025-12-07 16:00:00',19),
(2,'Matrix','2025-12-07 22:00:00',20),

-- Nuevos para Avatar (sala 3)
(3,'Avatar','2025-12-10 17:00:00',21),
(3,'Avatar','2025-12-10 20:30:00',22),
(3,'Avatar','2025-12-11 15:00:00',23),
(3,'Avatar','2025-12-11 19:00:00',24),
(3,'Avatar','2025-12-12 21:00:00',25);

/*!40000 ALTER TABLE `funcion` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `lugar`
--

DROP TABLE IF EXISTS `lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lugar` (
  `idLugar` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idLugar`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugar`
--

LOCK TABLES `lugar` WRITE;
/*!40000 ALTER TABLE `lugar` DISABLE KEYS */;
INSERT INTO `lugar` VALUES (1,'Polimark Avellaneda','Av. Güemes 123, Avellaneda'),(2,'Polimark Quilmes','Calle 12 de Octubre 456, Quilmes'),(3,'Polimark Lomas','Av. Hipólito Yrigoyen 789, Lomas de Zamora'),(4,'Polimark Morón','Belgrano 321, Morón'),(5,'Polimark San Justo','Marcos Paz 654, San Justo'),(6,'Polimark Santa Teresita','Av. Costanera 234, Santa Teresita'),(7,'Polimark San Bernardo','Calle 35 567, San Bernardo'),(8,'Polimark Mar del Plata','Av. Colón 890, Mar del Plata'),(9,'Polimark Córdoba Centro','Av. Vélez Sársfield 1234, Córdoba'),(10,'Polimark Córdoba Nueva','Av. Fuerza Aérea 567, Córdoba');
/*!40000 ALTER TABLE `lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodoPago`
--

DROP TABLE IF EXISTS `metodoPago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodoPago` (
  `idMetodoPago` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descuentoPorcentaje` int DEFAULT NULL,
  PRIMARY KEY (`idMetodoPago`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodoPago`
--

LOCK TABLES `metodoPago` WRITE;
/*!40000 ALTER TABLE `metodoPago` DISABLE KEYS */;
INSERT INTO `metodoPago` VALUES (1,'Efectivo',10),(2,'Tarjeta de Crédito',0),(3,'Tarjeta de Débito',5),(4,'Mercado Pago',2),(5,'Transferencia',3);
/*!40000 ALTER TABLE `metodoPago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notificaciones`
--

DROP TABLE IF EXISTS `notificaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notificaciones` (
  `idNotificacion` int NOT NULL AUTO_INCREMENT,
  `usuarioIdentificador` int NOT NULL,
  `funcionIdFuncion` int NOT NULL,
  `mensaje` varchar(255) NOT NULL,
  `fechaCreacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `leida` tinyint DEFAULT '0',
  `fechaEnvio` date NOT NULL,
  PRIMARY KEY (`idNotificacion`),
  KEY `fk_notificaciones_usuario1_idx` (`usuarioIdentificador`),
  KEY `fk_notificaciones_funcion1_idx` (`funcionIdFuncion`),
  CONSTRAINT `fk_notificaciones_funcion1` FOREIGN KEY (`funcionIdFuncion`) REFERENCES `funcion` (`idFuncion`),
  CONSTRAINT `fk_notificaciones_usuario1` FOREIGN KEY (`usuarioIdentificador`) REFERENCES `usuario` (`identificador`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificaciones`
--

LOCK TABLES `notificaciones` WRITE;
/*!40000 ALTER TABLE `notificaciones` DISABLE KEYS */;
INSERT INTO `notificaciones` VALUES (1,1,1,'Recordatorio: Tu función de El Señor de los Anillos es hoy a las 18:00','2025-11-19 08:05:10',0,'2025-11-20'),(2,2,2,'Promoción especial para Matrix: 20% de descuento en combos','2025-11-19 08:05:10',1,'2025-11-20'),(3,4,4,'No te pierdas Toy Story esta tarde a las 15:00','2025-11-19 08:05:10',0,'2025-11-21'),(4,5,5,'Recordatorio: Jurassic Park en 1 hora','2025-11-19 08:05:10',1,'2025-11-21'),(5,7,7,'Harry Potter: Lleva 2x1 en pochoclos hoy','2025-11-19 08:05:10',0,'2025-11-22');
/*!40000 ALTER TABLE `notificaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula` (
  `nombre` varchar(45) NOT NULL,
  `productor` varchar(45) DEFAULT NULL,
  `duracionMin` int DEFAULT NULL,
  `fotoCartelera` varchar(100) DEFAULT NULL,
  `linkTrailer` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula`
--

LOCK TABLES `pelicula` WRITE;
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
INSERT INTO `pelicula` VALUES ('Avatar','James Cameron',162,'avatar_poster.jpg','https://youtube.com/avatar_trailer'),('El Señor de los Anillos','Peter Jackson',178,'lotr_poster.jpg','https://youtube.com/lotr_trailer'),('Forrest Gump','Robert Zemeckis',142,'forrest_poster.jpg','https://youtube.com/forrest_trailer'),('Harry Potter y la Piedra Filosofal','Chris Columbus',152,'harrypotter_poster.jpg','https://youtube.com/harrypotter_trailer'),('Jurassic Park','Steven Spielberg',127,'jurassic_poster.jpg','https://youtube.com/jurassic_trailer'),('Matrix','Lana Wachowski',136,'matrix_poster.jpg','https://youtube.com/matrix_trailer'),('Pulp Fiction','Quentin Tarantino',154,'pulp_poster.jpg','https://youtube.com/pulp_trailer'),('Star Wars: Una Nueva Esperanza','George Lucas',121,'starwars_poster.jpg','https://youtube.com/starwars_trailer'),('The Dark Knight','Christopher Nolan',152,'batman_poster.jpg','https://youtube.com/batman_trailer'),('Toy Story','John Lasseter',81,'toystory_poster.jpg','https://youtube.com/toystory_trailer');
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `articuloIdArticulo` int NOT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`articuloIdArticulo`),
  KEY `fk_producto_articulo1_idx` (`articuloIdArticulo`),
  CONSTRAINT `fk_producto_artiFculo1` FOREIGN KEY (`articuloIdArticulo`) REFERENCES `articulo` (`idArticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('Coca Cola 500ml','Gaseosa Coca Cola 500ml',1,'Bebida',100),('Pochoclos Grande','Pochoclos tamaño grande',2,'Snack',50),('Agua Mineral','Agua sin gas 500ml',4,'Bebida',80),('Sandwich Jamón y Queso','Sandwich fresco',5,'Comida',25),('Gomitas','Gomitas surtidas 100gr',6,'Snack',60),('Chocolate','Tableta de chocolate',7,'Snack',45),('Nachos con Queso','Nachos con salsa de queso',9,'Snack',35),('Helado','Helado en pote 250ml',10,'Snack',40);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_has_compra`
--

DROP TABLE IF EXISTS `producto_has_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_has_compra` (
  `compraIdCompra` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  `productoArticuloIdArticulo` int NOT NULL,
  PRIMARY KEY (`compraIdCompra`,`productoArticuloIdArticulo`),
  KEY `fk_producto_has_compra_compra1_idx` (`compraIdCompra`),
  KEY `fk_producto_has_compra_producto1_idx` (`productoArticuloIdArticulo`),
  CONSTRAINT `fk_producto_has_compra_compra1` FOREIGN KEY (`compraIdCompra`) REFERENCES `compra` (`idCompra`),
  CONSTRAINT `fk_producto_has_compra_producto1` FOREIGN KEY (`productoArticuloIdArticulo`) REFERENCES `producto` (`articuloIdArticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_has_compra`
--

LOCK TABLES `producto_has_compra` WRITE;
/*!40000 ALTER TABLE `producto_has_compra` DISABLE KEYS */;
INSERT INTO `producto_has_compra` VALUES (11,2,1),(12,3,2),(12,2,4),(13,1,5),(13,1,6),(14,2,7),(15,3,9),(15,2,10),(16,1,1),(16,2,2),(17,1,4),(18,3,5),(18,1,6),(19,2,7),(20,1,9),(20,3,10),(21,2,1),(21,1,2),(22,2,4),(23,1,5),(23,1,6),(24,2,7),(25,3,9),(25,2,10),(26,1,1),(26,2,2),(27,1,4),(28,3,5),(28,1,6),(29,2,7),(30,1,9),(30,3,10),(31,2,1),(31,1,2),(32,2,4),(33,1,5),(33,1,6);
/*!40000 ALTER TABLE `producto_has_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promociones`
--

DROP TABLE IF EXISTS `promociones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promociones` (
  `idPromociones` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  `caracteristicas` varchar(45) DEFAULT NULL,
  `rangoIdRango` int NOT NULL,
  PRIMARY KEY (`idPromociones`),
  KEY `fk_promociones_rango1_idx` (`rangoIdRango`),
  CONSTRAINT `fk_promociones_rango1` FOREIGN KEY (`rangoIdRango`) REFERENCES `rango` (`idRango`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promociones`
--

LOCK TABLES `promociones` WRITE;
/*!40000 ALTER TABLE `promociones` DISABLE KEYS */;
INSERT INTO `promociones` VALUES (1,'DESCUENTO','20-combos-martes',1),(2,'COMBO','Pochoclos+Bebida-3000',2),(3,'DOSXUNO','2x1-entrada-miércoles',3),(4,'DESCUENTO','15-producto',2),(5,'COMBO','Nachos+Nachos+Bebida+Bebida+Helado-12000',3),(6,'DOSXUNO','3x2-Bebida',1),(7,'DESCUENTO','10-Snack',2),(8,'COMBO','Nachos+Nachos+Bebida-7000',3),(9,'DOSXUNO','2x1-pochoclos-jueves',1),(10,'DESCUENTO','5-entrada-viernes',2);
/*!40000 ALTER TABLE `promociones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rango`
--

DROP TABLE IF EXISTS `rango`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rango` (
  `idRango` int NOT NULL AUTO_INCREMENT,
  `precio` int DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRango`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rango`
--

LOCK TABLES `rango` WRITE;
/*!40000 ALTER TABLE `rango` DISABLE KEYS */;
INSERT INTO `rango` VALUES (1,0,'regular'),(2,10,'car pro+'),(3,15,'car premium');
/*!40000 ALTER TABLE `rango` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `idSala` int NOT NULL AUTO_INCREMENT,
  `capacidad` int DEFAULT NULL,
  `lugarIdLugar` int NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSala`),
  KEY `fk_sala_lugar_idx` (`lugarIdLugar`),
  CONSTRAINT `fk_sala_lugar` FOREIGN KEY (`lugarIdLugar`) REFERENCES `lugar` (`idLugar`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (1,150,1,'NORMAL'),(2,100,1,'CUARTROD'),(3,50,1,'AUTOCINE'),(4,120,2,'NORMAL'),(5,80,2,'CUARTROD'),(6,200,3,'NORMAL'),(7,90,3,'CUARTROD'),(8,60,4,'AUTOCINE'),(9,110,4,'NORMAL'),(10,130,5,'NORMAL'),(11,70,6,'CUARTROD'),(12,40,7,'AUTOCINE'),(13,100,8,'NORMAL'),(14,85,9,'CUARTROD'),(15,55,10,'AUTOCINE');
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `identificador` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `contrasenaHash` binary(60) DEFAULT NULL,
  `rangoIdRango` int DEFAULT NULL,
  `puntos` int DEFAULT NULL,
  PRIMARY KEY (`identificador`),
  KEY `fk_usuario_rango1_idx` (`rangoIdRango`),
  CONSTRAINT `fk_usuario_rango1` FOREIGN KEY (`rangoIdRango`) REFERENCES `rango` (`idRango`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Juan','Pérez','juan.perez@mail.com',_binary 'x��w�$[�\��JNle��8=Ds\�O\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',1,100),(2,'María','Gómez','maria.gomez@mail.com',_binary '����c��X��+�ʻ0\�\�\r\��\�\�F�ӛ;\�D\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',2,250),(3,'Carlos','López','carlos.lopez@mail.com',_binary '\�\�u\�S\Z��\�\�\�\�xU�E4��\�j�\�\n\�?�j\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',1,75),(4,'Ana','Martínez','ana.martinez@mail.com',_binary '�E�^t\�\�\�r���\�s\�Qt�ӏ\Z����(:Z�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',3,500),(5,'Pedro','Rodríguez','pedro.rodriguez@mail.com',_binary '\'\�4\��\�g�\�\�\��\���k�4V\�\�\�+\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',2,180),(6,'Laura','García','laura.garcia@mail.com',_binary '�(\���Sm�#!�)&0!x_KH9�tIA��7��\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',1,60),(7,'Diego','Fernández','diego.fernandez@mail.com',_binary 'i�H[)R*d�\�$�3F�Y�dy\��\�\�G�e\�\�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',3,420),(8,'Sofía','Hernández','sofia.hernandez@mail.com',_binary '\��&�=���6DV()�8kU4��8�8\0�ēq\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',2,210),(9,'Miguel','Díaz','miguel.diaz@mail.com',_binary 'FQV�\�\�⊭X>��h�1�\�K��(6�Dgk�t\�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',1,90),(10,'Elena','Torres','elena.torres@mail.com',_binary '#�\�\�ѴW�\��\0\n�6\�`�0CPO��\r�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',3,380);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-19  8:15:19
