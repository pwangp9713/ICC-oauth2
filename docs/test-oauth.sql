/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50516
Source Host           : localhost:3306
Source Database       : test-oauth

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2019-02-18 17:00:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `username` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'user', 'user');

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('test', '1', '123456');

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `resourceIds` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `appSecret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grantTypes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `redirectUrl` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authorities` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,
  `autoApproveScopes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '该字段的值是将access_token的值通过MD5加密后存储的.',
  `token` blob COMMENT '存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值.\r\n',
  `authentication_id` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的',
  `user_name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id\r\n',
  `client_id` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authentication` blob COMMENT '存储将OAuth2Authentication.java对象序列化后的二进制数据.',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `refresh_token` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的.',
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='生成的token';

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientId` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键,必须唯一,不能为空. \r\n用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). \r\n对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.',
  `resource_ids` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource". ',
  `client_secret` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用于指定客户端(client)的访问密匙',
  `scope` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write". ',
  `authorized_grant_types` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password". ',
  `web_server_redirect_uri` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致.',
  `authorities` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER". \r\n',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天). ',
  `additional_information` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '数据的创建时间',
  `archived` int(1) unsigned DEFAULT '0' COMMENT '用于标识客户端是否已存档(即实现逻辑删除),默认值为''0''(即未存档).',
  `trusted` int(1) unsigned DEFAULT '0' COMMENT '设置客户端是否为受信任的,默认为''0''(即不受信任的,1为受信任的). ',
  `autoapprove` varchar(256) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '设置用户是否自动Approval操作, 默认值为 ''false'', 可选值包括 ''true'',''false'', ''read'',''write''. ',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='客户端信息';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('admin', '', '$2a$10$6lIOO2/uSAjzEP37.X1qD.orufLsKXQQ4JIsimxPrNpvmpCPuRnCS', 'all', 'password,authorization_code,refresh_token,implicit,client_credentials', 'http://www.baidu.com/', '', null, null, '', '2019-02-18 15:11:55', '0', '0', 'true');
INSERT INTO `oauth_client_details` VALUES ('client', null, '$2a$10$6lIOO2/uSAjzEP37.X1qD.orufLsKXQQ4JIsimxPrNpvmpCPuRnCS', 'all', 'password,authorization_code,refresh_token,implicit,client_credentials', 'http://www.baidu.com/', null, null, null, null, '2019-02-18 14:57:58', '0', '0', 'true');
INSERT INTO `oauth_client_details` VALUES ('client_1', 'order', '123456', 'read,write', 'authorization_code,refresh_token,password', null, 'ROLE_USER', null, null, null, '2019-02-18 15:16:42', '0', '0', '0');
INSERT INTO `oauth_client_details` VALUES ('client_zuul', null, '$2a$10$6lIOO2/uSAjzEP37.X1qD.orufLsKXQQ4JIsimxPrNpvmpCPuRnCS', 'all', 'password,authorization_code,refresh_token,implicit,client_credentials', 'http://www.baidu.com/', null, null, null, null, '2019-02-18 14:07:29', '0', '0', 'true');
INSERT INTO `oauth_client_details` VALUES ('second', null, '{noop}secret', 'read', 'authorization_code,password,refresh_token,implicit', 'http://localhost:8081/', null, '30', '1800', '{}', '2019-02-15 16:59:36', '0', '0', 'true');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '从服务器端获取到的access_token的值.',
  `token` blob COMMENT '这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据.\r\n',
  `authentication_id` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. ',
  `user_name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录时的用户名',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `client_id` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'oauth_client_details 主键',
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `code` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '存储服务端系统生成的code的值(未加密).',
  `authentication` blob COMMENT '存储将AuthorizationRequestHolder.java对象序列化后的二进制数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='授权码';

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `token_id` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的.\r\n',
  `token` blob COMMENT '存储将OAuth2RefreshToken.java对象序列化后的二进制数据.',
  `authentication` blob COMMENT '存储将OAuth2Authentication.java对象序列化后的二进制数据.\r\n'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT=' 刷新token';

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
