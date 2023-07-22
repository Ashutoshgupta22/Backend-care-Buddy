-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: localhost    Database: mitrazMobileDB
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `confirmation_token_nurse`
--

DROP TABLE IF EXISTS `confirmation_token_nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `confirmation_token_nurse` (
  `id` bigint NOT NULL,
  `confirmed_at` datetime(6) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `expires_at` datetime(6) NOT NULL,
  `token` varchar(255) NOT NULL,
  `nurse_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdpijrbahc4gbl65ifog7v7oir` (`nurse_id`),
  CONSTRAINT `FKdpijrbahc4gbl65ifog7v7oir` FOREIGN KEY (`nurse_id`) REFERENCES `nurse` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confirmation_token_nurse`
--

LOCK TABLES `confirmation_token_nurse` WRITE;
/*!40000 ALTER TABLE `confirmation_token_nurse` DISABLE KEYS */;
INSERT INTO `confirmation_token_nurse` VALUES (9,'2023-04-29 21:23:26.761348','2023-04-29 21:23:26.761252','2023-04-29 21:38:26.761318','04ea0fa8-4dbf-499c-a707-60c88643919d',18),(10,'2023-04-29 21:37:50.071971','2023-04-29 21:37:50.071908','2023-04-29 21:52:50.071943','ed114ce0-5dc1-49b3-a668-3e298f1e282c',19);
/*!40000 ALTER TABLE `confirmation_token_nurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `confirmation_token_sequence`
--

DROP TABLE IF EXISTS `confirmation_token_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `confirmation_token_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confirmation_token_sequence`
--

LOCK TABLES `confirmation_token_sequence` WRITE;
/*!40000 ALTER TABLE `confirmation_token_sequence` DISABLE KEYS */;
INSERT INTO `confirmation_token_sequence` VALUES (31),(1);
/*!40000 ALTER TABLE `confirmation_token_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `confirmation_token_user`
--

DROP TABLE IF EXISTS `confirmation_token_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `confirmation_token_user` (
  `id` bigint NOT NULL,
  `confirmed_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `expires_at` datetime(6) NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2wv9fkdeoihoeg8d4dts6ldxb` (`user_id`),
  CONSTRAINT `FK2wv9fkdeoihoeg8d4dts6ldxb` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confirmation_token_user`
--

LOCK TABLES `confirmation_token_user` WRITE;
/*!40000 ALTER TABLE `confirmation_token_user` DISABLE KEYS */;
INSERT INTO `confirmation_token_user` VALUES (30,NULL,'2023-06-15 01:14:25.509641','2023-06-15 01:29:25.509681','0a379a4b-bcf8-4a7e-8071-7d981f6fefca',45);
/*!40000 ALTER TABLE `confirmation_token_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (11),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurse`
--

DROP TABLE IF EXISTS `nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `latitude` double NOT NULL,
  `locked` bit(1) NOT NULL,
  `longitude` double NOT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `firebase_token` varchar(255) DEFAULT NULL,
  `rating` double NOT NULL,
  `biography` text,
  `experience` int NOT NULL,
  `patient_no` int NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `qualifications` varchar(500) DEFAULT NULL,
  `specialities` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse`
--

LOCK TABLES `nurse` WRITE;
/*!40000 ALTER TABLE `nurse` DISABLE KEYS */;
INSERT INTO `nurse` VALUES (5,18,'Millie Bobby Brown','millie@brown.com',NULL,_binary '\0',12.334455098,_binary '\0',-98.4488822,NULL,NULL,'{\n    \"firebaseToken\" : \"TOKEN\"\n}',0,NULL,0,0,NULL,NULL,NULL),(6,19,'Saidie sink','sink@sadie.com',NULL,_binary '\0',-2.03622198,_binary '\0',-58.57916739,NULL,NULL,NULL,0,NULL,0,0,NULL,NULL,NULL),(8,32,'Bruce Wayne','wayne@bruce.com',NULL,_binary '\0',122.03674098,_binary '\0',8.437988226739,NULL,'94043',NULL,4.5,NULL,0,0,NULL,NULL,NULL),(9,28,'jhon','abc@gmail.com','$2a$10$dwaYu58E.jyyzBebWhiL9ukSKBIaO/sBiyODS7dJcT.r9fSiIb422',_binary '\0',0,_binary '\0',0,'NURSE','94043',NULL,4,NULL,0,0,NULL,NULL,NULL),(18,28,'daniela','asparkofficial@gmail.com','$2a$10$tj0/lbzIfF6VyK.tl9vubufEh6.6a/sIfLbuXil6d5Yfd0ZMnrS0q',_binary '\0',0,_binary '\0',0,'NURSE','94043','cW_XOf4ZSoOVhwG5ldiwh7:APA91bG37FEsmxzYl4tpd9Gr3iOMW54vDX5FkAKPtKJrCYIoAMX1-PVHmK3WNe08NFTP_LPiGd1zE9Q59bM1ROtet99bIkOVnRqPGxCLmk5c4i4g1BJTmpJAghIhDPUyTbgs4pP4GkbB',4.6,NULL,0,0,NULL,NULL,NULL),(19,32,'sophie','asgu21is@cmrit.ac.in','$2a$10$xTZLOSvGx2a5fBN4z.JIveuFJ5JuF4M3VIGH9VK2r5N/kAzcu8XaK',_binary '\0',99.4023,_binary '\0',-12.774655,'NURSE','94043',NULL,4.5,NULL,0,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `nurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `locked` bit(1) NOT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `firebase_token` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (45,65,'John Doe','asgu21is@cmrit.ac.in','$2a$10$O96hzUsP.PLLso6KaekKTeE7W6iMuTHPz1PHxxLCHhAGbuSxu7qau',_binary '\0',_binary '\0','USER',24.4433222,-72.3345119877,'94043','f4p03QxKT8eqC5ziha_wzL:APA91bGqVza26whLMqfRwOQFxV4mz9_EqCuXlqpnmVfMAD2ARDSdXqWVlVGaQhXHXhpFx2WINSIQbg8mTGTaK-yuOLWN-LZDyMoTcqBZ3E00hd0KEoG-TEQH86UtfoPhavcrAbNqr_Km',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_sequence`
--

DROP TABLE IF EXISTS `user_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_sequence`
--

LOCK TABLES `user_sequence` WRITE;
/*!40000 ALTER TABLE `user_sequence` DISABLE KEYS */;
INSERT INTO `user_sequence` VALUES (2);
/*!40000 ALTER TABLE `user_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-23  2:35:47
