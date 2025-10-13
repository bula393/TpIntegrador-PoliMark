-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: polimark
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
-- Table structure for table `butaca`
--

DROP TABLE IF EXISTS `butaca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `butaca` (
  `compra_idcompra` int NOT NULL,
  `funcion_cartelera_Pelicula_nombre` varchar(45) NOT NULL,
  `funcion_sala_idsala` int NOT NULL,
  `ubicacion` varchar(45) DEFAULT NULL,
  `desmanda` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`compra_idcompra`,`funcion_cartelera_Pelicula_nombre`,`funcion_sala_idsala`),
  KEY `fk_compra_has_funcion_funcion1_idx` (`funcion_cartelera_Pelicula_nombre`,`funcion_sala_idsala`),
  KEY `fk_compra_has_funcion_compra1_idx` (`compra_idcompra`),
  CONSTRAINT `fk_compra_has_funcion_compra1` FOREIGN KEY (`compra_idcompra`) REFERENCES `compra` (`idcompra`),
  CONSTRAINT `fk_compra_has_funcion_funcion1` FOREIGN KEY (`funcion_cartelera_Pelicula_nombre`, `funcion_sala_idsala`) REFERENCES `funcion` (`cartelera_Pelicula_nombre`, `sala_idsala`)
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
-- Table structure for table `cartelera`
--

DROP TABLE IF EXISTS `cartelera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartelera` (
  `Pelicula_nombre` varchar(45) NOT NULL,
  `Lugar_idLugar` int NOT NULL,
  `fechaEntradaCartelera` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Pelicula_nombre`,`Lugar_idLugar`),
  KEY `fk_Pelicula_has_Lugar_Lugar1_idx` (`Lugar_idLugar`),
  KEY `fk_Pelicula_has_Lugar_Pelicula1_idx` (`Pelicula_nombre`),
  CONSTRAINT `fk_Pelicula_has_Lugar_Lugar1` FOREIGN KEY (`Lugar_idLugar`) REFERENCES `Lugar` (`idLugar`),
  CONSTRAINT `fk_Pelicula_has_Lugar_Pelicula1` FOREIGN KEY (`Pelicula_nombre`) REFERENCES `Pelicula` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartelera`
--

LOCK TABLES `cartelera` WRITE;
/*!40000 ALTER TABLE `cartelera` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartelera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `idcompra` int NOT NULL,
  `Estado` varchar(45) DEFAULT NULL,
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
-- Table structure for table `formatosDisponibles`
--

DROP TABLE IF EXISTS `formatosDisponibles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formatosDisponibles` (
  `formatosSala_idformatosSala` int NOT NULL,
  `Pelicula_nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`formatosSala_idformatosSala`,`Pelicula_nombre`),
  KEY `fk_formatosSala_has_Pelicula_Pelicula1_idx` (`Pelicula_nombre`),
  KEY `fk_formatosSala_has_Pelicula_formatosSala1_idx` (`formatosSala_idformatosSala`),
  CONSTRAINT `fk_formatosSala_has_Pelicula_formatosSala1` FOREIGN KEY (`formatosSala_idformatosSala`) REFERENCES `formatosSala` (`idformatosSala`),
  CONSTRAINT `fk_formatosSala_has_Pelicula_Pelicula1` FOREIGN KEY (`Pelicula_nombre`) REFERENCES `Pelicula` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formatosDisponibles`
--

LOCK TABLES `formatosDisponibles` WRITE;
/*!40000 ALTER TABLE `formatosDisponibles` DISABLE KEYS */;
/*!40000 ALTER TABLE `formatosDisponibles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formatosSala`
--

DROP TABLE IF EXISTS `formatosSala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formatosSala` (
  `idformatosSala` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idformatosSala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formatosSala`
--

LOCK TABLES `formatosSala` WRITE;
/*!40000 ALTER TABLE `formatosSala` DISABLE KEYS */;
/*!40000 ALTER TABLE `formatosSala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcion`
--

DROP TABLE IF EXISTS `funcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcion` (
  `cartelera_Pelicula_nombre` varchar(45) NOT NULL,
  `sala_idsala` int NOT NULL,
  `fechaInicioFuncion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cartelera_Pelicula_nombre`,`sala_idsala`),
  KEY `fk_funcion_sala1_idx` (`sala_idsala`),
  CONSTRAINT `fk_funcion_cartelera1` FOREIGN KEY (`cartelera_Pelicula_nombre`) REFERENCES `cartelera` (`Pelicula_nombre`),
  CONSTRAINT `fk_funcion_sala1` FOREIGN KEY (`sala_idsala`) REFERENCES `sala` (`idsala`)
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
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `idsala` int NOT NULL,
  `capacidad` varchar(45) DEFAULT NULL,
  `Lugar_idLugar` int NOT NULL,
  `formatosSala_idformatosSala` int NOT NULL,
  PRIMARY KEY (`idsala`,`Lugar_idLugar`),
  KEY `fk_sala_Lugar_idx` (`Lugar_idLugar`),
  KEY `fk_sala_formatosSala1_idx` (`formatosSala_idformatosSala`),
  CONSTRAINT `fk_sala_formatosSala1` FOREIGN KEY (`formatosSala_idformatosSala`) REFERENCES `formatosSala` (`idformatosSala`),
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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-13 12:14:24
