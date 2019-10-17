/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : backstage_management_of_housing

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 15/10/2019 23:43:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for housing_information
-- ----------------------------
DROP TABLE IF EXISTS `housing_information`;
CREATE TABLE `housing_information`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '位置',
  `community` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小区',
  `building_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '楼栋号',
  `floor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '楼层',
  `room_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间号',
  `housing_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房源面积',
  `rental_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '计租面积',
  `apartment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '户型',
  `building_structure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '建筑结构',
  `nature_of_lease` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '建筑结构',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of housing_information
-- ----------------------------
INSERT INTO `housing_information` VALUES (1, '城中区', '瑞景河畔', '16号楼', '1', '111', '65.97㎡', '65.97㎡', '一室一厅一卫', '混凝土', '公租房', '建成待租');
INSERT INTO `housing_information` VALUES (2, '城中区', '瑞景河畔', '16号楼', '1', '112', '67.47㎡', '67.47㎡', '一室一厅一卫', '混凝土', '公租房', '已配租');
INSERT INTO `housing_information` VALUES (3, '城中区', '瑞景河畔', '16号楼', '2', '121', '67.47㎡', '67.47㎡', '一室一厅一卫', '混凝土', '公租房', '建成待租');
INSERT INTO `housing_information` VALUES (4, '城中区', '瑞景河畔', '16号楼', '2', '122', '65.97㎡', '65.97㎡', '一室一厅一卫', '混凝土', '公租房', '建成待租');
INSERT INTO `housing_information` VALUES (5, '城中区', '瑞景河畔', '16号楼', '3', '131', '65.97㎡', '65.97㎡', '一室一厅一卫', '混凝土', '公租房', '建成待租');
INSERT INTO `housing_information` VALUES (6, '城中区', '瑞景河畔', '16号楼', '3', '132', '67.47㎡', '67.47㎡', '一室一厅一卫', '混凝土', '公租房', '建成待租');
INSERT INTO `housing_information` VALUES (7, '城中区', '瑞景河畔', '16号楼', '4', '141', '65.97㎡', '65.97㎡', '一室一厅一卫', '混凝土', '公租房', '建成待租');
INSERT INTO `housing_information` VALUES (8, '城中区', '瑞景河畔', '16号楼', '4', '142', '67.47㎡', '67.47㎡', '一室一厅一卫', '混凝土', '公租房', '已配租');
INSERT INTO `housing_information` VALUES (9, '城中区', '瑞景河畔', '16号楼', '5', '151', '65.97㎡', '65.97㎡', '一室一厅一卫', '混凝土', '公租房', '建成待租');
INSERT INTO `housing_information` VALUES (10, '城中区', '瑞景河畔', '16号楼', '5', '152', '65.97㎡', '65.97㎡', '一室一厅一卫', '混凝土', '公租房', '已配租');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
