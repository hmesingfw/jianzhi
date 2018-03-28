/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.199
Source Server Version : 50712
Source Host           : 192.168.0.199:3306
Source Database       : jianzhi

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-03-28 10:32:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zcourse_sort
-- ----------------------------
DROP TABLE IF EXISTS `zcourse_sort`;
CREATE TABLE `zcourse_sort` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) DEFAULT NULL COMMENT '所有父级编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `code` varchar(100) DEFAULT NULL COMMENT '编码',
  `price` varchar(100) DEFAULT NULL COMMENT '价格',
  `validity` varchar(20) DEFAULT NULL COMMENT '有效期天数',
  `type` char(1) DEFAULT NULL COMMENT '类型',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程分类';

-- ----------------------------
-- Records of zcourse_sort
-- ----------------------------
INSERT INTO `zcourse_sort` VALUES ('79a145885af446049f40bda858fb0d0c', '0', '', '一级建造师', '0', '', '0.01', '130', '', '1', '2018-03-09 16:15:35', '1', '2018-03-09 16:15:35', '', '0');
INSERT INTO `zcourse_sort` VALUES ('79bc56a6519c483bbcebee8323632101', '0', '', '张韬测试', '0', '', '0.01', '130', '', '1', '2018-03-17 15:48:53', '1', '2018-03-27 10:38:43', '', '0');
INSERT INTO `zcourse_sort` VALUES ('915b1a6622c648a2b1cdb88c7e678236', '79bc56a6519c483bbcebee8323632101', '', '张韬测试1', '0', '', '0.01', '130', '', '1', '2018-03-17 15:49:03', '1', '2018-03-17 15:49:03', '', '0');
INSERT INTO `zcourse_sort` VALUES ('9b79b5abf5954f9ea0ca4d02fd6acadd', 'fd62e553584e42c6b22c453ed50c6c92', '', '二级监理程师', '0', '', '0.01', '130', '', '1', '2018-03-09 16:16:02', '1', '2018-03-09 16:16:02', '', '0');
INSERT INTO `zcourse_sort` VALUES ('fd62e553584e42c6b22c453ed50c6c92', '79a145885af446049f40bda858fb0d0c', '', '监理工程师', '0', '', '0.01', '130', '', '1', '2018-03-09 16:15:45', '1', '2018-03-09 16:15:45', '', '0');
