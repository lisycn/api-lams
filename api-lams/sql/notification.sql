-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: lams
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `notification`
--

USE `lams`;
DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `message` longtext,
  `notification_type` varchar(255) DEFAULT NULL,
  `template_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `provider` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notification_1_idx` (`provider`),
  CONSTRAINT `fk_notification_1` FOREIGN KEY (`provider`) REFERENCES `notification_provider` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_log`
--

DROP TABLE IF EXISTS `notification_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_sent` bigint(20) unsigned DEFAULT NULL,
  `log_message` varchar(255) DEFAULT NULL,
  `log_response` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `notification_type` varchar(255) DEFAULT NULL,
  `resent_count` bigint(20) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `to_email` varchar(255) DEFAULT NULL,
  `notification_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notification_log_1_idx` (`notification_id`),
  CONSTRAINT `fk_notification_log_1` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_log`
--

LOCK TABLES `notification_log` WRITE;
/*!40000 ALTER TABLE `notification_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_provider`
--

DROP TABLE IF EXISTS `notification_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_provider` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `notification_type` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `property` varchar(255) DEFAULT NULL,
  `request_url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_provider`
--

LOCK TABLES `notification_provider` WRITE;
/*!40000 ALTER TABLE `notification_provider` DISABLE KEYS */;
INSERT INTO `notification_provider` VALUES (1,1,'2018-03-11 16:40:59','',NULL,NULL,'EMAIL',NULL,'com.lams.notification.email.vfinance',NULL,NULL,'VFinance'),(2,1,'2018-03-11 16:51:51','',NULL,NULL,'SMS',NULL,'com.lams.notification.sms.textguru',NULL,NULL,'TextGuru');
/*!40000 ALTER TABLE `notification_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_template`
--

DROP TABLE IF EXISTS `notification_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notification_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_template`
--

LOCK TABLES `notification_template` WRITE;
/*!40000 ALTER TABLE `notification_template` DISABLE KEYS */;
INSERT INTO `notification_template` VALUES (1,1,'2018-03-11 16:44:49','',1,NULL,'EMAIL','email.ftl','EMAIL');
/*!40000 ALTER TABLE `notification_template` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-11 17:15:34