/*M!999999\- enable the sandbox mode */
-- MariaDB dump 10.19-12.1.2-MariaDB, for debian-linux-gnu (aarch64)
--
-- Host: localhost    Database: hermandad
-- ------------------------------------------------------
-- Server version	12.1.2-MariaDB-ubu2404

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

DROP DATABASE IF EXISTS hermandad;
CREATE DATABASE hermandad;
USE hermandad;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `usuario` VALUES
                          (2,'admin','23456789A','calle falsa 12','123123123','admin@email.com','$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',1,'admin','2026-02-01'),
                          (3,'junta','23456789B','calle falsa 12','123123124','junta@email.com','$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',1,'junta','2026-02-01'),
                          (4,'capataz','23456789C','calle falsa 12','123123125','capataz@email.com','$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',1,'capataz','2026-02-01'),
                          (17,'usuario','23456789V','Calle Falsa','123123123','ususario@email.com','$2a$10$O2Q4Oeu8oV.wAwtxqlp9ieAOoc69Upry62gmWY6cIK5iycg8JalFq',1,'usuario','2026-02-01'),
                          (20,'Rafael Nieto Campos','20621350X','Guadix','649233798','rafanietocampos@gmail.com','$2a$10$BAYsgYP8uUCcwNTjeARjK.jB4lEm22hj0/hts67oF7AndWTZwXRV.',1,'admin','2026-02-13');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `sesiones`
--

DROP TABLE IF EXISTS `sesiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
commit;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tramo`
--

DROP TABLE IF EXISTS `tramo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tramo` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `go_out` date NOT NULL,
                         `enter` date NOT NULL,
                         `description` varchar(100) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tramo`
--

LOCK TABLES `tramo` WRITE;
/*!40000 ALTER TABLE `tramo` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `tramo` VALUES
                        (2,'2026-02-10','2026-02-10','Salida procesional del Martes'),
                        (5,'2026-02-12','2026-02-12','Martes'),
                        (7,'2026-02-16','2026-02-16','Miercoles');
/*!40000 ALTER TABLE `tramo` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(100) NOT NULL,
                         `type` varchar(100) NOT NULL,
                         `base64` longtext NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `images_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(100) NOT NULL,
                         `description` text DEFAULT NULL,
                         `image_id` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `grupo_image_FK` (`image_id`),
                         CONSTRAINT `grupo_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `grupo` VALUES
                        (1,'Cruz de Guia','Grupo que habre el cortejo',NULL),
                        (2,'Nazarenos Cristo','Nazarenos que procesionan delante del paso de Cristo',NULL),
                        (3,'Acolitos Cristo','Parte de los nazarenos que hacen de unión con los nazarenos y el paso de cristo',NULL),
                        (4,'Paso Cristo','Paso procesional del cristo',NULL),
                        (5,'Banda Cristo','banda de cornetas y tambores',NULL),
                        (6,'Nazarenos Virgen','Nazarenos que procesionan delante de el paso de virgen',NULL),
                        (7,'Acolitos Virgen','Parte de los nazarenos que hacen de unión ente los nazarenos de la virgen y el paso de la virgen',NULL),
                        (8,'Paso Virgen','Paso actualizado',16),
                        (9,'Banda Virgen','banda de música de la virgen',NULL);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `fecha` date NOT NULL,
                          `description` varchar(100) NOT NULL,
                          `id_grupo` int(11) DEFAULT NULL,
                          `title` varchar(100) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `ensayo_grupo_FK` (`id_grupo`),
                          CONSTRAINT `ensayo_grupo_FK` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `evento` VALUES
                         (1,'2026-01-31','Convocatoria de costaleros, para la igualá del paso del señor.',4,'Igualá'),
                         (2,'2026-02-21','Ensayo Solidario de los costaleros del paso del señor',4,'Ensayo'),
                         (3,'2026-03-06','Traslado Paso Cristo',4,'Traslado'),
                         (4,'2026-03-14','Ensayo del paso de Cristo',4,'Ensayo'),
                         (5,'2026-03-20','Ensayo del paso de Cristo',4,'Ensayo'),
                         (6,'2026-03-21','Anual fiesta de la cerveza de la Hermandad del señor de los cojones y la puta virgen coño',NULL,'Fiesta de la cerveza'),
                         (8,'2026-02-11','Ensayo de los tios grandes',4,'Ensayo');
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Dumping data for table `noticia`
--

DROP TABLE IF EXISTS `noticia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticia` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `title` varchar(100) NOT NULL,
                           `fecha` date DEFAULT curdate(),
                           `description` text NOT NULL,
                           `image_id` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `noticia_image_FK` (`image_id`),
                           CONSTRAINT `noticia_image_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticia`
--

LOCK TABLES `noticia` WRITE;
/*!40000 ALTER TABLE `noticia` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `noticia` VALUES
                          (8,'rafa','2026-02-13','rafa',5),
                          (9,'rayo','2026-02-13','rayo cachau',9);
/*!40000 ALTER TABLE `noticia` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `tramo_grupo_usuario`
--

DROP TABLE IF EXISTS `tramo_grupo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tramo_grupo_usuario` (
                                       `id_tramo` int(11) NOT NULL,
                                       `id_grupo` int(11) NOT NULL,
                                       `id_usuario` int(11) NOT NULL,
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `uk_tramo_grupo_usuario` (`id_tramo`,`id_grupo`,`id_usuario`),
                                       KEY `tramo_usuario_FK` (`id_usuario`),
                                       KEY `tramo_grupo_FK` (`id_grupo`),
                                       KEY `tramo_salida_entrada_FK` (`id_tramo`),
                                       CONSTRAINT `tramo_grupo_FK` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON DELETE CASCADE,
                                       CONSTRAINT `tramo_salida_entrada_FK` FOREIGN KEY (`id_tramo`) REFERENCES `tramo` (`id`),
                                       CONSTRAINT `tramo_usuario_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tramo_grupo_usuario`
--

LOCK TABLES `tramo_grupo_usuario` WRITE;
/*!40000 ALTER TABLE `tramo_grupo_usuario` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `tramo_grupo_usuario` VALUES
                                      (2,2,17,3),
                                      (2,4,4,4),
                                      (7,3,17,2);
/*!40000 ALTER TABLE `tramo_grupo_usuario` ENABLE KEYS */;
UNLOCK TABLES;
commit;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-02-14 18:38:58
