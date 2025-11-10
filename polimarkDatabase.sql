
-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: Polimark
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.24.04.1

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `rangoIdRango` int NOT NULL,
  `puntos` int DEFAULT NULL,
  PRIMARY KEY (`identificador`,`rangoIdRango`),
  KEY `fk_usuario_rango1_idx` (`rangoIdRango`),
  CONSTRAINT `fk_usuario_rango1` FOREIGN KEY (`rangoIdRango`) REFERENCES `rango` (`idRango`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `idCompra` int NOT NULL AUTO_INCREMENT,
  `pagado` tinyint DEFAULT NULL,
  `metodoPagoIdMetodoPago` int NOT NULL,
  `usuarioIdentificador` int NOT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `fk_compra_metodoPago1_idx` (`metodoPagoIdMetodoPago`),
  KEY `fk_compra_usuario1_idx` (`usuarioIdentificador`),
  CONSTRAINT `fk_compra_metodoPago1` FOREIGN KEY (`metodoPagoIdMetodoPago`) REFERENCES `metodoPago` (`idMetodoPago`),
  CONSTRAINT `fk_compra_usuario1` FOREIGN KEY (`usuarioIdentificador`) REFERENCES `usuario` (`identificador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `fk_producto_articulo1` FOREIGN KEY (`articuloIdArticulo`) REFERENCES `articulo` (`idArticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `compraHasPromocion`
--

DROP TABLE IF EXISTS `compraHasPromocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compraHasPromocion` (
  `compraIdCompra` int NOT NULL,
  `promocionesIdPromociones` int NOT NULL,
  'cantidadAplicada' int DEFAULT NULL,
  PRIMARY KEY (`compraIdCompra`,`promocionesIdPromociones`),
  KEY `fk_compra_has_promociones_compra1_idx` (`compraIdCompra`),
  KEY `fk_compra_has_promociones_promociones1_idx` (`promocionesIdPromociones`),
  CONSTRAINT `fk_compra_has_promociones_compra1` FOREIGN KEY (`compraIdCompra`) REFERENCES `compra` (`idCompra`),
  CONSTRAINT `fk_compra_has_promociones_promociones1` FOREIGN KEY (`promocionesIdPromociones`) REFERENCES `promociones` (`idPromociones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
