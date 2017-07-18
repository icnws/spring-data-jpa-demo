/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 10.1.8-MariaDB : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`id`,`first_name`,`last_name`) values (1,'Jack','Bauer'),(2,'Chloe','O\'Brian'),(3,'Kim','Bauer'),(4,'David','Palmer'),(5,'Michelle','Dessler'),(6,'Bauer','Dessler');

/*Table structure for table `my_order` */

DROP TABLE IF EXISTS `my_order`;

CREATE TABLE `my_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `c_id` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5whma1a1ladabvkt5jgh8phgp` (`c_id`),
  CONSTRAINT `FK5whma1a1ladabvkt5jgh8phgp` FOREIGN KEY (`c_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `my_order` */

insert  into `my_order`(`id`,`c_id`,`code`,`total`) values (1,1,'123456','11.10'),(2,1,'123457','20.90'),(3,1,'123458','11.90'),(4,1,'123459','9.99'),(5,1,'123455','55.23');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
