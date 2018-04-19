/*
Navicat MySQL Data Transfer

Source Server         : PK
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : campusshops

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-19 14:32:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `pw` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT '123456@qq.com',
  `phone` varchar(20) NOT NULL,
  `gender` varchar(4) NOT NULL DEFAULT '保密',
  `isActive` varchar(2) NOT NULL DEFAULT '1',
  `isDelete` varchar(2) NOT NULL DEFAULT '0',
  `role` varchar(20) NOT NULL DEFAULT 'user',
  `headPortrait` varchar(255) DEFAULT 'Picture.jpg',
  PRIMARY KEY (`userID`),
  KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Liaray', '51b1934cd75b68af382fcd73544dbe9534b8048dd8f69cb961813c953ca94b21', 'p18155925262@gmail.com', '18155925262', '男', '1', '0', 'admin', 'handsome.jpg');
INSERT INTO `user` VALUES ('2', 'samchevia', '51b1934cd75b68af382fcd73544dbe9534b8048dd8f69cb961813c953ca94b21', 'p18155925510@gmail.com', '18155925510', '男', '1', '0', 'user', 'Picture.jpg');
INSERT INTO `user` VALUES ('3', 'wangyang', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '1367789445@gmail.com', '13677894455', '男', '1', '0', 'user', 'Picture.jpg');
INSERT INTO `user` VALUES ('4', 'limin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '1367789445@gmail.com', '13677894455', '男', '1', '0', 'user', 'Picture.jpg');
