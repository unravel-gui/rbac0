/*
Navicat MySQL Data Transfer

Source Server         : eutopia
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : j_eutopia

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2024-06-04 12:04:21
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_menu
-- ----------------------------
INSERT INTO `j_menu` VALUES ('2', 'ComponentValue', 'PathValue', 'PathValue', 'null', 'null', 'n2222', null, null, null, '');
INSERT INTO `j_menu` VALUES ('3', 'ComponentValue', 'PathValue', null, null, null, null, null, null, null, '');

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
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_permission
-- ----------------------------
INSERT INTO `j_permission` VALUES ('8', 'Put', '/menu/putMenu', '添加菜单', '添加菜单的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('9', 'Delete', '/menu/delMenu', '删除菜单', '删除菜单的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('21', 'Post', '/menu/modifyMenu', '修改菜单', '修改菜单的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('25', 'Post', '/register', '普通用户注册', '普通用户注册的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('26', 'Post', '/login', '普通用户登录', '普通用户登录的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('27', 'Post', '/menu/getByPtype', '通过资源类型获得菜单', '通过资源类型获得菜单的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('29', 'Get', '/menu/allMenu', '获得所有菜单', '获得所有菜单的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('30', 'Post', '/menu/getMenu', '按菜单ID查询菜单', '按菜单ID查询菜单的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('33', 'Post', '/permission/getPermission', '按权限ID查询权限', '按权限ID查询权限的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('34', 'Post', '/permission/getByUser', '通过资源类型查询权限', '通过资源类型查询权限的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('35', 'Put', '/permission/putPermission', '添加权限', '添加权限的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('36', 'Get', '/permission/allPermission', '获得所有权限', '获得所有权限的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('37', 'Post', '/permission/modifyPermission', '修改权限', '修改权限的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('38', 'Post', '/ptype/assignPermissions', '资源类型分配资源', '资源类型分配资源，资源包含菜单和权限', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('39', 'Post', '/ptype/getByRole', '通过角色查询菜单', '通过角色查询菜单的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('40', 'Put', '/ptype/putPtype', '添加资源类型', '添加资源类型的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('41', 'Post', '/ptype/getPtype', '通过资源类型查询菜单', '通过资源类型查询菜单的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('42', 'Delete', '/ptype/delPtype', '删除资源类型', '删除资源类型的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('43', 'Post', '/ptype/modifyPtype', '修改资源类型', '修改资源类型的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('44', 'Get', '/ptype/allPtype', '查询所有的资源种类', '查询所有的资源种类的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('45', 'Post', '/role/assignRole', '角色分配资源类型', '角色分配资源类型的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('46', 'Post', '/role/getByUser', '通过用户ID获得角色', '通过用户ID获得角色的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('47', 'Post', '/role/getRole', '按角色ID获得角色', '按角色ID获得角色的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('48', 'Post', '/role/modifyRole', '修改角色', '修改角色的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('49', 'Put', '/role/putRole', '添加角色', '添加角色的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('50', 'Get', '/role/allRole', '获得所有角色', '获得所有角色的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('51', 'Delete', '/role/delRole', '删除角色', '删除角色的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('52', 'Post', '/user/addUser', '添加用户', '添加用户的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('53', 'Delete', '/user/delUser', '删除用户', '删除用户的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('54', 'Post', '/user/login', '管理员登录', '管理员登录的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('55', 'Post', '/user/assignRoles', '用户分配角色', '用户分配角色的参数', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('56', 'Get', '/user/allUser', '获得所有用户', '获得所有用户的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('57', 'Post', '/user/getUser', '按用户ID获得用户', '按用户ID获得用户的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('58', 'Post', '/user/modifyUser', '修改用户', '修改用户的描述', '2024-06-03 22:43:28', '2024-06-03 22:43:28');
INSERT INTO `j_permission` VALUES ('59', 'Post', '/user/inactive', '失效用户', '失效用户的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('60', 'Post', '/user/getByRole', '按角色ID查询用户', '按角色ID查询用户的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('61', 'Post', '/user/active', '激活用户', '激活用户的描述', '2024-06-03 22:43:27', '2024-06-03 22:43:27');
INSERT INTO `j_permission` VALUES ('136', 'GET', '/api/test', 'View Users after update aaaa', 'Allows users to view user data', '2024-06-03 12:25:30', '2024-06-03 12:25:30');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_ptype
-- ----------------------------

-- ----------------------------
-- Table structure for `j_ptype_menu`
-- ----------------------------
DROP TABLE IF EXISTS `j_ptype_menu`;
CREATE TABLE `j_ptype_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `tid` int(20) NOT NULL COMMENT '权限类型ID',
  `mid` int(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_ptype_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `j_ptype_permission`
-- ----------------------------
DROP TABLE IF EXISTS `j_ptype_permission`;
CREATE TABLE `j_ptype_permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `tid` int(20) NOT NULL COMMENT '权限类型ID',
  `pid` int(20) NOT NULL COMMENT '后端接口权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_ptype_permission
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_role
-- ----------------------------
INSERT INTO `j_role` VALUES ('1', '1', '1', '', null, null);
INSERT INTO `j_role` VALUES ('2', '2', '2', '', null, null);
INSERT INTO `j_role` VALUES ('3', '3', '3', '', null, null);

-- ----------------------------
-- Table structure for `j_role_ptype`
-- ----------------------------
DROP TABLE IF EXISTS `j_role_ptype`;
CREATE TABLE `j_role_ptype` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `rid` int(20) NOT NULL COMMENT '角色ID',
  `tid` int(20) NOT NULL COMMENT '权限类型ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_role_ptype
-- ----------------------------

-- ----------------------------
-- Table structure for `j_user`
-- ----------------------------
DROP TABLE IF EXISTS `j_user`;
CREATE TABLE `j_user` (
  `uid` int(20) NOT NULL AUTO_INCREMENT,
  `open_id` int(11) DEFAULT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `is_activce` bit(1) NOT NULL DEFAULT b'0',
  `update_at` datetime DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_user
-- ----------------------------
INSERT INTO `j_user` VALUES ('1', '1', '1', '1', '', null, null);
INSERT INTO `j_user` VALUES ('2', '2', '2', '2', '', null, null);

-- ----------------------------
-- Table structure for `j_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `j_user_role`;
CREATE TABLE `j_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `rid` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of j_user_role
-- ----------------------------
