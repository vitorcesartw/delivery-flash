-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: deliverydb
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nota` int NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `restaurante_id` bigint DEFAULT NULL,
  `entregador_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_id` (`cliente_id`),
  KEY `restaurante_id` (`restaurante_id`),
  KEY `entregador_id` (`entregador_id`),
  CONSTRAINT `avaliacao_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `avaliacao_ibfk_2` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`),
  CONSTRAINT `avaliacao_ibfk_3` FOREIGN KEY (`entregador_id`) REFERENCES `entregador` (`id`),
  CONSTRAINT `avaliacao_chk_1` CHECK ((`nota` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacao`
--

LOCK TABLES `avaliacao` WRITE;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardapio`
--

DROP TABLE IF EXISTS `cardapio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cardapio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardapio`
--

LOCK TABLES `cardapio` WRITE;
/*!40000 ALTER TABLE `cardapio` DISABLE KEYS */;
/*!40000 ALTER TABLE `cardapio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardapio_itens`
--

DROP TABLE IF EXISTS `cardapio_itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cardapio_itens` (
  `cardapio_id` bigint NOT NULL,
  `itens_id_item` bigint NOT NULL,
  UNIQUE KEY `UKswb4sp3ib7pyc5gcufc393u09` (`itens_id_item`),
  KEY `FKh6gwdbfwnmyj1qotu36l41fdq` (`cardapio_id`),
  CONSTRAINT `FK9ne72g7iuispiv4gsbqmr41qd` FOREIGN KEY (`itens_id_item`) REFERENCES `item_cardapio` (`id_item`),
  CONSTRAINT `FKh6gwdbfwnmyj1qotu36l41fdq` FOREIGN KEY (`cardapio_id`) REFERENCES `cardapio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardapio_itens`
--

LOCK TABLES `cardapio_itens` WRITE;
/*!40000 ALTER TABLE `cardapio_itens` DISABLE KEYS */;
/*!40000 ALTER TABLE `cardapio_itens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `nivel_acesso` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `tipo_user` varchar(255) DEFAULT NULL,
  `dtype` varchar(31) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (14,'13758060761','vitor_c_g@yahoo.com.br','Rua Bahia, 112','Vitor Souza','22997198661',NULL,'123','restaurante','Cliente'),(15,'13758060761','vitor_c_g@yahoo.com','Rua Bahia, 112','Vitor Dias','22997198600',NULL,'123','cliente','Cliente');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_restaurante`
--

DROP TABLE IF EXISTS `cliente_restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_restaurante` (
  `cliente_id` bigint NOT NULL,
  `restaurante_id` bigint NOT NULL,
  PRIMARY KEY (`cliente_id`,`restaurante_id`),
  KEY `fk_restaurante_cliente` (`restaurante_id`),
  CONSTRAINT `fk_cliente_restaurante` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `fk_restaurante_cliente` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_restaurante`
--

