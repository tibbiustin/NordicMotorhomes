/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 100116
Source Host           : localhost:3306
Source Database       : motorhomes

Target Server Type    : MYSQL
Target Server Version : 100116
File Encoding         : 65001

Date: 2017-05-28 15:34:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `username_admin` varchar(20) DEFAULT NULL,
  `password_admin` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id_customer` int(11) NOT NULL AUTO_INCREMENT,
  `name_customer` varchar(20) DEFAULT NULL,
  `email_customer` varchar(20) DEFAULT NULL,
  `password_customer` varchar(256) DEFAULT NULL,
  `cpr_customer` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_customer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for mechanic
-- ----------------------------
DROP TABLE IF EXISTS `mechanic`;
CREATE TABLE `mechanic` (
  `id_mechanic` int(11) NOT NULL AUTO_INCREMENT,
  `username_mechanic` varchar(45) DEFAULT NULL,
  `password_mechanic` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_mechanic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mechanic
-- ----------------------------

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL AUTO_INCREMENT,
  `start_reservation` varchar(20) NOT NULL,
  `finish_reservation` varchar(20) NOT NULL,
  `reservation_customer_id` int(11) NOT NULL,
  `vehicle_reservation` int(11) NOT NULL DEFAULT '0',
  `pickup_reservation` varchar(45) NOT NULL DEFAULT '0',
  `dropoff_reservation` varchar(45) NOT NULL DEFAULT '0',
  `price_reservation` varchar(45) NOT NULL DEFAULT '0',
  `bike_reservation` tinyint(4) NOT NULL DEFAULT '0',
  `child_reservation` tinyint(4) NOT NULL DEFAULT '0',
  `picnic_reservation` tinyint(4) NOT NULL DEFAULT '0',
  `payment_type_reservation` varchar(45) NOT NULL DEFAULT 'cash',
  `paid_reservation` tinyint(4) NOT NULL DEFAULT '0',
  `fuel_level_over_half_reservation` tinyint(4) NOT NULL DEFAULT '0',
  `mechanic_approval_reservation` tinyint(4) NOT NULL DEFAULT '0',
  `mechanic_comments` varchar(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_reservation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reservation
-- ----------------------------

-- ----------------------------
-- Table structure for typeofvehicle
-- ----------------------------
DROP TABLE IF EXISTS `typeofvehicle`;
CREATE TABLE `typeofvehicle` (
  `id_typeOfVehicle` int(11) NOT NULL AUTO_INCREMENT,
  `capacity_typeOfVehicle` varchar(20) DEFAULT NULL,
  `brand_typeOfVehicle` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_typeOfVehicle`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of typeofvehicle
-- ----------------------------
INSERT INTO `typeofvehicle` VALUES ('1', '4', 'Toyota');
INSERT INTO `typeofvehicle` VALUES ('2', '4', 'Alfa Romeo');
INSERT INTO `typeofvehicle` VALUES ('3', '4', 'Aston Martin');
INSERT INTO `typeofvehicle` VALUES ('4', '4', 'Audi');
INSERT INTO `typeofvehicle` VALUES ('5', '6', 'Ford');
INSERT INTO `typeofvehicle` VALUES ('6', '2', 'Mazda');
INSERT INTO `typeofvehicle` VALUES ('7', '8', 'Kia');
INSERT INTO `typeofvehicle` VALUES ('8', '4', 'Nissan');

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `id_vehicle` int(11) NOT NULL AUTO_INCREMENT,
  `type_vehicle` int(11) NOT NULL,
  `license_vehicle` varchar(20) NOT NULL DEFAULT 'NOLICENSE',
  `booked_vehicle` tinyint(4) NOT NULL DEFAULT '0',
  `price_vehicle` int(11) NOT NULL DEFAULT '200',
  PRIMARY KEY (`id_vehicle`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES ('1', '2', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('2', '2', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('3', '3', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('4', '3', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('5', '1', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('6', '1', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('7', '4', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('8', '4', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('9', '5', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('10', '5', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('11', '6', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('12', '6', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('13', '7', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('14', '7', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('15', '8', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('16', '8', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('17', '2', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('18', '3', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('19', '4', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('20', '5', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('21', '6', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('22', '7', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('23', '8', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('24', '4', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('25', '2', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('26', '1', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('27', '4', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('28', '5', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('29', '6', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('30', '7', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('31', '8', 'NOLICENSE', '0', '200');
INSERT INTO `vehicle` VALUES ('32', '9', 'NOLICENSE', '0', '200');
