/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : campus_shop

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-04-19 08:49:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for servicerecord
-- ----------------------------
DROP TABLE IF EXISTS `servicerecord`;
CREATE TABLE `servicerecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ServiceID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `content` text NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of servicerecord
-- ----------------------------
INSERT INTO `servicerecord` VALUES ('1', '1', '1', '似的发射点', '2018-04-17 22:50:35');
INSERT INTO `servicerecord` VALUES ('2', '2', '2', 'v自行车v', '2018-04-19 22:50:52');
INSERT INTO `servicerecord` VALUES ('3', '1', '1', '似的发射点', '2018-04-17 22:50:35');
INSERT INTO `servicerecord` VALUES ('4', '2', '2', 'v自行车v', '2018-04-19 22:50:52');
INSERT INTO `servicerecord` VALUES ('5', '1', '1', '似的发射点', '2018-04-17 22:50:35');
INSERT INTO `servicerecord` VALUES ('6', '2', '2', 'v自行车v', '2018-04-19 22:50:52');
INSERT INTO `servicerecord` VALUES ('7', '1', '1', '似的发射点', '2018-04-17 22:50:35');
INSERT INTO `servicerecord` VALUES ('8', '2', '2', 'v自行车v', '2018-04-19 22:50:52');
