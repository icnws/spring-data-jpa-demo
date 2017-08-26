/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 10.1.8-MariaDB : Database - test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = '' */;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id`         BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255)        DEFAULT NULL,
  `last_name`  VARCHAR(255)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

/*Data for the table `customer` */

INSERT INTO `customer` (`id`, `first_name`, `last_name`)
VALUES (1, 'Jack', 'Bauer'), (2, 'Chloe', 'O\'Brian'), (3, 'Kim', 'Bauer'), (4, 'David', 'Palmer'),
  (5, 'Michelle', 'Dessler'), (6, 'Bauer', 'Dessler');

/*Table structure for table `job_config` */

DROP TABLE IF EXISTS `job_config`;

CREATE TABLE `job_config` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `create_at`   DATETIME         DEFAULT NULL,
  `cron_time`   VARCHAR(255)     DEFAULT NULL,
  `full_entity` VARCHAR(255)     DEFAULT NULL,
  `group_name`  VARCHAR(255)     DEFAULT NULL,
  `name`        VARCHAR(255)     DEFAULT NULL,
  `status`      INT(11)          DEFAULT NULL,
  `update_at`   DATETIME         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

/*Data for the table `job_config` */

INSERT INTO `job_config` (`id`, `create_at`, `cron_time`, `full_entity`, `group_name`, `name`, `status`, `update_at`)
VALUES (1, '2017-08-25 21:03:35', '0/8 * *  * * ?', 'com.example.demo.jobs.MyJob', 'test', 'My test', 1, NULL),
  (2, '2017-08-25 21:12:02', '0/23 * * * * ?', 'com.example.demo.jobs.MyJob', 'test', 'My Job', 1, NULL);

/*Table structure for table `my_order` */

DROP TABLE IF EXISTS `my_order`;

CREATE TABLE `my_order` (
  `id`    BIGINT(20) NOT NULL AUTO_INCREMENT,
  `c_id`  BIGINT(20)          DEFAULT NULL,
  `code`  VARCHAR(255)        DEFAULT NULL,
  `total` DECIMAL(19, 2)      DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5whma1a1ladabvkt5jgh8phgp` (`c_id`),
  CONSTRAINT `FK5whma1a1ladabvkt5jgh8phgp` FOREIGN KEY (`c_id`) REFERENCES `customer` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `my_order` */

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id`   BIGINT(20) NOT NULL AUTO_INCREMENT,
  `age`  INT(11)             DEFAULT NULL,
  `name` VARCHAR(255)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `person` */

/*Table structure for table `phone` */

DROP TABLE IF EXISTS `phone`;

CREATE TABLE `phone` (
  `id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(255)        DEFAULT NULL,
  `p_id`   BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9mhp1pog1bypdcong57dx0ffu` (`p_id`),
  CONSTRAINT `FK9mhp1pog1bypdcong57dx0ffu` FOREIGN KEY (`p_id`) REFERENCES `person` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Data for the table `phone` */

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
