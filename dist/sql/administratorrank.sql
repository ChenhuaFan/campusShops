/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : campus_shop

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-04-19 08:49:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administratorrank
-- ----------------------------
DROP TABLE IF EXISTS `administratorrank`;
CREATE TABLE `administratorrank` (
  `AdminID` int(11) NOT NULL,
  `AdminRank` int(1) NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administratorrank
-- ----------------------------
INSERT INTO `administratorrank` VALUES ('1', '1');
INSERT INTO `administratorrank` VALUES ('2', '0');
INSERT INTO `administratorrank` VALUES ('3', '2');
INSERT INTO `administratorrank` VALUES ('4', '1');
