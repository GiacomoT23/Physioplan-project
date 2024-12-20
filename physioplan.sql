CREATE DATABASE  IF NOT EXISTS `physioplan` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `physioplan`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: physioplan
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `esercitazioni`
--

DROP TABLE IF EXISTS `esercitazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `esercitazioni` (
  `nomeUtente` varchar(45) NOT NULL,
  `nomeEsercizio` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `ripetizioni` int(11) DEFAULT NULL,
  `serieConsigliate` int(11) DEFAULT NULL,
  `serieFatte` int(11) DEFAULT NULL,
  `difficoltaMedia` double DEFAULT NULL,
  PRIMARY KEY (`nomeUtente`,`nomeEsercizio`,`data`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `esercitazioni`
--

LOCK TABLES `esercitazioni` WRITE;
/*!40000 ALTER TABLE `esercitazioni` DISABLE KEYS */;
INSERT INTO `esercitazioni` VALUES ('utente2','estensione attiva','2021-02-18',10,6,5,6),('utente2','estensione attiva','2021-02-19',10,6,4,7),('utente2','flessione ginocchio','2021-02-18',12,6,4,6),('utente2','flessione ginocchio','2021-02-19',12,6,5,4.5),('utente2','squat','2021-02-19',8,3,3,7),('utente2','squat','2021-02-20',10,4,2,5.5),('utente@gmail.com','estensione attiva','2021-02-18',10,5,3,7.33333333333333),('utente@gmail.com','estensione attiva','2021-02-20',10,5,3,6),('utente@gmail.com','estensione attiva','2021-02-21',10,5,4,3.745),('utente@gmail.com','flessione ginocchio','2021-02-17',10,6,5,8.2),('utente@gmail.com','flessione ginocchio','2021-02-18',10,6,5,7.3),('utente@gmail.com','flessione ginocchio','2021-02-19',10,6,5,6.5),('utente@gmail.com','flessione ginocchio','2021-02-20',10,6,5,4),('utente@gmail.com','squat','2021-02-18',12,3,4,7),('utente@gmail.com','squat','2021-02-19',10,4,4,6.25),('utente@gmail.com','squat','2021-02-20',10,4,4,6),('utente@gmail.com','squat','2021-02-21',12,6,5,5),('utente@gmail.com','squat','2021-02-22',12,5,3,6);
/*!40000 ALTER TABLE `esercitazioni` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-15 11:43:52
/*
-- Query: SELECT * FROM physioplan.esercitazioni
LIMIT 0, 1000

-- Date: 2021-02-15 11:45
*/
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente2','estensione attiva','2021-02-18',10,6,5,6);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente2','estensione attiva','2021-02-19',10,6,4,7);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente2','flessione ginocchio','2021-02-18',12,6,4,6);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente2','flessione ginocchio','2021-02-19',12,6,5,4.5);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente2','squat','2021-02-19',8,3,3,7);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente2','squat','2021-02-20',10,4,2,5.5);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','estensione attiva','2021-02-18',10,5,3,7.33333333333333);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','estensione attiva','2021-02-20',10,5,3,6);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','estensione attiva','2021-02-21',10,5,4,3.745);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','flessione ginocchio','2021-02-17',10,6,5,8.2);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','flessione ginocchio','2021-02-18',10,6,5,7.3);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','flessione ginocchio','2021-02-19',10,6,5,6.5);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','flessione ginocchio','2021-02-20',10,6,5,4);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','squat','2021-02-18',12,3,4,7);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','squat','2021-02-19',10,4,4,6.25);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','squat','2021-02-20',10,4,4,6);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','squat','2021-02-21',12,6,5,5);
INSERT INTO `esercitazioni` (`nomeUtente`,`nomeEsercizio`,`data`,`ripetizioni`,`serieConsigliate`,`serieFatte`,`difficoltaMedia`) VALUES ('utente@gmail.com','squat','2021-02-22',12,5,3,6);
