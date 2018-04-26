/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : campus_shop

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-04-19 08:49:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `ShopID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `Goods` text NOT NULL,
  PRIMARY KEY (`ShopID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES ('1', '1', '{\"name\":\"张三\",\"age\":\"31\"}');
INSERT INTO `shopping_cart` VALUES ('2', '2', '{\"name\": \"张三\",\"age\": \"20\"}');
INSERT INTO `shopping_cart` VALUES ('3', '3', '{\"name\": \"张三\",\"age\": \"20\"}');
INSERT INTO `shopping_cart` VALUES ('4', '4', '{\"name\":\"张三\",\"age\":\"21\"}');
