CREATE DATABASE  IF NOT EXISTS `ims` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ims`;
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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `customer_first` varchar(32) NOT NULL,
  `customer_last` varchar(32) NOT NULL,
  `customer_addr_1` varchar(128) NOT NULL,
  `customer_addr_2` varchar(32) DEFAULT NULL,
  `customer_city` varchar(32) NOT NULL,
  `customer_county` varchar(32) DEFAULT NULL,
  `customer_country` varchar(32) NOT NULL,
  `customer_postcode` char(8) NOT NULL,
  `customer_email` varchar(320) NOT NULL,
  `customer_tel` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `FK_soap_id` int DEFAULT NULL,
  `item_name` varchar(64) NOT NULL,
  `item_price` decimal(5,2) NOT NULL,
  `item_stock` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`),
  KEY `FK_soap_id` (`FK_soap_id`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`FK_soap_id`) REFERENCES `soaps` (`soap_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `order_summary`
--

DROP TABLE IF EXISTS `order_summary`;
/*!50001 DROP VIEW IF EXISTS `order_summary`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `order_summary` AS SELECT 
 1 AS `order_ID`,
 1 AS `customer_first`,
 1 AS `customer_last`,
 1 AS `order_placed`,
 1 AS `order_total`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_ID` int NOT NULL AUTO_INCREMENT,
  `FK_customer_id` int NOT NULL,
  `order_placed` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_ID`),
  KEY `FK_customer_id` (`FK_customer_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`FK_customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders_items`
--

DROP TABLE IF EXISTS `orders_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_items` (
  `FK_order_ID` int NOT NULL,
  `FK_item_id` int NOT NULL,
  `item_quantity` int NOT NULL DEFAULT '1',
  KEY `FK_order_ID` (`FK_order_ID`),
  KEY `FK_item_id` (`FK_item_id`),
  CONSTRAINT `orders_items_ibfk_1` FOREIGN KEY (`FK_order_ID`) REFERENCES `orders` (`order_ID`),
  CONSTRAINT `orders_items_ibfk_2` FOREIGN KEY (`FK_item_id`) REFERENCES `items` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `soaps`
--

DROP TABLE IF EXISTS `soaps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `soaps` (
  `soap_id` int NOT NULL AUTO_INCREMENT,
  `soap_name` varchar(64) NOT NULL,
  `soap_colour` varchar(32) NOT NULL,
  `soap_scent` varchar(32) DEFAULT NULL,
  `soap_type` enum('Bar','Cake Slice','Cupcake') NOT NULL DEFAULT 'Bar',
  PRIMARY KEY (`soap_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `user_password` varchar(32) NOT NULL,
  `FK_customer_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `FK_customer_id` (`FK_customer_id`),
  UNIQUE KEY `FK_customer_id_2` (`FK_customer_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`FK_customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (10,'root','r00t_p455w0rd',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Final view structure for view `order_summary`
--

/*!50001 DROP VIEW IF EXISTS `order_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `order_summary` (`order_ID`,`customer_first`,`customer_last`,`placed`,`order_total`) AS select `ordersum`.`order_ID` AS `order_ID`,`c`.`customer_first` AS `customer_first`,`c`.`customer_last` AS `customer_last`,`ordersum`.`placed` AS `placed`,`ordersum`.`order_total` AS `order_total` from (`customers` `c` join (select `o`.`order_ID` AS `order_ID`,`o`.`FK_customer_id` AS `FK_customer_id`,`o`.`order_placed` AS `placed`,sum(`oijoin`.`total`) AS `order_total` from (`orders` `o` join (select `oi`.`FK_order_ID` AS `FK_order_ID`,`oi`.`FK_item_id` AS `FK_item_id`,(`oi`.`item_quantity` * `i`.`item_price`) AS `total` from (`orders_items` `oi` join `items` `i` on((`oi`.`FK_item_id` = `i`.`item_id`)))) `oijoin` on((`o`.`order_ID` = `oijoin`.`FK_order_ID`))) group by `oijoin`.`FK_order_ID`) `ordersum` on((`c`.`customer_id` = `ordersum`.`FK_customer_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;