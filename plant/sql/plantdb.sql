/*
Navicat MySQL Data Transfer

Source Server         : mysqldatabase
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : plantdb

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2024-01-14 11:16:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `distribution`
-- ----------------------------
DROP TABLE IF EXISTS `distribution`;
CREATE TABLE `distribution` (
  `distributionID` int(11) NOT NULL AUTO_INCREMENT,
  `parentID` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `level` enum('Province','City','County') DEFAULT NULL,
  PRIMARY KEY (`distributionID`),
  KEY `parentID` (`parentID`) USING BTREE,
  CONSTRAINT `distribution_ibfk_1` FOREIGN KEY (`parentID`) REFERENCES `distribution` (`distributionID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of distribution
-- ----------------------------
INSERT INTO `distribution` VALUES ('4', null, '四川', 'Province');
INSERT INTO `distribution` VALUES ('5', '4', '泸州', 'City');
INSERT INTO `distribution` VALUES ('6', '5', '', 'County');

-- ----------------------------
-- Table structure for `maintenancetask`
-- ----------------------------
DROP TABLE IF EXISTS `maintenancetask`;
CREATE TABLE `maintenancetask` (
  `taskID` int(11) NOT NULL AUTO_INCREMENT,
  `taskName` varchar(255) DEFAULT NULL,
  `executionTime` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `personnel` varchar(255) DEFAULT NULL,
  `description` text,
  `plantID` int(11) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `bool` varchar(255) DEFAULT '未养护',
  PRIMARY KEY (`taskID`),
  KEY `plantID` (`plantID`),
  CONSTRAINT `maintenancetask_ibfk_1` FOREIGN KEY (`plantID`) REFERENCES `plant` (`plantID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintenancetask
-- ----------------------------
INSERT INTO `maintenancetask` VALUES ('2', '1', '11', '11', '1', '1', '1', '1', '2023-12-25 20:47:48', null, '未养护');
INSERT INTO `maintenancetask` VALUES ('4', 'care', '2022-9-8', 'bjfu', 'care1', 'water', '1', 'manger', '2023-12-25 21:15:27', null, '未养护');
INSERT INTO `maintenancetask` VALUES ('5', 'test', '2023-12-11', 'bjfu', 'care1', 'water', '1', 'manger', '2023-12-26 21:22:55', null, '未养护');

-- ----------------------------
-- Table structure for `pestcontrol`
-- ----------------------------
DROP TABLE IF EXISTS `pestcontrol`;
CREATE TABLE `pestcontrol` (
  `pestControlID` int(11) NOT NULL AUTO_INCREMENT,
  `pestName` varchar(255) DEFAULT NULL,
  `controlMethod` text,
  `pesticideName` varchar(255) DEFAULT NULL,
  `pesticideAmount` varchar(255) DEFAULT NULL,
  `effectiveDuration` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`pestControlID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pestcontrol
-- ----------------------------
INSERT INTO `pestcontrol` VALUES ('1', 'genish', 'lol', 'mkbl', '10', '1', 'care1', '2023-12-25 21:30:25', null);

-- ----------------------------
-- Table structure for `plant`
-- ----------------------------
DROP TABLE IF EXISTS `plant`;
CREATE TABLE `plant` (
  `plantID` int(11) NOT NULL AUTO_INCREMENT,
  `diseaseName` varchar(255) DEFAULT NULL,
  `commonName` varchar(255) DEFAULT NULL,
  `morphology` varchar(255) DEFAULT NULL,
  `cultivationTips` varchar(255) DEFAULT NULL,
  `pestControlMeasures` varchar(255) DEFAULT NULL,
  `applicationValue` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`plantID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plant
-- ----------------------------
INSERT INTO `plant` VALUES ('1', '', '断肠草', '多年生草本，高20-60（-100）厘米。须根数条，粗线形，长8-10厘米，粗1-1.5毫米，黄色，干时茶褐色；根茎短，被残枯的基生叶鞘。', '', '', '', 'admin', '2023-12-25 00:00:00', '2023-12-25 00:00:00');

-- ----------------------------
-- Table structure for `plantimage`
-- ----------------------------
DROP TABLE IF EXISTS `plantimage`;
CREATE TABLE `plantimage` (
  `imageID` int(11) NOT NULL AUTO_INCREMENT,
  `plantID` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `photographer` varchar(255) DEFAULT NULL,
  `photoPath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`imageID`),
  KEY `plantID` (`plantID`) USING BTREE,
  CONSTRAINT `plantimage_ibfk_1` FOREIGN KEY (`plantID`) REFERENCES `plant` (`plantID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plantimage
-- ----------------------------
INSERT INTO `plantimage` VALUES ('1', '1', '', '', '', '');

-- ----------------------------
-- Table structure for `plant_classify`
-- ----------------------------
DROP TABLE IF EXISTS `plant_classify`;
CREATE TABLE `plant_classify` (
  `plantID` int(11) NOT NULL AUTO_INCREMENT,
  `commonName` varchar(255) DEFAULT NULL,
  `growthEnvironment` varchar(255) DEFAULT NULL,
  `taxonomyID` int(11) DEFAULT NULL,
  `distributionID` int(11) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`plantID`),
  KEY `taxonomyID` (`taxonomyID`) USING BTREE,
  KEY `distributionID` (`distributionID`) USING BTREE,
  CONSTRAINT `plant_classify_ibfk_1` FOREIGN KEY (`taxonomyID`) REFERENCES `taxonomy_classify` (`taxonomyID`),
  CONSTRAINT `plant_classify_ibfk_2` FOREIGN KEY (`distributionID`) REFERENCES `distribution` (`distributionID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plant_classify
-- ----------------------------
INSERT INTO `plant_classify` VALUES ('1', '断肠草', '林下、林缘、灌丛下、草坡或路边。', '6', '6', 'admin', '2023-12-26 00:00:00', null);

-- ----------------------------
-- Table structure for `plant_monitoring`
-- ----------------------------
DROP TABLE IF EXISTS `plant_monitoring`;
CREATE TABLE `plant_monitoring` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monitoring_time` date DEFAULT NULL,
  `monitoring_personnel` varchar(255) DEFAULT NULL,
  `monitoring_location` varchar(255) DEFAULT NULL,
  `monitored_object` varchar(255) DEFAULT NULL,
  `monitored_objectId` int(11) DEFAULT NULL,
  `monitored_value` float DEFAULT NULL,
  `monitoring_index` varchar(255) DEFAULT NULL,
  `monitoring_device` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_time` date DEFAULT NULL,
  `updated_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plant_monitoring
-- ----------------------------
INSERT INTO `plant_monitoring` VALUES ('1', '2023-12-25', 'watch1', '1', '1', '1', '2', 'PH', 'H2', 'watch1', '2023-12-25', '2023-12-25');

-- ----------------------------
-- Table structure for `taxonomy`
-- ----------------------------
DROP TABLE IF EXISTS `taxonomy`;
CREATE TABLE `taxonomy` (
  `taxonomyID` int(11) NOT NULL AUTO_INCREMENT,
  `family` varchar(255) DEFAULT NULL,
  `genus` varchar(255) DEFAULT NULL,
  `species` varchar(255) DEFAULT NULL,
  `plantID` int(11) DEFAULT NULL,
  PRIMARY KEY (`taxonomyID`),
  KEY `plantID` (`plantID`) USING BTREE,
  CONSTRAINT `taxonomy_ibfk_1` FOREIGN KEY (`plantID`) REFERENCES `plant` (`plantID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taxonomy
-- ----------------------------
INSERT INTO `taxonomy` VALUES ('1', '罂粟科', '紫堇属', '南黄堇 ', '1');

-- ----------------------------
-- Table structure for `taxonomy_classify`
-- ----------------------------
DROP TABLE IF EXISTS `taxonomy_classify`;
CREATE TABLE `taxonomy_classify` (
  `taxonomyID` int(11) NOT NULL AUTO_INCREMENT,
  `parentID` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `level` enum('Family','Genus','Species') DEFAULT NULL,
  PRIMARY KEY (`taxonomyID`),
  KEY `parentID` (`parentID`) USING BTREE,
  CONSTRAINT `taxonomy_classify_ibfk_1` FOREIGN KEY (`parentID`) REFERENCES `taxonomy_classify` (`taxonomyID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taxonomy_classify
-- ----------------------------
INSERT INTO `taxonomy_classify` VALUES ('4', null, '罂粟科', 'Family');
INSERT INTO `taxonomy_classify` VALUES ('5', '4', '紫堇属', 'Genus');
INSERT INTO `taxonomy_classify` VALUES ('6', '5', '南黄堇', 'Species');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) DEFAULT NULL,
  `password` char(255) DEFAULT NULL,
  `user_type` char(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', 'msydazen@bjfu.edu.cn');
INSERT INTO `user` VALUES ('2', 'care1', '123456', 'careTaker', 'care1@163.com');
INSERT INTO `user` VALUES ('3', 'watch1', '123456', 'monitor', 'watch1@163.com');
INSERT INTO `user` VALUES ('4', 'manger', '123456', 'department', 'manger@163.com');
INSERT INTO `user` VALUES ('5', 'manger2', '123456', 'department', 'manger2@163.com');
