CREATE DATABASE  IF NOT EXISTS `lams` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `lams`;
-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: lams
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `address_mstr`
--

DROP TABLE IF EXISTS `address_mstr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address_mstr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `street_name` varchar(45) DEFAULT NULL,
  `land_mark` varchar(45) DEFAULT NULL,
  `pincode` varchar(45) DEFAULT NULL,
  `city_id` bigint(20) unsigned DEFAULT NULL,
  `created_by` bigint(20) unsigned NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `add_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_mstr`
--

LOCK TABLES `address_mstr` WRITE;
/*!40000 ALTER TABLE `address_mstr` DISABLE KEYS */;
INSERT INTO `address_mstr` VALUES (5,'sfsdfsdf','sfsdfsf','382350',4,1,NULL,1,'2018-03-11 19:45:33','',1,1),(6,NULL,NULL,'382350',4,1,NULL,1,'2018-03-11 14:16:28','',1,2),(7,'sfsdfsdf','sfsdfsf','382350',4,1,NULL,NULL,NULL,'',1,2),(8,'sfsdfsdf','sfsdfsf','382350',4,1,NULL,NULL,NULL,'',1,2),(9,'sfsdfsdf','sfsdfsf','382350',4,1,NULL,1,'2018-03-11 19:39:27','',1,2),(10,'sfsdfsdf','sfsdfsf','382350',4,1,NULL,NULL,NULL,'',1,2);
/*!40000 ALTER TABLE `address_mstr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_type_mstr`
--

DROP TABLE IF EXISTS `application_type_mstr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_type_mstr` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_type_mstr`
--

