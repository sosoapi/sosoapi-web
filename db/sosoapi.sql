/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-07-18 16:54:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_api_doc
-- ----------------------------
DROP TABLE IF EXISTS `t_api_doc`;
CREATE TABLE `t_api_doc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '描述信息',
  `host` varchar(64) DEFAULT NULL COMMENT '访问主机',
  `base_path` varchar(128) DEFAULT NULL COMMENT '基路径',
  `pub` tinyint(4) DEFAULT '0',
  `open` tinyint(4) NOT NULL DEFAULT '0',
  `scheme` varchar(32) DEFAULT NULL,
  `consume` varchar(128) DEFAULT NULL,
  `produce` varchar(128) DEFAULT NULL,
  `version` varchar(16) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_proj_id` (`proj_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='api文档信息';

-- ----------------------------
-- Table structure for t_inter
-- ----------------------------
DROP TABLE IF EXISTS `t_inter`;
CREATE TABLE `t_inter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL COMMENT 'api文档id',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块id',
  `name` varchar(64) DEFAULT NULL COMMENT '接口方法名称',
  `path` varchar(128) DEFAULT NULL COMMENT '请求url',
  `method` varchar(8) DEFAULT NULL COMMENT '请求方法',
  `scheme` varchar(32) DEFAULT NULL COMMENT '请求协议',
  `summary` varchar(64) DEFAULT NULL COMMENT '概要信息',
  `description` text COMMENT '描述信息',
  `consume` varchar(128) DEFAULT NULL COMMENT '请求格式',
  `produce` varchar(128) DEFAULT NULL COMMENT '响应格式',
  `deprecated` tinyint(4) DEFAULT NULL COMMENT '是否弃用',
  `sort_weight` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_doc_id` (`doc_id`),
  KEY `idx_module_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='接口信息';

-- ----------------------------
-- Table structure for t_inter_param
-- ----------------------------
DROP TABLE IF EXISTS `t_inter_param`;
CREATE TABLE `t_inter_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL,
  `inter_id` bigint(20) DEFAULT NULL COMMENT '接口id',
  `code` varchar(128) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `type` varchar(32) DEFAULT NULL COMMENT '数据类型',
  `format` varchar(64) DEFAULT NULL COMMENT '格式化',
  `position` varchar(16) DEFAULT NULL COMMENT '参数位置',
  `required` tinyint(4) DEFAULT NULL COMMENT '是否必输项',
  `cust_schema` text,
  `ext_schema` text,
  `ref_schema_id` bigint(20) DEFAULT NULL,
  `def_value` varchar(512) DEFAULT NULL COMMENT '默认值',
  PRIMARY KEY (`id`),
  KEY `idx_inter_id` (`inter_id`),
  KEY `idx_doc_id` (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='接口参数';

-- ----------------------------
-- Table structure for t_inter_resp
-- ----------------------------
DROP TABLE IF EXISTS `t_inter_resp`;
CREATE TABLE `t_inter_resp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL,
  `inter_id` bigint(20) DEFAULT NULL COMMENT '接口id',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `name` varchar(128) DEFAULT NULL,
  `description` text COMMENT '描述信息',
  `type` varchar(32) DEFAULT NULL COMMENT '响应类型',
  `ref_schema_id` bigint(20) DEFAULT NULL COMMENT '响应数据结构id',
  `def` tinyint(4) DEFAULT NULL COMMENT '是否是默认',
  `required` tinyint(4) DEFAULT NULL,
  `cust_schema` text COMMENT '自定义结构体',
  `ext_schema` text,
  `sort_weight` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_inter_id` (`inter_id`),
  KEY `idx_doc_id` (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='接口响应';

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL COMMENT 'api文档id',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `sort_weight` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_doc_id` (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='模块信息';

-- ----------------------------
-- Table structure for t_proj
-- ----------------------------
DROP TABLE IF EXISTS `t_proj`;
CREATE TABLE `t_proj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `status` varchar(16) DEFAULT NULL COMMENT '项目状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='项目信息';

-- ----------------------------
-- Table structure for t_proj_log
-- ----------------------------
DROP TABLE IF EXISTS `t_proj_log`;
CREATE TABLE `t_proj_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '发布者id',
  `pub_date` datetime DEFAULT NULL COMMENT '发布时间',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='变更记录';

-- ----------------------------
-- Table structure for t_proj_mem
-- ----------------------------
DROP TABLE IF EXISTS `t_proj_mem`;
CREATE TABLE `t_proj_mem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role` varchar(32) DEFAULT NULL,
  `proj_role_id` bigint(20) DEFAULT NULL COMMENT '项目角色id',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_proj_id` (`proj_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='项目成员';

-- ----------------------------
-- Table structure for t_proj_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_proj_privilege`;
CREATE TABLE `t_proj_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `code` varchar(16) DEFAULT NULL COMMENT '编码',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目权限';

-- ----------------------------
-- Table structure for t_proj_role
-- ----------------------------
DROP TABLE IF EXISTS `t_proj_role`;
CREATE TABLE `t_proj_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `code` varchar(32) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `description` text COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目角色';

-- ----------------------------
-- Table structure for t_proj_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_proj_role_privilege`;
CREATE TABLE `t_proj_role_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `proj_role_id` bigint(20) DEFAULT NULL COMMENT '项目角色id',
  `proj_privilege_id` bigint(20) DEFAULT NULL COMMENT '项目权限id',
  PRIMARY KEY (`id`),
  KEY `idx_proj_role_id` (`proj_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目角色-权限';

-- ----------------------------
-- Table structure for t_resp_schema
-- ----------------------------
DROP TABLE IF EXISTS `t_resp_schema`;
CREATE TABLE `t_resp_schema` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `doc_id` bigint(20) DEFAULT NULL COMMENT 'api文档id',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块id',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `cust_schema` text COMMENT '结构',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ref_schema_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_doc_id` (`doc_id`),
  KEY `idx_module_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='响应数据结构';

-- ----------------------------
-- Table structure for t_suggest
-- ----------------------------
DROP TABLE IF EXISTS `t_suggest`;
CREATE TABLE `t_suggest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `content` text,
  `deal_date` datetime DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `type` varchar(16) DEFAULT 'bug',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='模板';

-- ----------------------------
-- Table structure for t_sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_msg`;
CREATE TABLE `t_sys_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '发布者id',
  `pub_date` datetime DEFAULT NULL COMMENT '发布时间',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `cancel` tinyint(4) DEFAULT NULL COMMENT '撤销',
  `cancel_date` datetime DEFAULT NULL COMMENT '撤销时间',
  `msg_type` varchar(16) DEFAULT NULL COMMENT '消息类型',
  `msg_status` varchar(16) DEFAULT NULL COMMENT '消息状态',
  `user_role` varchar(16) DEFAULT NULL COMMENT '接收对象类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='系统消息';

-- ----------------------------
-- Table structure for t_user_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_user_basic`;
CREATE TABLE `t_user_basic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否验证',
  `role` varchar(32) DEFAULT 'normal',
  `locked` tinyint(1) DEFAULT '0' COMMENT '是否锁定',
  `locked_date` datetime DEFAULT NULL COMMENT '锁定时间',
  `register_ip` varchar(32) DEFAULT NULL COMMENT '注册ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本信息';

-- ----------------------------
-- Table structure for t_user_cube
-- ----------------------------
DROP TABLE IF EXISTS `t_user_cube`;
CREATE TABLE `t_user_cube` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `total_regist_count` int(11) DEFAULT NULL COMMENT '总注册用户数',
  `day_regist_count` int(11) DEFAULT NULL COMMENT '日注册用户数',
  `day_login_count` int(11) DEFAULT NULL COMMENT '日登录用户数',
  `day_old_login_count` int(11) DEFAULT NULL COMMENT '日登陆老用户数',
  `total_proj_count` int(11) DEFAULT NULL COMMENT '总项目数',
  `day_proj_count` int(11) DEFAULT NULL COMMENT '日新增项目数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户统计';

-- ----------------------------
-- Table structure for t_user_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_user_detail`;
CREATE TABLE `t_user_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `area_id` bigint(20) DEFAULT '-1' COMMENT '区域id',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `gender` varchar(11) DEFAULT '1' COMMENT '性别',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `zip_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `head_url` varchar(255) DEFAULT NULL COMMENT '头像url',
  `university` varchar(32) DEFAULT NULL COMMENT '毕业院校',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户详细信息';

-- ----------------------------
-- Table structure for t_user_ext
-- ----------------------------
DROP TABLE IF EXISTS `t_user_ext`;
CREATE TABLE `t_user_ext` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `last_fetch_sys_msg_date` datetime DEFAULT NULL COMMENT '最后获取系统消息时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户扩展信息';

-- ----------------------------
-- Table structure for t_user_login
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login`;
CREATE TABLE `t_user_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modify_date` datetime NOT NULL COMMENT '修改时间',
  `login_date` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_failure_count` int(11) NOT NULL COMMENT '连续登陆失败次数',
  `login_count` int(11) DEFAULT '0',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登陆ip',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `login_type` varchar(16) DEFAULT '0' COMMENT '登陆方式',
  `login_status` varchar(16) DEFAULT NULL,
  `auth_code` varchar(8) DEFAULT NULL COMMENT '登陆验证码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户登陆信息';

-- ----------------------------
-- Table structure for t_user_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_user_msg`;
CREATE TABLE `t_user_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `sys` tinyint(4) DEFAULT NULL COMMENT '是否系统消息',
  `sys_msg_id` bigint(20) DEFAULT NULL COMMENT '系统消息id',
  `sender_id` bigint(20) DEFAULT NULL COMMENT '发送者id',
  `receiver_id` bigint(20) DEFAULT NULL COMMENT '接收者id',
  `pub_date` datetime DEFAULT NULL COMMENT '发布时间',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `msg_type` varchar(16) DEFAULT NULL COMMENT '消息类型',
  `deal` tinyint(4) DEFAULT NULL COMMENT '是否已读',
  `deal_date` datetime DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户消息';

-- ----------------------------
-- Table structure for t_user_token
-- ----------------------------
DROP TABLE IF EXISTS `t_user_token`;
CREATE TABLE `t_user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `login_ip` varchar(64) DEFAULT NULL COMMENT '登陆ip',
  `token` varchar(128) DEFAULT NULL COMMENT 'token信息',
  `expire_date` datetime DEFAULT NULL COMMENT '失效时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_token` (`token`),
  UNIQUE KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='登陆凭证';

-- ----------------------------
-- Procedure structure for clean
-- ----------------------------
DROP PROCEDURE IF EXISTS `clean`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `clean`()
BEGIN
  -- 无需清数据相关的表
	DECLARE except_table VARCHAR(255) DEFAULT '';
  -- 存储表名
  DECLARE clean_name VARCHAR(255);
  -- 动态sql
  DECLARE sql_text VARCHAR(200); 
	-- 遍历数据结束标志
	DECLARE done INT DEFAULT FALSE;
  -- 获取所有的表
	DECLARE cur CURSOR FOR SELECT table_name FROM information_schema. TABLES WHERE table_schema = 'sosoapi';
	-- 将结束标志绑定到游标
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	-- 打开游标
	OPEN cur;

	-- 开始循环
	read_loop :
		LOOP
			-- 提取游标里的数据
			FETCH cur INTO clean_name;
			select clean_name;
			-- 声明结束的时候
			IF done THEN
				LEAVE read_loop;
			END IF;

      
			IF FIND_IN_SET(clean_name,except_table) = 0 THEN
        SET sql_text = CONCAT('delete from ',clean_name);
        SELECT sql_text; 
				SET @sql_text=sql_text;
				PREPARE stmt FROM @sql_text;
				EXECUTE stmt;
				DEALLOCATE PREPARE stmt;  
				-- DEALLOCATE PREPARE stmt;
			END IF;
		END LOOP;
	-- 关闭游标
	CLOSE cur;
END
;;
DELIMITER ;
