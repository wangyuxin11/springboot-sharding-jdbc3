/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : multipledatasource2

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-08-15 16:01:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cs_test
-- ----------------------------
DROP TABLE IF EXISTS `cs_test`;
CREATE TABLE `cs_test` (
  `id` varchar(64) NOT NULL,
  `userid` varchar(64) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `classid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
