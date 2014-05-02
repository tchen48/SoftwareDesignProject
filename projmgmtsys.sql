/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : projmgmtsys

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2014-04-30 00:23:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data`
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `dataId` varchar(255) NOT NULL,
  `value` varchar(50) NOT NULL,
  `fieldId` varchar(50) NOT NULL,
  `objId` varchar(50) NOT NULL,
  `rowId` varchar(50) NOT NULL,
  `depId` varchar(50) NOT NULL,
  `groId` varchar(50) NOT NULL,
  PRIMARY KEY (`dataId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data
-- ----------------------------
INSERT INTO `data` VALUES ('0_2_1_10_1', 'Proj2', '1', '0', '10', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_10_2', 'This is the second project', '2', '0', '10', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_10_3', '05/01/2014', '3', '0', '10', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_10_4', '0', '4', '0', '10', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_10_5', '08/8/8', '5', '0', '10', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_10_6', '432', '6', '0', '10', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_10_7', 'asdfhiadbgjo', '7', '0', '10', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_10_8', 'ljoingpafnp', '8', '0', '10', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_9_1', 'Proj1', '1', '0', '9', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_9_2', 'This is the first project', '2', '0', '9', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_9_3', '04/01/2014', '3', '0', '9', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_9_4\r\n0_2_1_9_4', '1', '4', '0', '9', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_9_5', '08/08/8', '5', '0', '9', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_9_6', '35', '6', '0', '9', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_9_7', '2435fda', '7', '0', '9', '2', '1');
INSERT INTO `data` VALUES ('0_2_1_9_8', 'asdfer', '8', '0', '9', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_0_5', 'Karl', '5', '1', '0', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_0_6', '04/16/2014', '6', '1', '0', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_0_7', '04/20/2014', '7', '1', '0', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_0_8', 'Progress No.1', '8', '1', '0', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_0_9', '10', '9', '1', '0', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_10_5', 'Karl', '5', '1', '10', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_10_6', '04/02/2014', '6', '1', '10', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_10_7', '04/08/2014', '7', '1', '10', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_10_8', 'Progress No. 11', '8', '1', '10', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_10_9', '10', '9', '1', '10', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_11_5', 'Karl', '5', '1', '11', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_11_6', '04/02/2014', '6', '1', '11', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_11_7', '04/01/2014', '7', '1', '11', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_11_8', 'Progress No. 12', '8', '1', '11', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_11_9', '10', '9', '1', '11', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_12_5', 'Karl', '5', '1', '12', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_12_6', '04/22/2014', '6', '1', '12', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_12_7', '04/21/2014', '7', '1', '12', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_12_8', 'Progress No. 13', '8', '1', '12', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_12_9', '10', '9', '1', '12', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_13_5', 'Karl', '5', '1', '13', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_13_6', '04/02/2014', '6', '1', '13', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_13_7', '04/09/2014', '7', '1', '13', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_13_8', 'Progress No. 14', '8', '1', '13', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_13_9', '10', '9', '1', '13', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_14_5', 'Karl', '5', '1', '14', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_14_6', '04/02/2014', '6', '1', '14', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_14_7', '04/02/2014', '7', '1', '14', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_14_8', 'Progress No. 15', '8', '1', '14', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_14_9', '10', '9', '1', '14', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_1_5', 'Karl', '5', '1', '1', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_1_6', '04/01/2014', '6', '1', '1', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_1_7', '04/04/2014', '7', '1', '1', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_1_8', 'Progress No. 2', '8', '1', '1', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_1_9', '10', '9', '1', '1', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_2_5', 'Karl', '5', '1', '2', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_2_6', '04/02/2014', '6', '1', '2', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_2_7', '04/04/2014', '7', '1', '2', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_2_8', 'Progress No. 3', '8', '1', '2', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_2_9', '10', '9', '1', '2', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_3_5', 'Karl', '5', '1', '3', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_3_6', '04/01/2014', '6', '1', '3', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_3_7', '04/30/2014', '7', '1', '3', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_3_8', 'Progress No. 4', '8', '1', '3', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_3_9', '10', '9', '1', '3', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_4_5', 'Karl', '5', '1', '4', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_4_6', '04/02/2014', '6', '1', '4', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_4_7', '04/23/2014', '7', '1', '4', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_4_8', 'Progress No. 5', '8', '1', '4', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_4_9', '10', '9', '1', '4', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_5_5', 'Karl', '5', '1', '5', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_5_6', '04/01/2014', '6', '1', '5', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_5_7', '04/08/2014', '7', '1', '5', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_5_8', 'Progress No. 6', '8', '1', '5', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_5_9', '10', '9', '1', '5', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_6_5', 'Karl', '5', '1', '6', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_6_6', '04/01/2014', '6', '1', '6', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_6_7', '04/01/2014', '7', '1', '6', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_6_8', 'Progress No. 7', '8', '1', '6', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_6_9', '10', '9', '1', '6', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_7_5', 'Karl', '5', '1', '7', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_7_6', '04/01/2014', '6', '1', '7', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_7_7', '04/30/2014', '7', '1', '7', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_7_8', 'Progress No. 8', '8', '1', '7', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_7_9', '10', '9', '1', '7', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_8_5', 'Karl', '5', '1', '8', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_8_6', '04/01/2014', '6', '1', '8', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_8_7', '04/30/2014', '7', '1', '8', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_8_8', 'Progress No. 9', '8', '1', '8', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_8_9', '10', '9', '1', '8', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_9_5', 'Karl', '5', '1', '9', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_9_6', '04/02/2014', '6', '1', '9', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_9_7', '04/16/2014', '7', '1', '9', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_9_8', 'Progress No. 10', '8', '1', '9', '2', '1');
INSERT INTO `data` VALUES ('1_2_1_9_9', '10', '9', '1', '9', '2', '1');

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `depId` varchar(255) NOT NULL,
  `depName` varchar(50) NOT NULL,
  PRIMARY KEY (`depId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('0', 'ddd');
INSERT INTO `dept` VALUES ('1', 'abc');
INSERT INTO `dept` VALUES ('2', 'nba');

-- ----------------------------
-- Table structure for `field`
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field` (
  `fieldId` varchar(255) NOT NULL,
  `fieldName` varchar(50) NOT NULL,
  `dataType` varchar(50) NOT NULL,
  `objId` varchar(50) NOT NULL,
  `depId` varchar(50) NOT NULL,
  `groId` varchar(50) NOT NULL,
  PRIMARY KEY (`fieldId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of field
-- ----------------------------
INSERT INTO `field` VALUES ('0', 'ProjectID', '0', '0', '0', '0');
INSERT INTO `field` VALUES ('1', 'ProjectName', '1', '0', '0', '0');
INSERT INTO `field` VALUES ('2', 'Description', '1', '0', '0', '0');
INSERT INTO `field` VALUES ('3', 'StartDate', '2', '0', '0', '0');
INSERT INTO `field` VALUES ('4', 'Status', '0', '0', '0', '0');
INSERT INTO `field` VALUES ('5', 'UserName', '1', '1', '0', '0');
INSERT INTO `field` VALUES ('6', 'StartDate', '2', '1', '0', '0');
INSERT INTO `field` VALUES ('7', 'EndDate', '2', '1', '0', '0');
INSERT INTO `field` VALUES ('8', 'Progress', '1', '1', '0', '0');
INSERT INTO `field` VALUES ('9', 'ProjectID', '0', '1', '0', '0');

-- ----------------------------
-- Table structure for `gro`
-- ----------------------------
DROP TABLE IF EXISTS `gro`;
CREATE TABLE `gro` (
  `groId` varchar(255) NOT NULL,
  `groName` varchar(50) NOT NULL,
  `depId` varchar(50) NOT NULL,
  PRIMARY KEY (`groId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gro
-- ----------------------------
INSERT INTO `gro` VALUES ('1', 'nba', '2');
INSERT INTO `gro` VALUES ('2', 'Group2', '2');
INSERT INTO `gro` VALUES ('3', 'Group3', '2');
INSERT INTO `gro` VALUES ('4', 'Group4', '2');
INSERT INTO `gro` VALUES ('5', 'Group1', '2');

-- ----------------------------
-- Table structure for `object`
-- ----------------------------
DROP TABLE IF EXISTS `object`;
CREATE TABLE `object` (
  `objId` varchar(255) NOT NULL,
  `objName` varchar(50) NOT NULL,
  `depId` varchar(50) NOT NULL,
  `groId` varchar(50) NOT NULL,
  `rowNO` varchar(50) NOT NULL,
  PRIMARY KEY (`objId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of object
-- ----------------------------
INSERT INTO `object` VALUES ('0', 'Project', '0', '0', '11');
INSERT INTO `object` VALUES ('1', 'Detail', '0', '0', '15');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(255) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `usertype` int(11) NOT NULL,
  `groId` int(11) NOT NULL,
  `depId` int(11) NOT NULL,
  `fail` int(11) NOT NULL,
  `block` bit(1) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', 'Shihuan Shao', '000000', '0', '0', '0', '0', '\0');
INSERT INTO `user` VALUES ('1', 'Rambo', 'Rambo', '3', '0', '1', '1', '\0');
INSERT INTO `user` VALUES ('2', 'John', '2', '1', '0', '2', '0', '\0');
INSERT INTO `user` VALUES ('3', 'Karl', 'Karl', '2', '1', '2', '0', '\0');
INSERT INTO `user` VALUES ('4', 'Malone', 'Malone', '3', '1', '2', '0', '\0');
INSERT INTO `user` VALUES ('5', 'Jenny', '55', '3', '1', '2', '0', '\0');
