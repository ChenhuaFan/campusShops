/*
Navicat MySQL Data Transfer

Source Server         : PK
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : campusshops

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-19 14:32:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `orderNumber` varchar(20) NOT NULL,
  `generatedTime` varchar(255) NOT NULL,
  `orderStatus` varchar(2) NOT NULL,
  `orderContent` text NOT NULL,
  `orderAmount` varchar(255) NOT NULL DEFAULT '0',
  `remark` text,
  `userId` int(10) NOT NULL,
  `shopId` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `userName` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