LOCK TABLES `application_type_mstr` WRITE;
/*!40000 ALTER TABLE `application_type_mstr` DISABLE KEYS */;
INSERT INTO `application_type_mstr` VALUES (5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21);
/*!40000 ALTER TABLE `application_type_mstr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applications` (
  `id` bigint(45) NOT NULL AUTO_INCREMENT,
  `application_type_id` bigint(20) DEFAULT NULL,
  `loan_type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `outstanding_amount` double DEFAULT NULL,
  `emi` double DEFAULT NULL,
  `balance_tenure` double DEFAULT NULL,
  `close_before_disbsmnt` bigint(20) DEFAULT NULL,
  `tenure` int(11) DEFAULT NULL,
  `property_cost` double DEFAULT NULL,
  `property_address` varchar(200) DEFAULT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `bank_acc_number` varchar(45) DEFAULT NULL,
  `loan_amount` double DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_application_type_mst_idx` (`application_type_id`),
  KEY `fk_loan_type_mst_idx` (`loan_type_id`),
  CONSTRAINT `fk_application_type_mst` FOREIGN KEY (`application_type_id`) REFERENCES `application_type_mstr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_loan_type_mst` FOREIGN KEY (`loan_type_id`) REFERENCES `loan_type_mstr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` VALUES (2,7,24,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-03-11 19:02:52',1,NULL,NULL,''),(3,5,23,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-03-11 19:46:22',1,NULL,NULL,''),(4,6,25,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-03-11 19:56:18',1,NULL,NULL,'');
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_mstr`
--

DROP TABLE IF EXISTS `bank_mstr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_mstr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_mstr`
--

LOCK TABLES `bank_mstr` WRITE;
/*!40000 ALTER TABLE `bank_mstr` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_mstr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city_mstr`
--

DROP TABLE IF EXISTS `city_mstr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city_mstr` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `state_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_city_mstr_2_idx` (`state_id`),
  CONSTRAINT `fk_city_mstr_1` FOREIGN KEY (`id`) REFERENCES `mstr_base` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_city_mstr_2` FOREIGN KEY (`state_id`) REFERENCES `state_mstr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city_mstr`
--

LOCK TABLES `city_mstr` WRITE;
/*!40000 ALTER TABLE `city_mstr` DISABLE KEYS */;
INSERT INTO `city_mstr` VALUES (4,3);
/*!40000 ALTER TABLE `city_mstr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_mstr`
--

DROP TABLE IF EXISTS `country_mstr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country_mstr` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_country_mstr_1` FOREIGN KEY (`id`) REFERENCES `mstr_base` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_mstr`
--

LOCK TABLES `country_mstr` WRITE;
/*!40000 ALTER TABLE `country_mstr` DISABLE KEYS */;
INSERT INTO `country_mstr` VALUES (2);
/*!40000 ALTER TABLE `country_mstr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_type_mstr`
--

DROP TABLE IF EXISTS `loan_type_mstr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan_type_mstr` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_type_mstr`
--

LOCK TABLES `loan_type_mstr` WRITE;
/*!40000 ALTER TABLE `loan_type_mstr` DISABLE KEYS */;
INSERT INTO `loan_type_mstr` VALUES (22),(23),(24),(25);
/*!40000 ALTER TABLE `loan_type_mstr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_audit_trail`
--

DROP TABLE IF EXISTS `login_audit_trail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_audit_trail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_type` bigint(20) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_audit_trail`
--

LOCK TABLES `login_audit_trail` WRITE;
/*!40000 ALTER TABLE `login_audit_trail` DISABLE KEYS */;
INSERT INTO `login_audit_trail` VALUES (1,'harshsuhagiya10@gmail.com','ecbcd340-eb2f-48e7-bd0e-11d52f883a42',1,1,'2018-02-19 03:04:43','\0'),(2,'patelakshay168@gmail.com','57ec9f4f-eb60-401c-b460-210b5cc32a28',2,2,'2018-02-19 03:08:41',''),(3,'admin@gmail.com','6fb57154-601f-4cf2-aef5-f2935f9d0af2',3,NULL,'2018-02-26 00:01:20','\0'),(4,'harshsuhagiya10@gmail.com','413c6dd9-a254-41f9-ad7a-3dc5e03adbc8',1,1,'2018-02-28 23:26:57','\0'),(5,'harshsuhagiya10@gmail.com','239fc406-8593-432d-9efe-8472a3038fd5',1,1,'2018-02-28 23:28:12','\0'),(6,'harshsuhagiya10@gmail.com','011f54c7-0488-4f09-92de-1bb768f20473',1,1,'2018-03-07 21:59:20','\0'),(7,'harshsuhagiya10@gmail.com','1caaf0c4-e53c-4152-b2ae-7cb8aafd7d34',1,1,'2018-03-07 21:59:36','\0'),(8,'harshsuhagiya10@gmail.com','9cda9783-4eba-4eda-b842-704b75efbcd8',1,1,'2018-03-07 22:02:13',''),(9,'harshsuhagiya10@gmail.com','a0505ece-3100-46c2-9d67-6010904f9fef',1,1,'2018-03-07 23:00:54',''),(10,'harshsuhagiya10@gmail.com','d00a5c50-997e-48cd-b0a8-96093fbe025a',1,1,'2018-03-10 18:11:55','\0'),(11,'harshsuhagiya10@gmail.com','88104b06-2776-4410-bc3a-f33eb871a114',1,2,'2018-03-11 18:39:29','\0'),(12,'harshsuhagiya10@gmail.com','b4a34489-6d13-41d6-8be7-1a9016d4a248',1,2,'2018-03-11 18:51:41','\0'),(13,'harshsuhagiya10@gmail.com','79c880e7-a9da-4083-ae6b-f6fac7dcef04',1,2,'2018-03-11 19:08:30','\0'),(14,'harshsuhagiya10@gmail.com','74e02250-3bf6-4ccd-b8b6-47ea0189ae95',1,2,'2018-03-11 19:44:40','');
/*!40000 ALTER TABLE `login_audit_trail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mstr_base`
--

DROP TABLE IF EXISTS `mstr_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstr_base` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `is_active` bit(1) NOT NULL DEFAULT b'1',
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mstr_base`
--

LOCK TABLES `mstr_base` WRITE;
/*!40000 ALTER TABLE `mstr_base` DISABLE KEYS */;
INSERT INTO `mstr_base` VALUES (1,'Mr.','Mr.','',1,'2018-02-28 23:26:49',1,'2018-02-28 23:26:49'),(2,'India','IND','',1,'2018-02-28 23:26:49',1,'2018-02-28 23:26:49'),(3,'Gujarat','GJ','',1,'2018-02-28 23:26:49',1,'2018-02-28 23:26:49'),(4,'Ahmedabad','AHM','',1,'2018-02-28 23:26:49',1,'2018-02-28 23:26:49'),(5,'Home Loans','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(6,'Loan Against Property','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(7,'Secured Business Loans','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(8,'Working Capital Loans','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(9,'Education Loans','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(10,'Car Loans','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(11,'OverDraft Facilities','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(12,'DropLine OverDraft Facilities','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(13,'Bank Guarantee','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(14,'CC Facilities','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(15,'Term Loans','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(16,'Loan Against FDs','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(17,'Loan Against Securities','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(18,'Project Finance','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(19,'Private Equity Finance','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(20,'Gold Loans','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(21,'Others','HL','',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(22,'Existing Loans',NULL,'',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(23,'Current Loan',NULL,'',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(24,'Bank Accounts',NULL,'\0',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49'),(25,'Closed Loans',NULL,'',1,'2018-03-11 23:26:49',1,'2018-03-11 23:26:49');
/*!40000 ALTER TABLE `mstr_base` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

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

--
-- Table structure for table `salutation_mstr`
--

DROP TABLE IF EXISTS `salutation_mstr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salutation_mstr` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_salutation_mstr_1` FOREIGN KEY (`id`) REFERENCES `mstr_base` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salutation_mstr`
--

LOCK TABLES `salutation_mstr` WRITE;
/*!40000 ALTER TABLE `salutation_mstr` DISABLE KEYS */;
INSERT INTO `salutation_mstr` VALUES (1);
/*!40000 ALTER TABLE `salutation_mstr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state_mstr`
--

DROP TABLE IF EXISTS `state_mstr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state_mstr` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `country_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_state_mstr_2_idx` (`country_id`),
  CONSTRAINT `fk_state_mstr_1` FOREIGN KEY (`id`) REFERENCES `mstr_base` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_state_mstr_2` FOREIGN KEY (`country_id`) REFERENCES `country_mstr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state_mstr`
--

LOCK TABLES `state_mstr` WRITE;
/*!40000 ALTER TABLE `state_mstr` DISABLE KEYS */;
INSERT INTO `state_mstr` VALUES (3,2);
/*!40000 ALTER TABLE `state_mstr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `is_accept_term_condition` bit(1) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `is_email_verified` bit(1) DEFAULT NULL,
  `gender` bigint(20) DEFAULT NULL,
  `is_otp_verified` bit(1) DEFAULT NULL,
  `user_type` bigint(20) DEFAULT NULL,
  `bank` bigint(20) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `employment_type` bigint(20) DEFAULT NULL,
  `employer_name` varchar(45) DEFAULT NULL,
  `employment_address` varchar(45) DEFAULT NULL,
  `gross_monthly_income` double DEFAULT NULL,
  `total_work_experience` int(11) DEFAULT NULL,
  `self_employed_type` bigint(20) DEFAULT NULL,
  `entity_name` varchar(45) DEFAULT NULL,
  `entity_type` bigint(20) DEFAULT NULL,
  `salutation` bigint(20) unsigned DEFAULT NULL,
  `communication_add` bigint(20) DEFAULT NULL,
  `permanent_add` bigint(20) DEFAULT NULL,
  `pan_card` varchar(45) DEFAULT NULL,
  `aadhar_card_no` varchar(45) DEFAULT NULL,
  `edu_qualification` varchar(45) DEFAULT NULL,
  `contact_number` varchar(45) DEFAULT NULL,
  `is_same_us_address` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_mstr_bank` (`bank`),
  KEY `fk_user_mst_per_add_idx` (`permanent_add`),
  KEY `fk_user_com_add_idx` (`communication_add`),
  CONSTRAINT `fk_user_comm_add` FOREIGN KEY (`communication_add`) REFERENCES `address_mstr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_mstr_bank` FOREIGN KEY (`bank`) REFERENCES `bank_mstr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_perm_add` FOREIGN KEY (`permanent_add`) REFERENCES `address_mstr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'harshsuhagiya10@gmail.com','9722232445','e10adc3949ba59abbe56e057f20f883e','','Harshit','Suhagiya','K','1994-06-13 00:00:00','\0',1,'\0',2,NULL,NULL,'2018-02-19 03:00:53',1,'2018-03-11 19:45:33','',1,'Test','India',2000,3,1,'TEst',3,1,NULL,NULL,'EPHPS89566L','6565989565665','BEIT','985656565656',''),(2,NULL,'patelakshay168@gmail.com','8460568956','0045a2fa4f6daed9084c5988bc58bed2','',NULL,NULL,NULL,NULL,'\0',NULL,'\0',2,NULL,NULL,'2018-02-19 03:08:30',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'admin','admin@gmail.com','8975849586','e8e99ce7fa9c15f522e21e2fb2cc752c',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,'sahil@test.com','9865269865','e10adc3949ba59abbe56e057f20f883e','','Sahil','Patel','D',NULL,'\0',NULL,'\0',1,NULL,3,'2018-02-26 01:28:12',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,'rahul@test.com','98656565656','e10adc3949ba59abbe56e057f20f883e','','Rahul','Gupta','L',NULL,'\0',NULL,'\0',1,NULL,3,'2018-02-26 01:28:50',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 20:13:26
