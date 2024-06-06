/*
Navicat MySQL Data Transfer

Source Server         : eutopia
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : j_eutopia

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2024-06-06 15:02:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `j_menu`
-- ----------------------------
DROP TABLE IF EXISTS `j_menu`;
CREATE TABLE `j_menu` (
  `mid` int(20) NOT NULL AUTO_INCREMENT,
  `component` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `redirect` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `is_leaf` int(20) DEFAULT NULL,
  `hidden` bit(1) DEFAULT b'0',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `j_permission`
-- ----------------------------
DROP TABLE IF EXISTS `j_permission`;
CREATE TABLE `j_permission` (
  `pid` int(20) NOT NULL AUTO_INCREMENT,
  `method` varchar(32) NOT NULL COMMENT 'http方法',
  `router` varchar(255) NOT NULL COMMENT '路由',
  `name` varchar(255) NOT NULL,
  `description` text,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `Interface` (`method`,`router`),
  UNIQUE KEY `Interface_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `j_ptype`
-- ----------------------------
DROP TABLE IF EXISTS `j_ptype`;
CREATE TABLE `j_ptype` (
  `tid` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '资源类型名称',
  `description` text COMMENT '资源类型的描述',
  PRIMARY KEY (`tid`),
  UNIQUE KEY `UniqueName` (`name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `j_ptype_menu`
-- ----------------------------
DROP TABLE IF EXISTS `j_ptype_menu`;
CREATE TABLE `j_ptype_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `tid` int(20) NOT NULL COMMENT '权限类型ID',
  `mid` int(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `j_ptype_permission`
-- ----------------------------
DROP TABLE IF EXISTS `j_ptype_permission`;
CREATE TABLE `j_ptype_permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `tid` int(20) NOT NULL COMMENT '权限类型ID',
  `pid` int(20) NOT NULL COMMENT '后端接口权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `j_role`
-- ----------------------------
DROP TABLE IF EXISTS `j_role`;
CREATE TABLE `j_role` (
  `rid` int(20) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) NOT NULL,
  `description` text,
  `is_active` bit(1) DEFAULT b'0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `unique_role_name` (`rolename`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `j_role_ptype`
-- ----------------------------
DROP TABLE IF EXISTS `j_role_ptype`;
CREATE TABLE `j_role_ptype` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `rid` int(20) NOT NULL COMMENT '角色ID',
  `tid` int(20) NOT NULL COMMENT '权限类型ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `j_user`
-- ----------------------------
DROP TABLE IF EXISTS `j_user`;
CREATE TABLE `j_user` (
  `uid` int(20) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(64) DEFAULT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `is_activce` bit(1) NOT NULL DEFAULT b'0',
  `update_at` datetime DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `j_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `j_user_role`;
CREATE TABLE `j_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `rid` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

