CREATE DATABASE  IF NOT EXISTS `school_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `school_db`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: school_db
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `ClassID` int NOT NULL,
  `CourseID` int DEFAULT NULL,
  `StaffID` int DEFAULT NULL,
  `RoomNumber` int DEFAULT NULL,
  `Schedule` text,
  `MaxCapacity` int DEFAULT NULL,
  PRIMARY KEY (`ClassID`),
  KEY `CourseID` (`CourseID`),
  KEY `StaffID` (`StaffID`),
  CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`CourseID`) REFERENCES `courses` (`CourseID`),
  CONSTRAINT `classes_ibfk_2` FOREIGN KEY (`StaffID`) REFERENCES `staff` (`StaffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,1,2,855,'MWF 9-10AM',30),(2,2,3,6201,'MWF 9-10AM',30),(3,3,6,6213,'TTh 10-11AM',30);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubs`
--

DROP TABLE IF EXISTS `clubs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clubs` (
  `ClubID` int NOT NULL,
  `ClubName` text,
  `StudentID` int NOT NULL,
  PRIMARY KEY (`ClubID`,`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubs`
--

LOCK TABLES `clubs` WRITE;
/*!40000 ALTER TABLE `clubs` DISABLE KEYS */;
INSERT INTO `clubs` VALUES (1,'Computer Club',7),(1,'Computer Club',17),(1,'Computer Club',30),(1,'Computer Club',37),(2,'Math Club',1),(2,'Math Club',20),(2,'Math Club',40),(3,'Science Club',1),(3,'Science Club',5),(3,'Science Club',8),(3,'Science Club',26),(3,'Science Club',29),(4,'Art Club',2),(4,'Art Club',10),(4,'Art Club',14),(4,'Art Club',44),(5,'Music Club',17),(5,'Music Club',39);
/*!40000 ALTER TABLE `clubs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `CourseID` int NOT NULL,
  `CourseName` text,
  `Description` text,
  `Credits` int DEFAULT NULL,
  PRIMARY KEY (`CourseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Mathematics','Introduction to Fractions',3),(2,'Science','Introduction to Biology',4),(3,'English','Introduction to Writing',3);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
  `DepartmentID` int NOT NULL,
  `DepartmentName` text,
  PRIMARY KEY (`DepartmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'Math'),(2,'Science'),(3,'English');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
  `EnrollmentID` int NOT NULL,
  `StudentID` int DEFAULT NULL,
  `ClassID` int DEFAULT NULL,
  `EnrollmentDate` text,
  PRIMARY KEY (`EnrollmentID`),
  KEY `StudentID` (`StudentID`),
  KEY `ClassID` (`ClassID`),
  CONSTRAINT `enrollment_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `students` (`StudentID`),
  CONSTRAINT `enrollment_ibfk_2` FOREIGN KEY (`ClassID`) REFERENCES `classes` (`ClassID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES (1,1,3,'4/23/2024'),(2,2,1,'4/29/2024'),(3,3,3,'4/22/2024'),(4,4,1,'3/9/2024'),(5,5,1,'7/12/2024'),(6,6,2,'2/11/2024'),(7,7,3,'4/3/2024'),(8,8,2,'3/9/2024'),(9,9,2,'3/22/2024'),(10,10,2,'7/23/2024'),(11,11,1,'6/18/2024'),(12,12,1,'5/31/2024'),(13,13,2,'5/1/2024'),(14,14,3,'4/3/2024'),(15,15,2,'7/13/2024'),(16,16,2,'1/29/2024'),(17,17,3,'1/4/2024'),(18,18,3,'6/6/2024'),(19,19,2,'4/1/2024'),(20,20,1,'2/15/2024'),(21,21,3,'2/29/2024'),(22,22,3,'4/10/2024'),(23,23,2,'3/15/2024'),(24,24,2,'6/15/2024'),(25,25,2,'7/25/2024'),(26,26,1,'7/1/2024'),(27,27,1,'2/9/2024'),(28,28,2,'1/20/2024'),(29,29,3,'3/19/2024'),(30,30,2,'4/16/2024'),(31,31,1,'2/25/2024'),(32,32,1,'6/30/2024'),(33,33,2,'2/23/2024'),(34,34,1,'6/25/2024'),(35,35,3,'3/23/2024'),(36,36,2,'4/15/2024'),(37,37,1,'3/7/2024'),(38,38,2,'1/24/2024'),(39,39,3,'4/19/2024'),(40,40,1,'2/15/2024'),(41,41,3,'1/19/2024'),(42,42,1,'3/31/2024'),(43,43,2,'7/20/2024'),(44,44,2,'4/4/2024'),(45,45,2,'3/7/2024');
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `ReportID` int NOT NULL,
  `StudentID` int DEFAULT NULL,
  `Description` text,
  `GeneratedDate` text,
  `GradeInClass` text,
  PRIMARY KEY (`ReportID`),
  KEY `StudentID` (`StudentID`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `students` (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,1,'Good Performance','1/5/2024','B'),(2,2,'Good Performance','7/21/2024','B'),(3,3,'Good Performance','2/5/2024','C'),(4,4,'Good Performance','1/17/2024','B'),(5,5,'Good Performance','6/13/2024','B'),(6,6,'Good Performance','4/19/2024','A'),(7,7,'Good Performance','2/15/2024','C'),(8,8,'Good Performance','5/16/2024','A'),(9,9,'Good Performance','4/8/2024','C'),(10,10,'Good Performance','1/31/2024','B'),(11,11,'Bad Performance','6/13/2024','D'),(12,12,'Bad Performance','3/11/2024','D'),(13,13,'Bad Performance','4/2/2024','F'),(14,14,'Good Performance','3/21/2024','A'),(15,15,'Good Performance','4/4/2024','A'),(16,16,'Good Performance','7/10/2024','B'),(17,17,'Good Performance','7/3/2024','A'),(18,18,'Good Performance','4/17/2024','A'),(19,19,'Good Performance','2/2/2024','A'),(20,20,'Good Performance','5/23/2024','B'),(21,21,'Good Performance','7/4/2024','A'),(22,22,'Good Performance','3/15/2024','A'),(23,23,'Good Performance','1/9/2024','A'),(24,24,'Bad Performance','3/6/2024','D'),(25,25,'Good Performance','4/29/2024','C'),(26,26,'Good Performance','2/16/2024','B'),(27,27,'Good Performance','4/2/2024','A'),(28,28,'Good Performance','5/31/2024','A'),(29,29,'Good Performance','7/10/2024','A'),(30,30,'Good Performance','5/22/2024','C'),(31,31,'Bad Performance','5/4/2024','F'),(32,32,'Good Performance','6/28/2024','A'),(33,33,'Bad Performance','5/2/2024','D'),(34,34,'Bad Performance','4/25/2024','F'),(35,35,'Good Performance','2/12/2024','C'),(36,36,'Bad Performance','3/3/2024','F'),(37,37,'Good Performance','2/3/2024','C'),(38,38,'Good Performance','5/15/2024','A'),(39,39,'Good Performance','3/5/2024','A'),(40,40,'Bad Performance','3/10/2024','F'),(41,41,'Bad Performance','6/26/2024','F'),(42,42,'Good Performance','5/7/2024','C'),(43,43,'Bad Performance','7/19/2024','D'),(44,44,'Bad Performance','7/24/2024','D'),(45,45,'Good Performance','2/11/2024','C');
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school` (
  `SchoolID` int NOT NULL,
  `SchoolName` text,
  `Address` text,
  `Principal` text,
  `PhoneNumber` text,
  PRIMARY KEY (`SchoolID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (1,'NewTown Elementary','789 Pine St','Dr. Green','555-666-7777');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `StaffID` int NOT NULL,
  `FirstName` text,
  `LastName` text,
  `DateOfBirth` text,
  `Position` text,
  `DepartmentID` int DEFAULT NULL,
  `Email` text,
  `PhoneNumber` text,
  PRIMARY KEY (`StaffID`),
  KEY `DepartmentID` (`DepartmentID`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`DepartmentID`) REFERENCES `departments` (`DepartmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Josh','Green','4/7/1965','Principal',NULL,'jgreen@pines.com','782-033-4657'),(2,'Tommy','Johnson','4/9/1974','Math Teacher',1,'tjohnson@pines.com','902-777-7326'),(3,'Jeffrey','Campbell','1/2/1970','Science Teacher',2,'jcampbell@pines.com','078-642-8523'),(4,'Jamie','Chang','3/10/1998','Nurse',NULL,'jchang@pines.com','432-446-9320'),(5,'Misty','Bates','10/27/1982','Groundkeeper',NULL,'mbates@pines.com','922-283-9733'),(6,'Grant','Campos','5/10/1977','English Teacher',3,'tsmith@pines.com','331-833-1811');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `StudentID` int NOT NULL,
  `FirstName` text,
  `LastName` text,
  `DateOfBirth` text,
  `GradeLevel` int DEFAULT NULL,
  `Address` text,
  `Email` text,
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Christopher','Scott','3/3/2015',4,'4563 Samantha Forges\nNorth Bonnieberg, ME 98539','cscott@pines.com'),(2,'Tamara','Henson','8/7/2014',4,'1293 Chelsea Road Apt. 201\nCooperport, AR 80945','thenson@pines.com'),(3,'Cynthia','Cunningham','11/27/2012',6,'5923 Ramirez View\nBrandyville, NY 53335','ccunningham@pines.com'),(4,'Sharon','Bell','1/6/2019',6,'276 Roberson Estates Suite 059\nWest Timothy, WV 57212','sbell@pines.com'),(5,'Rebecca','Ramirez','7/5/2019',4,'295 Haley Road Apt. 994\nSouth Maryborough, ID 96023','rramirez@pines.com'),(6,'Dawn','Pratt','3/9/2011',4,'116 Jennifer Knoll\nLake Tracy, OR 59510','dpratt@pines.com'),(7,'Andrew','Carlson','5/8/2019',6,'323 Nicole Stravenue Suite 853\nRomeroburgh, MO 94741','acarlson@pines.com'),(8,'Wanda','Richardson','7/1/2017',5,'068 Johnson Lights Apt. 375\nLake Brycetown, NE 89569','wrichardson@pines.com'),(9,'Timothy','Robbins','1/11/2012',4,'513 Knight Land\nSouth Michael, ND 35753','trobbins@pines.com'),(10,'Carl','Howard','5/24/2016',5,'7385 Tiffany Keys Suite 404\nLake Curtis, KY 09949','choward@pines.com'),(11,'Robert','Lee','9/22/2015',4,'354 Nelson Oval\nGrahambury, ME 23071','rlee@pines.com'),(12,'Emma','Taylor','7/10/2019',5,'661 Olson Walk Apt. 825\nAndersonmouth, KS 88690','etaylor@pines.com'),(13,'Joseph','Byrd','1/15/2011',6,'92110 Andrew Ford Apt. 516\nRobertmouth, CT 78429','jbyrd@pines.com'),(14,'Anna','Brown','8/22/2012',5,'150 Barry Village Suite 664\nMoorefort, MS 98400','abrown@pines.com'),(15,'Kathleen','Doyle','11/17/2011',4,'3400 Walker Gardens Apt. 451\nDawnport, MT 58810','kdoyle@pines.com'),(16,'Jose','Tucker','12/6/2014',5,'329 Thomas Shore Suite 476\nMathewsview, VT 97752','jtucker@pines.com'),(17,'Marissa','Johnson','2/22/2018',4,'Unit 6655 Box 0589\nDPO AA 05382','mjohnson@pines.com'),(18,'Aaron','Spears','4/28/2015',4,'294 Martin Street\nKelseytown, DE 17495','aspears@pines.com'),(19,'Jennifer','Mcgee','12/30/2015',4,'22505 Cardenas Mountains Apt. 697\nSouth Ashlee, OK 56817','jmcgee@pines.com'),(20,'Michael','Johnson','4/5/2010',6,'036 Brown Meadows\nHeatherfort, AZ 95203','mjohnson@pines.com'),(21,'Lisa','Payne','3/17/2018',5,'0712 Justin Light\nSouth Heatherhaven, PA 83362','lpayne@pines.com'),(22,'Angela','Odom','8/14/2010',5,'40276 Woods Overpass\nSouth Tinaview, SC 48310','aodom@pines.com'),(23,'Brenda','Bailey','1/2/2012',5,'57725 Rebecca Lodge\nEvanbury, VT 91791','bbailey@pines.com'),(24,'Angela','Thornton','3/25/2019',6,'USCGC Ramirez\nFPO AE 51433','athornton@pines.com'),(25,'Kelly','Pennington','9/7/2016',6,'638 Alexander Trafficway Suite 033\nWest Betty, IL 70282','kpennington@pines.com'),(26,'George','Bennett','11/7/2013',4,'86140 Blair Cove Apt. 790\nJoshuaside, OR 52331','gbennett@pines.com'),(27,'John','Dunn','12/20/2013',6,'73971 Stanley Mill\nMorrisonport, AL 50693','jdunn@pines.com'),(28,'Danielle','Lewis','7/24/2014',6,'667 Evan Wall Apt. 710\nLake Brenda, PA 94242','dlewis@pines.com'),(29,'Kaitlyn','Lawrence','4/21/2019',4,'67707 Zachary Green Suite 837\nNew David, NY 66948','klawrence@pines.com'),(30,'Bryce','Clark','7/9/2010',4,'40978 Dennis Freeway\nNorth Kennethhaven, CA 48285','bclark@pines.com'),(31,'Sara','Lawson','12/18/2015',4,'380 Justin Vista\nNorth Cynthiatown, MI 40632','slawson@pines.com'),(32,'Lisa','Collins','3/5/2018',4,'50266 Shannon Mews\nEast James, IN 77496','lcollins@pines.com'),(33,'Barbara','Sanchez','11/20/2009',4,'523 Stokes Mission Suite 538\nNorth Christy, MD 56775','bsanchez@pines.com'),(34,'Gloria','Martin','6/24/2017',5,'PSC 9045, Box 5043\nAPO AE 15786','gmartin@pines.com'),(35,'Jesse','Daniels','11/16/2013',5,'8331 Hughes Springs\nPhilipberg, MA 75038','jdaniels@pines.com'),(36,'Melanie','Watkins','11/26/2008',6,'4309 Calderon Crossing\nNew Eileenmouth, IN 55307','mwatkins@pines.com'),(37,'Michael','Davis','3/24/2013',6,'84406 Chris Valley Suite 069\nAguirrechester, NV 47173','mdavis@pines.com'),(38,'Gregory','Lutz','6/2/2010',4,'6629 Julia Landing\nWest Rachelton, WV 83784','glutz@pines.com'),(39,'Kathleen','Hunter','10/5/2018',4,'80498 Christopher Lights Apt. 699\nKatrinaville, CA 07013','khunter@pines.com'),(40,'Michael','George','4/6/2010',4,'15226 Holmes Corner Suite 256\nMoorehaven, ID 88083','mgeorge@pines.com'),(41,'Christian','Sanchez','12/16/2011',5,'71492 George Alley Suite 986\nReginamouth, CT 38610','csanchez@pines.com'),(42,'Troy','Hammond','2/15/2011',4,'PSC 1137, Box 2615\nAPO AE 56711','thammond@pines.com'),(43,'Crystal','Burton','7/8/2013',6,'3536 Jensen Harbors Suite 793\nKylemouth, NV 41264','cburton@pines.com'),(44,'Alejandro','Hughes','4/17/2010',5,'245 Richmond Lake\nSouth Susan, RI 51162','ahughes@pines.com'),(45,'James','Delgado','4/3/2015',4,'6329 Christopher Divide\nDonnaton, DE 27330','jdelgado@pines.com');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technologies`
--

DROP TABLE IF EXISTS `technologies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technologies` (
  `TechnologyName` text,
  `SerialNumber` text,
  `AssignedToStaffID` int DEFAULT NULL,
  `TechID` int NOT NULL,
  PRIMARY KEY (`TechID`),
  KEY `AssignedToStaffID` (`AssignedToStaffID`),
  CONSTRAINT `technologies_ibfk_1` FOREIGN KEY (`AssignedToStaffID`) REFERENCES `staff` (`StaffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technologies`
--

LOCK TABLES `technologies` WRITE;
/*!40000 ALTER TABLE `technologies` DISABLE KEYS */;
INSERT INTO `technologies` VALUES ('Tablet','ee2ffcea-a5ad-43f3-b0b0-1cc4d2cf9946',1,1),('Smartboard','2d732e1d-25a7-41dc-b9cb-61d223aa8f85',1,2),('Smartboard','d8ff7bef-c445-495b-9853-b87e5e63396d',2,3),('Projector','977372a1-c2f7-421d-aabd-5b436d2b2cde',2,4),('Projector','fc8b81ae-3104-4cf2-9677-db7223cbba22',3,5),('Smartboard','d58ea33d-4a93-4404-a240-bd96c3e364b0',3,6),('Tablet','ecf280fc-6f62-4b27-a359-52a4536de3d2',6,7),('Smartboard','0f863009-5981-4293-9675-0d0fe1c84ce5',6,8),('Laptop','e1539a3b-4e93-4868-b36d-dad24e5252d7',4,9),('Printer','2ffdd7b2-e1f8-4f4f-8802-a6cc87996a13',3,10),('Printer','d290d813-cd81-4f76-bcf1-427207a774fc',2,11),('Tablet','6b0986c8-647f-41a6-9826-d2643616b927',4,12),('Printer','fe251bb0-1c87-421c-8bdd-84ee4e3a546e',2,13),('Laptop','25c357a8-7dc5-4941-a6fc-2daa8f573a1e',6,14),('Laptop','59914ee7-0692-4e0e-a99a-95e67932a7fc',2,15);
/*!40000 ALTER TABLE `technologies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-01 23:27:57
