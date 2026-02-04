-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for osx10.10 (x86_64)
--
-- Host: localhost    Database: hermandad
-- ------------------------------------------------------
-- Server version	10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP DATABASE IF EXISTS hermandad;
CREATE DATABASE hermandad;
USE hermandad;

--
-- Table structure for table `usuario`
--
DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `dni` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `activo` tinyint(4) NOT NULL DEFAULT 1,
  `rol` set('admin','usuario','capataz','junta') NOT NULL DEFAULT 'usuario',
  `fecha_ingreso` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_usuario_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--
LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Juan','11111111A','Calle Real 1','600000001','juan@email.com','Juan',1,'usuario','2026-03-26'),(2,'Pedro','22222222B','Calle Real 2','600000002','pedro@email.com','Pedro',0,'usuario','2026-03-26'),(3,'Luis','33333333C','Calle Real 3','600000003','luis@email.com','Luis',1,'usuario','2026-03-26'),(4,'Carlos','44444444D','Calle Real 4','600000004','carlos@email.com','Carlos',0,'capataz','2026-03-26'),(5,'Miguel','55555555E','Calle Real 5','600000005','miguel@email.com','Miguel',1,'usuario','2026-03-26'),(6,'Antonio','66666666F','Calle Real 6','600000006','antonio@email.com','Antonio',0,'junta','2026-03-26'),(7,'Manuel','77777777G','Calle Real 7','600000007','manuel@email.com','Manuel',1,'usuario','2026-03-26'),(8,'Javier','88888888H','Calle Real 8','600000008','javier@email.com','Javier',0,'usuario','2026-03-26'),(9,'rafa','99999999J','Calle Real 9','600000009','rafael@email.com','rafa',1,'admin','2026-03-26'),(10,'Diego','10101010K','Calle Real 10','600000010','diego@email.com','Diego',0,'usuario','2026-03-26'),(11,'jose','00000000A','Calle Real 11','600000011','jose@email.com','jose',1,'capataz','2026-03-26'),(12,'migue','00000001A','Calle Real 12','600000012','migue@email.com','migue',0,'junta','2026-03-26'),(13,'Cristian','11111111X','Calle Real 1','600000111','cristian@email.com','Cristian',1,'usuario','2026-03-26'),(14,'Chemar','12312612A','Calle Falsa 123','123456789','chema@email.com','$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',1,'junta','2026-02-01');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--
DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--
LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'Cruz de Guia','Grupo que habre el cortejo'),(2,'Nazarenos Cristo','Nazarenos que procesionan delante del paso de Cristo'),(3,'Acolitos Cristo','Parte de los nazarenos que hacen de unión con los nazarenos y el paso de cristo'),(4,'Paso Cristo','Paso procesional del cristo'),(5,'Banda Cristo','banda de cornetas y tambores'),(6,'Nazarenos Virgen','Nazarenos que procesionan delante de el paso de virgen'),(7,'Acolitos Virgen','Parte de los nazarenos que hacen de unión ente los nazarenos de la virgen y el paso de la virgen'),(8,'Paso Virgen','Paso procesional de la virgen'),(9,'Banda Virgen','banda de música de la virgen');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tramo`
--
DROP TABLE IF EXISTS `tramo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tramo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salida` date NOT NULL,
  `entrada` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tramo`
