CREATE DATABASE  IF NOT EXISTS `curso-udemy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `curso-udemy`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  id bigint NOT NULL,
  title varchar(255) DEFAULT NULL,
  price decimal(65,2) NOT NULL,
  author varchar(255) DEFAULT NULL,
  launch_Date datetime DEFAULT NULL,
  PRIMARY KEY (id)
);


