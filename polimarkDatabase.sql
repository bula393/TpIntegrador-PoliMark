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
-- Table structure for table `Lugar`
--

DROP TABLE IF EXISTS `Lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Lugar` (
  `idLugar` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idLugar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lugar`
--

LOCK TABLES `Lugar` WRITE;
/*!40000 ALTER TABLE `Lugar` DISABLE KEYS */;
/*!40000 ALTER TABLE `Lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pelicula`
--

DROP TABLE IF EXISTS `Pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pelicula` (
  `nombre` varchar(45) NOT NULL,
  `productor` varchar(45) DEFAULT NULL,
  `duracion(min)` int DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pelicula`
--

LOCK TABLES `Pelicula` WRITE;
/*!40000 ALTER TABLE `Pelicula` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rango`
--

DROP TABLE IF EXISTS `Rango`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Rango` (
  `idRango` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRango`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rango`
--

LOCK TABLES `Rango` WRITE;
/*!40000 ALTER TABLE `Rango` DISABLE KEYS */;
/*!40000 ALTER TABLE `Rango` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuario` (
  `identificador` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `contraseña(hash)` varchar(45) DEFAULT NULL,
  `Rango_idRango` int NOT NULL,
  `puntos` int DEFAULT NULL,
  PRIMARY KEY (`identificador`,`Rango_idRango`),
  KEY `fk_Usuario_Rango1_idx` (`Rango_idRango`),
  CONSTRAINT `fk_Usuario_Rango1` FOREIGN KEY (`Rango_idRango`) REFERENCES `Rango` (`idRango`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `idarticulo` int NOT NULL,
  `precio` int DEFAULT NULL,
  PRIMARY KEY (`idarticulo`)
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
  `idbutaca` int NOT NULL,
  `columna` int DEFAULT NULL,
  `fila` varchar(45) DEFAULT NULL,
  `sala_idsala` int NOT NULL,
  PRIMARY KEY (`idbutaca`),
  KEY `fk_butaca_sala_idx` (`sala_idsala`),
  CONSTRAINT `fk_butaca_sala` FOREIGN KEY (`sala_idsala`) REFERENCES `sala` (`idsala`)
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
  `idcompra` int NOT NULL,
  `pagado` tinyint DEFAULT NULL,
  `metodoPago_idmetodoPago` int NOT NULL,
  `Usuario_identificador` int NOT NULL,
  PRIMARY KEY (`idcompra`),
  KEY `fk_compra_metodoPago1_idx` (`metodoPago_idmetodoPago`),
  KEY `fk_compra_Usuario1_idx` (`Usuario_identificador`),
  CONSTRAINT `fk_compra_metodoPago1` FOREIGN KEY (`metodoPago_idmetodoPago`) REFERENCES `metodoPago` (`idmetodoPago`),
  CONSTRAINT `fk_compra_Usuario1` FOREIGN KEY (`Usuario_identificador`) REFERENCES `Usuario` (`identificador`)
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
  `compra_idcompra` int NOT NULL,
  `funcion_idfuncion` int NOT NULL,
  `butaca_idbutaca` int NOT NULL,
  `articulo_idarticulo` int NOT NULL,
  PRIMARY KEY (`articulo_idarticulo`),
  KEY `fk_entrada_compra1_idx` (`compra_idcompra`),
  KEY `fk_entrada_funcion1_idx` (`funcion_idfuncion`),
  KEY `fk_entrada_butaca1_idx` (`butaca_idbutaca`),
  CONSTRAINT `fk_entrada_articulo1` FOREIGN KEY (`articulo_idarticulo`) REFERENCES `articulo` (`idarticulo`),
  CONSTRAINT `fk_entrada_butaca1` FOREIGN KEY (`butaca_idbutaca`) REFERENCES `butaca` (`idbutaca`),
  CONSTRAINT `fk_entrada_compra1` FOREIGN KEY (`compra_idcompra`) REFERENCES `compra` (`idcompra`),
  CONSTRAINT `fk_entrada_funcion1` FOREIGN KEY (`funcion_idfuncion`) REFERENCES `funcion` (`idfuncion`)
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
  `sala_idsala` int NOT NULL,
  `Pelicula_nombre` varchar(45) NOT NULL,
  `horario` datetime DEFAULT NULL,
  `idfuncion` int NOT NULL,
  PRIMARY KEY (`idfuncion`),
  KEY `fk_sala_has_Pelicula_Pelicula1_idx` (`Pelicula_nombre`),
  KEY `fk_sala_has_Pelicula_sala1_idx` (`sala_idsala`),
  CONSTRAINT `fk_sala_has_Pelicula_Pelicula1` FOREIGN KEY (`Pelicula_nombre`) REFERENCES `Pelicula` (`nombre`),
  CONSTRAINT `fk_sala_has_Pelicula_sala1` FOREIGN KEY (`sala_idsala`) REFERENCES `sala` (`idsala`)
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
  `idmetodoPago` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descuento(%)` int DEFAULT NULL,
  PRIMARY KEY (`idmetodoPago`)
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
  `articulo_idarticulo` int NOT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`articulo_idarticulo`),
  KEY `fk_producto_articulo1_idx` (`articulo_idarticulo`),
  CONSTRAINT `fk_producto_articulo1` FOREIGN KEY (`articulo_idarticulo`) REFERENCES `articulo` (`idarticulo`)
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
  `compra_idcompra` int NOT NULL,
  `cantidad` varchar(45) DEFAULT NULL,
  `producto_articulo_idarticulo` int NOT NULL,
  PRIMARY KEY (`compra_idcompra`,`producto_articulo_idarticulo`),
  KEY `fk_producto_has_compra_compra1_idx` (`compra_idcompra`),
  KEY `fk_producto_has_compra_producto1_idx` (`producto_articulo_idarticulo`),
  CONSTRAINT `fk_producto_has_compra_compra1` FOREIGN KEY (`compra_idcompra`) REFERENCES `compra` (`idcompra`),
  CONSTRAINT `fk_producto_has_compra_producto1` FOREIGN KEY (`producto_articulo_idarticulo`) REFERENCES `producto` (`articulo_idarticulo`)
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
-- Table structure for table `producto_has_promociones`
--

-- Primero eliminar la tabla existente
DROP TABLE IF EXISTS `producto_has_promociones`;

-- Crear la nueva tabla con el nombre actualizado
CREATE TABLE `articuloHasPromocion` (
  `articulo_idarticulo` int NOT NULL,
  `promociones_idpromociones` int NOT NULL,
  PRIMARY KEY (`articulo_idarticulo`,`promociones_idpromociones`),
  KEY `fk_articuloHasPromocion_promociones1_idx` (`promociones_idpromociones`),
  KEY `fk_articuloHasPromocion_articulo1_idx` (`articulo_idarticulo`),
  CONSTRAINT `fk_articuloHasPromocion_articulo1` FOREIGN KEY (`articulo_idarticulo`) REFERENCES `articulo` (`idarticulo`),
  CONSTRAINT `fk_articuloHasPromocion_promociones1` FOREIGN KEY (`promociones_idpromociones`) REFERENCES `promociones` (`idpromociones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `promociones`
--

DROP TABLE IF EXISTS `promociones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promociones` (
  `idpromociones` int NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `caracteristicas` varchar(45) DEFAULT NULL,
  `Rango_idRango` int NOT NULL,
  PRIMARY KEY (`idpromociones`),
  KEY `fk_promociones_Rango1_idx` (`Rango_idRango`),
  CONSTRAINT `fk_promociones_Rango1` FOREIGN KEY (`Rango_idRango`) REFERENCES `Rango` (`idRango`)
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
  `idsala` int NOT NULL,
  `capacidad` varchar(45) DEFAULT NULL,
  `Lugar_idLugar` int NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idsala`,`Lugar_idLugar`),
  KEY `fk_sala_Lugar_idx` (`Lugar_idLugar`),
  CONSTRAINT `fk_sala_Lugar` FOREIGN KEY (`Lugar_idLugar`) REFERENCES `Lugar` (`idLugar`)
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

-- Crear tabla de notificaciones
CREATE TABLE `notificaciones` (
  `idnotificacion` int NOT NULL AUTO_INCREMENT,
  `Usuario_identificador` int NOT NULL,
  `funcion_idfuncion` int NOT NULL,
  `mensaje` varchar(255) NOT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `leida` tinyint DEFAULT '0',
  `fecha_envio` date NOT NULL,
  PRIMARY KEY (`idnotificacion`),
  KEY `fk_notificaciones_Usuario1_idx` (`Usuario_identificador`),
  KEY `fk_notificaciones_funcion1_idx` (`funcion_idfuncion`),
  CONSTRAINT `fk_notificaciones_Usuario1` FOREIGN KEY (`Usuario_identificador`) REFERENCES `Usuario` (`identificador`),
  CONSTRAINT `fk_notificaciones_funcion1` FOREIGN KEY (`funcion_idfuncion`) REFERENCES `funcion` (`idfuncion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-16 11:57:12