--
LOCK TABLES `tramo` WRITE;
/*!40000 ALTER TABLE `tramo` DISABLE KEYS */;
INSERT INTO `tramo` VALUES (1,'2026-03-29','2026-03-30');
/*!40000 ALTER TABLE `tramo` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `tramo_grupo_usuario`
--
DROP TABLE IF EXISTS `tramo_grupo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tramo_grupo_usuario` (
  `id_tramo` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `funcion` set('musico','costalero','nazareno','administrativa') NOT NULL DEFAULT 'administrativa',
  KEY `tramo_usuario_FK` (`id_usuario`),
  KEY `tramo_grupo_FK` (`id_grupo`),
  KEY `tramo_salida_entrada_FK` (`id_tramo`),
  CONSTRAINT `tramo_grupo_FK` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tramo_salida_entrada_FK` FOREIGN KEY (`id_tramo`) REFERENCES `tramo` (`id`),
  CONSTRAINT `tramo_usuario_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tramo_grupo_usuario`
--
LOCK TABLES `tramo_grupo_usuario` WRITE;
/*!40000 ALTER TABLE `tramo_grupo_usuario` DISABLE KEYS */;
INSERT INTO `tramo_grupo_usuario` VALUES (1,1,1,'nazareno'),(1,2,2,'nazareno'),(1,3,3,'nazareno'),(1,4,4,'administrativa'),(1,5,5,'musico'),(1,6,6,'administrativa'),(1,7,7,'nazareno'),(1,8,8,'costalero'),(1,8,9,'administrativa'),(1,4,10,'costalero'),(1,8,11,'administrativa'),(1,9,12,'administrativa'),(1,9,13,'musico');
/*!40000 ALTER TABLE `tramo_grupo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--
DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `id_grupo` int(11) DEFAULT NULL,
  `titulo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ensayo_grupo_FK` (`id_grupo`),
  CONSTRAINT `ensayo_grupo_FK` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--
LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,'2026-01-31','Convocatoria de costaleros, para la igualá del paso del señor.',4,'Igualá'),(2,'2026-02-21','Ensayo Solidario de los costaleros del paso del señor',4,'Ensayo'),(3,'2026-03-06','Traslado Paso Cristo',4,'Traslado'),(4,'2026-03-14','Ensayo del paso de Cristo',4,'Ensayo'),(5,'2026-03-20','Ensayo del paso de Cristo',4,'Ensayo'),(6,'2026-03-21','Anual fiesta de la cerveza de la Hermandad del señor de los cojones y la puta virgen coño',NULL,'Fiesta de la cerveza');
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticia`
--
DROP TABLE IF EXISTS `noticia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `noticia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` text NOT NULL,
  `imagen` longtext DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticia`
--
LOCK TABLES `noticia` WRITE;
/*!40000 ALTER TABLE `noticia` DISABLE KEYS */;
INSERT INTO `noticia` VALUES (1,'Restauración del señor','2026-02-01','Concluye con éxito la restauración del Señor de la Humildad\n\nLa imagen del Señor de la Humildad ha regresado al culto tras culminar un meticuloso proceso de restauración que se ha prolongado durante varios meses y que ha permitido recuperar el esplendor original de esta venerada talla devocional.\n\nLos trabajos han sido realizados por un equipo especializado en conservación de imaginería religiosa, siguiendo criterios estrictos de respeto histórico y artístico. Durante la intervención se ha llevado a cabo una limpieza integral de la policromía, eliminando depósitos de suciedad acumulados con el paso del tiempo, así como la consolidación estructural de la madera para garantizar su estabilidad a largo plazo.\n\nAsimismo, se han corregido pequeñas fisuras provocadas por cambios de temperatura y humedad, y se ha procedido a una reintegración cromática puntual en aquellas zonas donde la policromía presentaba pérdidas, siempre utilizando técnicas reversibles y materiales compatibles con la obra original.\n\nEl resultado final devuelve al Señor de la Humildad una expresión serena y profunda, resaltando nuevamente los matices del modelado y la riqueza cromática que caracterizan a la imagen. Desde la hermandad se ha destacado que esta restauración no solo preserva el valor artístico de la talla, sino que refuerza su significado espiritual y patrimonial para los fieles.\n\nLa imagen será presentada oficialmente en un acto público de acción de gracias, previo a su reincorporación a los cultos y a la próxima estación de penitencia, un momento especialmente esperado por hermanos y devotos tras este importante hito en la conservación de su patrimonio',NULL);
/*!40000 ALTER TABLE `noticia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesiones`
--

DROP TABLE IF EXISTS `sesiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sesiones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `token` longtext NOT NULL,
  `fecha_emision` bigint(20) unsigned NOT NULL,
  `fecha_expiracion` bigint(20) unsigned NOT NULL,
  `finguer_print` longtext NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `sesiones_usuario_FK` (`id_usuario`),
  CONSTRAINT `sesiones_usuario_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesiones`
--

LOCK TABLES `sesiones` WRITE;
/*!40000 ALTER TABLE `sesiones` DISABLE KEYS */;
/*!40000 ALTER TABLE `sesiones` ENABLE KEYS */;
UNLOCK TABLES;

