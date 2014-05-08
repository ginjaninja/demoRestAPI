
--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active_ind` varchar(1) NOT NULL,
  `activity_dt_tm` datetime NOT NULL,
  `created_dt_tm` datetime NOT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Y','2014-05-05 00:00:00','2013-10-06 15:14:05','BooBoo','Lee'),(2,'Y','2014-05-05 00:00:00','2013-10-06 15:14:05','Deborah','Lee'),(3,'Y','2014-05-05 00:00:00','2014-02-11 05:08:17','Ori','Russell'),(4,'Y','2014-05-05 00:00:00','2014-03-20 02:49:57','Rebekah','Delacruz'),(5,'Y','2014-05-05 00:00:00','2013-09-20 23:56:55','Wing','Sutton'),(6,'Y','2014-05-05 00:00:00','2013-06-01 20:09:08','Lee','Munoz'),(7,'Y','2014-05-05 00:00:00','2013-05-11 21:50:28','Yoko','Hyde'),(8,'Y','2014-05-05 00:00:00','2013-09-01 11:59:23','Sandra','Webster'),(9,'Y','2014-05-05 00:00:00','2013-06-23 11:06:19','Scott','Booth'),(10,'Y','2014-05-05 00:00:00','2013-07-04 17:51:54','Bruce','Golden'),(11,'Y','2014-05-05 00:00:00','2013-12-14 22:43:23','Alana','Morin'),(12,'Y','2014-05-05 00:00:00','2013-12-30 23:11:11','Mufutau','Serrano'),(13,'Y','2014-05-05 00:00:00','2014-02-13 22:46:55','Jacqueline','Spence'),(14,'Y','2014-05-05 00:00:00','2013-09-11 03:09:49','Patience','Callahan'),(15,'Y','2014-05-05 00:00:00','2014-04-23 02:02:55','Isabelle','Kline'),(16,'Y','2014-05-05 00:00:00','2013-08-29 12:49:04','Adrian','Mcintosh'),(17,'Y','2014-05-05 00:00:00','2013-11-11 01:52:38','Austin','Franklin'),(18,'Y','2014-05-05 00:00:00','2014-02-17 13:38:24','Signe','Franks'),(19,'Y','2014-05-05 00:00:00','2013-12-02 16:50:18','Gail','Farley'),(20,'Y','2014-05-05 00:00:00','2013-08-26 07:02:34','Heather','Walters'),(21,'Y','2014-05-05 00:00:00','2013-08-25 11:14:10','Anastasia','Murray'),(22,'Y','2014-05-05 00:00:00','2013-08-10 16:37:20','Christen','Erickson'),(23,'Y','2014-05-05 00:00:00','2013-09-02 12:47:24','Reuben','Shields'),(24,'Y','2014-05-05 00:00:00','2013-12-30 18:09:17','Nathaniel','Ashley'),(25,'Y','2014-05-05 00:00:00','2013-10-04 18:31:21','Kameko','Cross'),(26,'Y','2014-05-05 00:00:00','2014-05-03 09:30:14','Sierra','Battle'),(27,'Y','2014-05-05 00:00:00','2013-12-01 04:36:42','Mechelle','Meadows'),(28,'Y','2014-05-05 00:00:00','2014-03-05 12:17:28','Riley','Thornton'),(29,'Y','2014-05-05 00:00:00','2014-03-06 08:49:57','Gloria','Mckinney'),(30,'Y','2014-05-05 00:00:00','2013-07-30 15:33:11','Maxine','Witt'),(31,'Y','2014-05-05 00:00:00','2014-03-31 18:52:12','Maxwell','Malone'),(32,'Y','2014-05-05 00:00:00','2013-05-23 23:38:11','Pandora','Mays');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift`
--

DROP TABLE IF EXISTS `shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shift` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active_ind` varchar(1) NOT NULL,
  `activity_dt_tm` datetime NOT NULL,
  `created_dt_tm` datetime NOT NULL,
  `label` varchar(50) NOT NULL,
  `max_assigned` int(11) NOT NULL,
  `min_assigned` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift`
--

LOCK TABLES `shift` WRITE;
/*!40000 ALTER TABLE `shift` DISABLE KEYS */;
INSERT INTO `shift` VALUES 
(1,'Y','2014-05-05 00:00:00','2014-04-11 05:02:33','1st Call',1,1),
(2,'Y','2014-05-05 00:00:00','2014-01-04 05:57:58','2nd Call',1,1),
(3,'Y','2014-05-05 00:00:00','2014-02-19 04:28:11','3rd Call',1,1),
(4,'Y','2014-05-05 00:00:00','2014-04-10 16:25:21','Back Up',2,1),
(5,'Y','2014-05-05 00:00:00','2013-08-21 07:54:25','Floor',4,2),
(6,'Y','2014-05-05 00:00:00','2013-07-30 22:10:03','Beeper',2,2),
(7,'Y','2014-05-05 00:00:00','2013-07-30 22:10:03','Extra',1,1);
/*!40000 ALTER TABLE `shift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift_assignment`
--

DROP TABLE IF EXISTS `shift_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shift_assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active_ind` varchar(1) NOT NULL,
  `activity_dt_tm` datetime NOT NULL,
  `created_dt_tm` datetime NOT NULL,
  `shift_date` datetime NOT NULL,
  `person_id` int(11) DEFAULT NULL,
  `shift_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jtbku7p4hwhy47ebjgsrpogqs` (`person_id`),
  KEY `FK_19i81o4jss77yve3edjd4jeik` (`shift_id`),
  CONSTRAINT `FK_19i81o4jss77yve3edjd4jeik` FOREIGN KEY (`shift_id`) REFERENCES `shift` (`id`),
  CONSTRAINT `FK_jtbku7p4hwhy47ebjgsrpogqs` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift_assignment`
--

LOCK TABLES `shift_assignment` WRITE;
/*!40000 ALTER TABLE `shift_assignment` DISABLE KEYS */;
INSERT INTO `shift_assignment` VALUES 
(1,'Y','2014-05-07 16:22:52','2014-05-07 16:22:52','2014-05-08 00:00:00',5,5),
(2,'Y','2014-05-07 16:25:16','2014-05-07 16:25:16','2014-05-08 00:00:00',5,5),
(4,'Y','2014-05-07 16:27:12','2014-05-07 16:27:12','2014-05-08 00:00:00',5,5);
/*!40000 ALTER TABLE `shift_assignment` ENABLE KEYS */;
UNLOCK TABLES;



-- Dump completed on 2014-05-07 17:10:54