LOCK TABLES `cliente_restaurante` WRITE;
/*!40000 ALTER TABLE `cliente_restaurante` DISABLE KEYS */;
INSERT INTO `cliente_restaurante` VALUES (14,6),(14,7),(14,8);
/*!40000 ALTER TABLE `cliente_restaurante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entregador`
--

DROP TABLE IF EXISTS `entregador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entregador` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `area_atuacao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `cnh` varchar(255) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `entregas_realizadas` int DEFAULT NULL,
  `meio_transporte` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregador`
--

LOCK TABLES `entregador` WRITE;
/*!40000 ALTER TABLE `entregador` DISABLE KEYS */;
INSERT INTO `entregador` VALUES (1,'Regiao','Entregadorrr','22992548766',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `entregador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_cardapio`
--

DROP TABLE IF EXISTS `item_cardapio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_cardapio` (
  `id_item` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` float NOT NULL,
  `visivel` bit(1) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `restaurante_id` bigint NOT NULL,
  PRIMARY KEY (`id_item`),
  KEY `fk_restaurante` (`restaurante_id`),
  CONSTRAINT `fk_restaurante` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_cardapio`
--

LOCK TABLES `item_cardapio` WRITE;
/*!40000 ALTER TABLE `item_cardapio` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_cardapio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_pedido`
--

DROP TABLE IF EXISTS `item_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `preco_total` float NOT NULL,
  `quantidade` int NOT NULL,
  `item_cardapio_id_item` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22dcmrgxp6mgapc1xd5vcsv2j` (`item_cardapio_id_item`),
  CONSTRAINT `FK22dcmrgxp6mgapc1xd5vcsv2j` FOREIGN KEY (`item_cardapio_id_item`) REFERENCES `item_cardapio` (`id_item`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_pedido`
--

LOCK TABLES `item_pedido` WRITE;
/*!40000 ALTER TABLE `item_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `forma_pagamento` varchar(255) DEFAULT NULL,
  `pago` bit(1) NOT NULL,
  `valor` float NOT NULL,
  `pedido_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsc46s3wc046ujpdoumidm4cr7` (`pedido_id`),
  CONSTRAINT `FKthad9tkw4188hb3qo1lm5ueb0` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valor_total` float NOT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `entregador_id` bigint DEFAULT NULL,
  `restaurante_id` bigint DEFAULT NULL,
  `estado_pedido` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK30s8j2ktpay6of18lbyqn3632` (`cliente_id`),
  KEY `FK5x80cwcdtp01tkevk2ftt0b6b` (`entregador_id`),
  KEY `FK3eud5cqmgsnltyk704hu3qj71` (`restaurante_id`),
  CONSTRAINT `FK30s8j2ktpay6of18lbyqn3632` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FK3eud5cqmgsnltyk704hu3qj71` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`),
  CONSTRAINT `FK5x80cwcdtp01tkevk2ftt0b6b` FOREIGN KEY (`entregador_id`) REFERENCES `entregador` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_itens`
--

DROP TABLE IF EXISTS `pedido_itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_itens` (
  `pedido_id` bigint NOT NULL,
  `itens_id` bigint NOT NULL,
  UNIQUE KEY `UKmu8rg6jm44j40igjdcg5jvh5k` (`itens_id`),
  KEY `FKb5t2ga17uxph7bp32w97ssu4t` (`pedido_id`),
  CONSTRAINT `FKb5t2ga17uxph7bp32w97ssu4t` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKpgxyyixiy6xcc75yujqnb8sg` FOREIGN KEY (`itens_id`) REFERENCES `item_pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_itens`
--

LOCK TABLES `pedido_itens` WRITE;
/*!40000 ALTER TABLE `pedido_itens` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_itens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante`
--

DROP TABLE IF EXISTS `restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `endereco` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `abertura` varchar(255) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `faz_entrega` bit(1) NOT NULL,
  `fechamento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante`
--

LOCK TABLES `restaurante` WRITE;
/*!40000 ALTER TABLE `restaurante` DISABLE KEYS */;
INSERT INTO `restaurante` VALUES (1,'Endere√ßo Teste','Teste','22992548766','12:55','Comida',_binary '','18:35'),(2,'aaaaaaaa','aaaaaaaaaa','22997198661','17:22','Comida',_binary '','19:24'),(3,'Rua Bahia, 112','Vitor Souza','aaaaaaaa','09:46','Comida',_binary '','10:46'),(4,'Rua Bahia 112','aaa','22997198661','06:58','Comida',_binary '','11:05'),(5,'Rua Bahia, 112','asdasfdf','22997198661','14:48','Comida',_binary '','16:51'),(6,'Rua Bahia 112','Tempero da Esquina','22997198661','15:58','Comida',_binary '','14:57'),(7,'Rua Bahia, 112','Tempero da Ro√ßa','22997198661','16:22','Comida',_binary '','18:21'),(8,'Rua Bahia, 112','Vitor Souza','22997198661','12:12','Comida',_binary '','12:12');
/*!40000 ALTER TABLE `restaurante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante_cardapio`
--

DROP TABLE IF EXISTS `restaurante_cardapio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurante_cardapio` (
  `restaurante_id` bigint NOT NULL,
  `cardapio_id_item` bigint NOT NULL,
  UNIQUE KEY `UKa2rdq4ybnua6ar78kfunkxbft` (`cardapio_id_item`),
  KEY `FK91a0qe2b8o182o6dxfvdn78v4` (`restaurante_id`),
  CONSTRAINT `FK91a0qe2b8o182o6dxfvdn78v4` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`),
  CONSTRAINT `FKlolfmm8nud5uy2u6st6corhqx` FOREIGN KEY (`cardapio_id_item`) REFERENCES `item_cardapio` (`id_item`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante_cardapio`
--

LOCK TABLES `restaurante_cardapio` WRITE;
/*!40000 ALTER TABLE `restaurante_cardapio` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurante_cardapio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('7e060ee3-e3e1-4b17-b71e-89331a612b09','518819c0-ed19-4fb1-8650-b12145421019',1746217502420,1746217507016,1800,1746219307016,NULL);
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('7e060ee3-e3e1-4b17-b71e-89331a612b09','clienteId',_binary '¨\Ì\0sr\0java.lang.Long;ã\‰êÃè#\ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî\‡ã\0\0xp\0\0\0\0\0\0\0'),('7e060ee3-e3e1-4b17-b71e-89331a612b09','tipoUsuario',_binary '¨\Ì\0t\0cliente');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-02 17:43:19
