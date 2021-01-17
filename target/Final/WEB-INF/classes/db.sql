UNLOCK TABLES;
CREATE DATABASE  IF NOT EXISTS `final`;
USE `final`;
--
-- Table structure for table `role`
--
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `user_company`;
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (default,'ROLE_USER');
INSERT INTO `role` VALUES (default,'ROLE_MANAGER');
INSERT INTO `role` VALUES (default,'ROLE_ADMIN');

UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `company`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `first_name` varchar(255) DEFAULT NULL,
                        `last_name`  varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
CREATE TABLE `company` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           `managerid` int(11),
                           PRIMARY KEY (`id`),
                           FOREIGN KEY (managerid) REFERENCES user(id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;




LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (default,'admin99','$2a$11$cKAlRTmOOwxSDK5.Y8KYGu.Z8D74kab6PVJbIP.yAfW5L1JMykeWi','','');
UNLOCK TABLES;

--
-- Table structure for table `company`
--


--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_roleid_idx` (`role_id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
LOCK TABLES `user_role` WRITE;
INSERT INTO `user_role` VALUES (4,2);
INSERT INTO `user_role` VALUES (4,3);
INSERT INTO `user_role` VALUES (4,4);
UNLOCK TABLES;
--
-- Table structure for table `user_company`
--
DROP TABLE IF EXISTS `user_company`;
CREATE TABLE `user_company` (
                             `company_id` int(11) NOT NULL,
                             `user_id` int(11) NOT NULL,
                             PRIMARY KEY (`company_id`, `user_id`),
                             KEY `fk_user_company_company_idx` (`company_id`),
                             CONSTRAINT `fk_user_company_companyid` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT `fk_user_company_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;