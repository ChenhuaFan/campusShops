/*
Navicat MySQL Data Transfer

Source Server         : PK
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : campusshops

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-19 14:32:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addressID` int(10) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) NOT NULL,
  `addressType` int(1) NOT NULL DEFAULT '0',
  `addressDetails` varchar(255) NOT NULL,
  PRIMARY KEY (`addressID`),
  KEY `user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
