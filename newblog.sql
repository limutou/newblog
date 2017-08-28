/*
MySQL Data Transfer
Source Host: bdm317877418.my3w.com
Source Database: bdm317877418_db
Target Host: bdm317877418.my3w.com
Target Database: bdm317877418_db
Date: 2017/8/28 21:35:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `articleid` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `user` int(11) NOT NULL COMMENT '文章作者ID',
  `category` int(11) NOT NULL COMMENT '目录ID',
  `tittle` varchar(255) NOT NULL COMMENT '文章标题',
  `richText` longtext NOT NULL COMMENT '文章内容',
  `simpleText` varchar(255) NOT NULL COMMENT '文章简介',
  `imgurl` varchar(255) DEFAULT NULL COMMENT '文章图片',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`articleid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT '0' COMMENT '类别状态0-正常,1-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentid` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `commentContent` varchar(255) NOT NULL COMMENT '评论内容',
  `opuser` int(11) NOT NULL COMMENT '评论用户ID',
  `article` int(11) NOT NULL COMMENT '文章ID',
  `comment` int(11) NOT NULL COMMENT '其他评论ID',
  `loginip` varchar(255) NOT NULL COMMENT '登录IP',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`commentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for upvote
-- ----------------------------
DROP TABLE IF EXISTS `upvote`;
CREATE TABLE `upvote` (
  `upvoteid` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `opuser` int(11) NOT NULL COMMENT '点赞用户ID',
  `article` int(11) NOT NULL COMMENT '点赞的文章ID',
  `comment` int(11) NOT NULL COMMENT '点赞的评论ID',
  `loginip` varchar(255) NOT NULL COMMENT '登录IP',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`upvoteid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(255) NOT NULL COMMENT '昵称',
  `userhead` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `signature` varchar(255) DEFAULT NULL COMMENT '签名',
  `role` int(11) DEFAULT '10' COMMENT '权限',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `user` VALUES ('21', 'username', 'D15D7F1F9D979245BAA228F9BAC70D39', 'email', 'nickname', 'userhead', 'signature', '100', '0', '2017-08-28 20:46:00', '2017-08-28 20:50:24');
