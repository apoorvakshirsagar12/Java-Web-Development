CREATE DATABASE  IF NOT EXISTS `my_stock` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `my_stock`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: my_stock
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `stock_symbol` varchar(20) NOT NULL,
  `qty` int(11) NOT NULL,
  `price` double NOT NULL,
  `amt` double NOT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `action` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (38,25,'AMZN',5,1179.14,5895.7,'2017-12-18 03:01:37','purchase'),(39,25,'FB',2,180.18,360.36,'2017-12-18 03:02:09','purchase'),(40,25,'AR',16,17.66,307.9904,'2017-12-18 03:06:33','purchase'),(41,26,'EBAY',15,38.37,575.55,'2017-12-18 03:16:44','purchase'),(42,26,'TWTR',10,22.22,222.2,'2017-12-18 03:17:03','purchase'),(43,26,'EBAY',9,38.37,345.33,'2017-12-18 03:17:49','sell');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_requests_manager`
--

DROP TABLE IF EXISTS `stock_requests_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_requests_manager` (
  `req_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `symbol` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amt` double DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `mgr_id` int(11) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`req_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_requests_manager`
--

LOCK TABLES `stock_requests_manager` WRITE;
/*!40000 ALTER TABLE `stock_requests_manager` DISABLE KEYS */;
INSERT INTO `stock_requests_manager` VALUES (9,25,'AR',17.66,300,16,28,'2017-12-18 03:06:33','purchase','approved'),(10,25,'NA',NULL,200,0,28,NULL,'sell','pending'),(11,26,'NA',NULL,200,0,27,NULL,'purchase','pending'),(12,26,'EBAY',38.37,400,9,27,'2017-12-18 03:17:49','sell','approved');
/*!40000 ALTER TABLE `stock_requests_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user` (
  `user_id` int(11) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  KEY `fk_userid_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (25,100000,'2017-12-18 02:58:39'),(26,100000,'2017-12-18 02:59:05'),(28,0,'2017-12-18 03:00:13'),(25,94104.3,'2017-12-18 03:01:37'),(25,93743.94,'2017-12-18 03:02:09'),(25,93435.9496,'2017-12-18 03:06:33'),(28,25.4304,'2017-12-18 03:06:33'),(27,0,'2017-12-18 03:15:04'),(26,99424.45,'2017-12-18 03:16:44'),(26,99202.25,'2017-12-18 03:17:03'),(26,99513.047,'2017-12-18 03:17:49'),(27,34.533,'2017-12-18 03:17:49');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_stock`
--

DROP TABLE IF EXISTS `user_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_stock` (
  `uid` int(11) NOT NULL,
  `stock_symbol` varchar(45) DEFAULT NULL,
  `qty` int(10) DEFAULT NULL,
  KEY `fk_uid_idx` (`uid`),
  CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `users` (`U_Userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_stock`
--

LOCK TABLES `user_stock` WRITE;
/*!40000 ALTER TABLE `user_stock` DISABLE KEYS */;
INSERT INTO `user_stock` VALUES (25,'AMZN',5),(25,'FB',2),(25,'AR',16),(26,'EBAY',6),(26,'TWTR',10);
/*!40000 ALTER TABLE `user_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `U_Userid` int(11) NOT NULL AUTO_INCREMENT,
  `U_UserName` varchar(45) DEFAULT NULL,
  `U_Password` varchar(45) DEFAULT NULL,
  `U_Role` varchar(45) DEFAULT NULL,
  `U_Status` varchar(45) DEFAULT NULL,
  `U_FirstName` varchar(45) DEFAULT NULL,
  `U_LastName` varchar(45) DEFAULT NULL,
  `U_Phone` varchar(45) DEFAULT NULL,
  `U_Address` varchar(45) DEFAULT NULL,
  `mgr_id` int(11) DEFAULT NULL,
  `mgt_fees` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`U_Userid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'apoorva.12993@gmail.com','apoorva','admin','approved','Apoorva','Kshirsagar','5183132983','870,washington ave',0,0,0),(25,'ajoshi@gmail.com','akshay','user','approved','Akshay','Joshi','5182983123','730, Morris Street\r\nApt #2',28,0,93435.9496),(26,'diksha@gmail.com','diksha','user','approved','Diskha','Jaiswal','1234987634','Washington Avenue',27,0,99513.047),(27,'renu@gmail.com','renu','manager','approved','Renu','Bhingare','9423555083','Mumbai,India',0,10,34.533),(28,'anuja@gmail.com','anuja','manager','approved','Anuja','Kshirsagar','9423555093','Nashik,India',0,9,25.4304),(29,'abhishek@gmail.com','abhishek','manager','pending','Abhishek','Rai','1234512345','Western Avenue',0,14,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-17 22:29:56
