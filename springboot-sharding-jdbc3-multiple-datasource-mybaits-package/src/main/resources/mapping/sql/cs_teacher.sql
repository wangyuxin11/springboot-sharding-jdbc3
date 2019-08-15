/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : multipledatasource2

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-08-15 16:01:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cs_teacher
-- ----------------------------
DROP TABLE IF EXISTS `cs_teacher`;
CREATE TABLE `cs_teacher` (
  `id` varchar(64) NOT NULL,
  `teachername` varchar(255) DEFAULT NULL,
  `classid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
