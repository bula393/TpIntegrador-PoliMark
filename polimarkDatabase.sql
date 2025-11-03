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
  `idLugar` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idLugar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugar`
--

LOCK TABLES `lugar` WRITE;
/*!40000 ALTER TABLE `lugar` DISABLE KEYS */;
/*!40000 ALTER TABLE `lugar` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rango`
--

DROP TABLE IF EXISTS `rango`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rango` (
  `idRango` int NOT NULL,
  `precio` int ,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRango`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rango`
--

LOCK TABLES `rango` WRITE;
/*!40000 ALTER TABLE `rango` DISABLE KEYS */;
/*!40000 ALTER TABLE `rango` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `identificador` int NOT NULL,
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
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `idArticulo` int NOT NULL,
  `precio` int DEFAULT NULL,
  PRIMARY KEY (`idArticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `butaca`
--

DROP TABLE IF EXISTS `butaca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `butaca` (
  `idButaca` int NOT NULL,
  `columna` int DEFAULT NULL,
  `fila` varchar(45) DEFAULT NULL,
  `salaIdSala` int NOT NULL,
  PRIMARY KEY (`idButaca`),
  KEY `fk_butaca_sala_idx` (`salaIdSala`),
  CONSTRAINT `fk_butaca_sala` FOREIGN KEY (`salaIdSala`) REFERENCES `sala` (`idSala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `butaca`
--

LOCK TABLES `butaca` WRITE;
/*!40000 ALTER TABLE `butaca` DISABLE KEYS */;
/*!40000 ALTER TABLE `butaca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `idCompra` int NOT NULL,
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
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `entrada` ENABLE KEYS */;
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
  `idFuncion` int NOT NULL,
  PRIMARY KEY (`idFuncion`),
  KEY `fk_sala_has_pelicula_pelicula1_idx` (`peliculaNombre`),
  KEY `fk_sala_has_pelicula_sala1_idx` (`salaIdSala`),
  CONSTRAINT `fk_sala_has_pelicula_pelicula1` FOREIGN KEY (`peliculaNombre`) REFERENCES `pelicula` (`nombre`),
  CONSTRAINT `fk_sala_has_pelicula_sala1` FOREIGN KEY (`salaIdSala`) REFERENCES `sala` (`idSala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcion`
--

LOCK TABLES `funcion` WRITE;
/*!40000 ALTER TABLE `funcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodoPago`
--

DROP TABLE IF EXISTS `metodoPago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodoPago` (
  `idMetodoPago` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descuentoPorcentaje` int DEFAULT NULL,
  PRIMARY KEY (`idMetodoPago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodoPago`
--

LOCK TABLES `metodoPago` WRITE;
/*!40000 ALTER TABLE `metodoPago` DISABLE KEYS */;
/*!40000 ALTER TABLE `metodoPago` ENABLE KEYS */;
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
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`articuloIdArticulo`),
  KEY `fk_producto_articulo1_idx` (`articuloIdArticulo`),
  CONSTRAINT `fk_producto_articulo1` FOREIGN KEY (`articuloIdArticulo`) REFERENCES `articulo` (`idArticulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
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
  `cantidad` varchar(45) DEFAULT NULL,
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
/*!40000 ALTER TABLE `producto_has_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articuloHasPromocion`
--

DROP TABLE IF EXISTS `articuloHasPromocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articuloHasPromocion` (
  `articuloIdArticulo` int NOT NULL,
  `promocionesIdPromociones` int NOT NULL,
  PRIMARY KEY (`articuloIdArticulo`,`promocionesIdPromociones`),
  KEY `fk_articuloHasPromocion_promociones1_idx` (`promocionesIdPromociones`),
  KEY `fk_articuloHasPromocion_articulo1_idx` (`articuloIdArticulo`),
  CONSTRAINT `fk_articuloHasPromocion_articulo1` FOREIGN KEY (`articuloIdArticulo`) REFERENCES `articulo` (`idArticulo`),
  CONSTRAINT `fk_articuloHasPromocion_promociones1` FOREIGN KEY (`promocionesIdPromociones`) REFERENCES `promociones` (`idPromociones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articuloHasPromocion`
--

LOCK TABLES `articuloHasPromocion` WRITE;
/*!40000 ALTER TABLE `articuloHasPromocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `articuloHasPromocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promociones`
--

DROP TABLE IF EXISTS `promociones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promociones` (
  `idPromociones` int NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `caracteristicas` varchar(45) DEFAULT NULL,
  `rangoIdRango` int NOT NULL,
  PRIMARY KEY (`idPromociones`),
  KEY `fk_promociones_rango1_idx` (`rangoIdRango`),
  CONSTRAINT `fk_promociones_rango1` FOREIGN KEY (`rangoIdRango`) REFERENCES `rango` (`idRango`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promociones`
--

LOCK TABLES `promociones` WRITE;
/*!40000 ALTER TABLE `promociones` DISABLE KEYS */;
/*!40000 ALTER TABLE `promociones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `idSala` int NOT NULL,
  `capacidad` varchar(45) DEFAULT NULL,
  `lugarIdLugar` int NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSala`,`lugarIdLugar`),
  KEY `fk_sala_lugar_idx` (`lugarIdLugar`),
  CONSTRAINT `fk_sala_lugar` FOREIGN KEY (`lugarIdLugar`) REFERENCES `lugar` (`idLugar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

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

DROP TABLE IF EXISTS `entradaAuto`;

CREATE TABLE `entradaAuto` (
  `entradaArticuloIdArticulo` INT NOT NULL,
  `patente` VARCHAR(7) NOT NULL,
  `cantidadAuto` INT DEFAULT NULL,
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

-- Dump completed on 2025-10-16 11:57:12