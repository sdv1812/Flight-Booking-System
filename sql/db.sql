--
-- Create dabase schema
--
CREATE DATABASE `db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE db;
--
-- Create table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Admin','User') DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Insert Admin user in `user` table
--
INSERT INTO `user` (`email`, `password`, `role`) VALUES ('admin@crossover.com', '$2a$10$ZrT5XbYEuZ3TGO8M7tcKJehp.ZF9KgzHDn9y4tdLEiXRAcSw4/GGa', 'Admin');

--
-- Create Table `account`
--
CREATE TABLE `account` (
  `id` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_account_id_idx` (`user_id`),
  CONSTRAINT `user_account_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Create tab;e `monetary_amount`
--

CREATE TABLE `monetary_amount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `currency` enum('USD','EUR') DEFAULT NULL,
  `account_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account_id_idx` (`account_id`),
  CONSTRAINT `account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
