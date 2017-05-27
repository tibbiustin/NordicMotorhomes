-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: motorhome
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL AUTO_INCREMENT,
  `start_reservation` datetime DEFAULT NULL,
  `finish_reservation` datetime DEFAULT NULL,
  `reservation_customer_id` int(11) DEFAULT NULL,
  `vehicle_reservation` int(11) DEFAULT NULL,
  `pickup_reservation` varchar(45) DEFAULT NULL,
  `dropoff_reservation` varchar(45) DEFAULT NULL,
  `price_reservation` varchar(45) DEFAULT NULL,
  `bike_reservation` tinyint(4) DEFAULT NULL,
  `child_reservation` tinyint(4) DEFAULT NULL,
  `picnic_reservation` tinyint(4) DEFAULT NULL,
  `payment_type_reservation` varchar(45) DEFAULT NULL,
  `paid_reservation` tinyint(4) DEFAULT NULL,
  `fuel_level_over_half_reservation` tinyint(4) DEFAULT NULL,
  `mechanic_approval_reservation` tinyint(4) DEFAULT NULL,
  `mechanic_comments` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_reservation`),
  KEY `vehicle_reservation_idx` (`vehicle_reservation`),
  KEY `customer_reservation_idx` (`reservation_customer_id`),
  CONSTRAINT `customer_reservation` FOREIGN KEY (`reservation_customer_id`) REFERENCES `customer` (`id_customer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vehicle_reservation` FOREIGN KEY (`vehicle_reservation`) REFERENCES `vehicle` (`id_vehicle`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'2017-04-20 00:00:00','2017-05-01 00:00:00',2,1,'there','there','1500',0,1,1,'card',1,1,1,'works like a charm'),(2,'2017-04-21 00:00:00','2017-05-02 00:00:00',3,1,'there','there','3123',1,0,1,'card',1,1,1,'fine'),(4,'2017-05-01 00:00:00','2017-05-05 00:00:00',1,1,'hq','hq','1000',0,1,1,'card',1,1,1,'');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-27 23:39:05
