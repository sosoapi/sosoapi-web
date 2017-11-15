/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-10-18 17:01:22
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
  UNIQUE KEY `idx_proj_id` (`proj_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='api文档信息';

-- ----------------------------
-- Records of t_api_doc
-- ----------------------------
INSERT INTO `t_api_doc` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', 'sosoapi_demo', '该demo主要用于汇集常见的接口编辑事例。\n<br/>\n接口测试过程中如果出现ajax跨域请求问题可参考\"常见问题->线下部署\"中的跨域请求相关资料。\n<br/>\n<br/>\n技术交流群:305629848', 'www.sosoapi.com', '/demo', '0', '1', '', '', '', '1.0.0');

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
  KEY `idx_doc_id` (`doc_id`) USING BTREE,
  KEY `idx_module_id` (`module_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='接口信息';

-- ----------------------------
-- Records of t_inter
-- ----------------------------
INSERT INTO `t_inter` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', '2', '新增用户(自定义参数)', '/user/complex/add.htm', 'POST', 'HTTP', null, '新增用户(自定义参数)', 'application/json', 'application/json', '0', '0');
INSERT INTO `t_inter` VALUES ('2', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '2', '查询用户列表(多层嵌套)', '/user/complex/list.htm', 'GET', 'HTTP', null, '查询用户列表(多层嵌套)', 'application/json', 'application/json', '0', '0');
INSERT INTO `t_inter` VALUES ('3', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '2', '获取用户详情(多层嵌套)', '/user/complex/{userId}/info.htm', 'GET', 'HTTP', null, '获取用户详情(多层嵌套)', 'application/json', 'application/json', '0', '0');
INSERT INTO `t_inter` VALUES ('4', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '新增用户', '/user/simple/add.htm', 'POST', 'HTTP', null, '新增用户信息', 'application/json', 'application/json', '0', '0');
INSERT INTO `t_inter` VALUES ('5', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '查询用户列表', '/user/simple/list.htm', 'GET', 'HTTP', null, '获取用户列表', 'application/json', 'application/json', '0', '0');
INSERT INTO `t_inter` VALUES ('6', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '删除用户', '/user/simple/{userId}/del.htm', 'DELETE', 'HTTP', null, '删除指定用户', 'application/json', 'application/json', '0', '0');
INSERT INTO `t_inter` VALUES ('7', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '查询用户', '/user/simple/{userId}/info.htm', 'GET', 'HTTP', null, '查询用户信息', 'application/json', 'application/json', '0', '0');
INSERT INTO `t_inter` VALUES ('8', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', '更新用户', '/user/simple/{userId}/update.htm', 'POST', 'HTTP', null, '更新用户信息', 'application/json', 'application/json', '0', '0');
INSERT INTO `t_inter` VALUES ('9', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '1', '上传图片', '/user/simple/{userId}/upload.htm', 'POST', 'HTTP', null, '上传', 'application/json', 'application/json', '0', '0');

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
  KEY `idx_inter_id` (`inter_id`) USING BTREE,
  KEY `idx_doc_id` (`doc_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='接口参数';

-- ----------------------------
-- Records of t_inter_param
-- ----------------------------
INSERT INTO `t_inter_param` VALUES ('1', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', 'userInfo', null, '用户信息', 'cust_json', 'json', 'body', '1', null, '{\n	\"email\": \"string,邮箱\",\n        \"nickName\": \"string,昵称\"\n}', null, null);
INSERT INTO `t_inter_param` VALUES ('2', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '3', 'userId', null, '用户id', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, null);
INSERT INTO `t_inter_param` VALUES ('3', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', 'email', null, '邮箱', 'sys_string', '', 'formData', '1', null, null, null, null);
INSERT INTO `t_inter_param` VALUES ('4', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', 'nickName', null, '昵称', 'sys_string', '', 'formData', '1', null, null, null, null);
INSERT INTO `t_inter_param` VALUES ('5', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', 'age', null, '年龄', 'sys_integer_int32', 'int32', 'formData', '0', null, null, null, null);
INSERT INTO `t_inter_param` VALUES ('6', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '6', 'userId', null, '用户id', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, null);
INSERT INTO `t_inter_param` VALUES ('7', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '7', 'userId', null, '用户id', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, null);
INSERT INTO `t_inter_param` VALUES ('8', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '8', 'userId', null, '用户id', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, null);
INSERT INTO `t_inter_param` VALUES ('9', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '8', 'nickName', null, '昵称', 'sys_string', '', 'query', '1', null, null, null, null);
INSERT INTO `t_inter_param` VALUES ('10', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '9', 'userId', null, '', 'sys_integer_int64', 'int64', 'path', '1', null, null, null, '');
INSERT INTO `t_inter_param` VALUES ('11', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '9', 'img', null, '', 'sys_file', '', 'formData', '1', null, null, null, '');

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
  KEY `idx_inter_id` (`inter_id`) USING BTREE,
  KEY `idx_doc_id` (`doc_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='接口响应';

-- ----------------------------
-- Records of t_inter_resp
-- ----------------------------
INSERT INTO `t_inter_resp` VALUES ('1', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '1', 'default', null, '默认响应', 'sys_object', null, '0', '0', '[{\"code\":\"userId\",\"description\":\"用户id\",\"type\":\"sys_integer_int64\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '0');
INSERT INTO `t_inter_resp` VALUES ('2', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '2', 'default', null, '默认响应', 'sys_object', null, '0', '0', '[{\"code\":\"data\",\"description\":\"响应信息\",\"type\":\"sys_object\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"totalCount\",\"description\":\"总记录数\",\"type\":\"sys_integer_int32\",\"nodeId\":\"1010\",\"parentId\":\"10\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"list\",\"description\":\"用户列表\",\"type\":\"sys_array\",\"nodeId\":\"1011\",\"parentId\":\"10\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"SimpleUserInfo\",\"description\":\"用户列表\",\"type\":\"sys_ref\",\"refSchemaId\":2,\"nodeId\":\"101110\",\"parentId\":\"1011\",\"childList\":[],\"childRefSchemaIdList\":[2]},{\"code\":\"errorCode\",\"description\":\"错误码\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '0');
INSERT INTO `t_inter_resp` VALUES ('3', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '3', 'default', null, '默认响应', 'sys_object', null, '0', '0', '[{\"code\":\"addressList\",\"description\":\"地址列表\",\"type\":\"sys_array\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"street\",\"description\":\"街道名称\",\"type\":\"sys_string\",\"nodeId\":\"1010\",\"parentId\":\"10\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"city\",\"description\":\"城市名称\",\"type\":\"sys_string\",\"nodeId\":\"1011\",\"parentId\":\"10\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"nickName\",\"description\":\"昵称\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"age\",\"description\":\"年龄\",\"type\":\"sys_integer_int32\",\"nodeId\":\"12\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"userId\",\"description\":\"用户id\",\"type\":\"sys_integer_int64\",\"nodeId\":\"13\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '0');
INSERT INTO `t_inter_resp` VALUES ('4', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '3', 'error', null, '错误信息', 'sys_ref', '1', '0', '0', null, null, '0');
INSERT INTO `t_inter_resp` VALUES ('5', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '3', '001', null, '管理员', 'sys_object', null, '0', '0', '[{\"code\":\"admin\",\"description\":\"管理员信息\",\"type\":\"sys_ref\",\"refSchemaId\":2,\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[2]}]', null, '0');
INSERT INTO `t_inter_resp` VALUES ('6', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', 'default', null, '默认响应', 'sys_object', null, '0', '0', '[{\"code\":\"userId\",\"description\":\"用户id\",\"type\":\"sys_integer_int64\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '0');
INSERT INTO `t_inter_resp` VALUES ('7', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '4', '404', null, '无法找到对应服务', 'sys_string', null, '0', '0', null, null, '0');
INSERT INTO `t_inter_resp` VALUES ('8', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '5', 'default', null, '默认响应', 'sys_array', null, '0', '0', '[{\"code\":\"email\",\"description\":\"用户邮箱\",\"type\":\"sys_string\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"nickName\",\"description\":\"用户昵称\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"userId\",\"description\":\"用户id\",\"type\":\"sys_integer_int64\",\"nodeId\":\"12\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', null, '0');
INSERT INTO `t_inter_resp` VALUES ('9', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '6', 'default', null, '默认响应', 'sys_ref', '1', '0', '0', null, null, '0');
INSERT INTO `t_inter_resp` VALUES ('10', '2016-10-18 17:00:01', '2016-10-18 17:00:01', '1', '7', 'default', null, '默认响应', 'sys_ref', '2', '0', '0', null, null, '0');
INSERT INTO `t_inter_resp` VALUES ('11', '2016-10-18 17:00:02', '2016-10-18 17:00:02', '1', '8', 'default', null, '默认响应', 'sys_ref', '1', '0', '0', null, null, '0');

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
  KEY `idx_doc_id` (`doc_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='模块信息';

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', null, '入门', '', '0');
INSERT INTO `t_module` VALUES ('2', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', null, '进阶', '', '50');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='项目信息';

-- ----------------------------
-- Records of t_proj
-- ----------------------------
INSERT INTO `t_proj` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', 'sosoapi_demo', null, '该demo主要用于汇集常见的接口编辑事例。\n<br/>\n接口测试过程中如果出现ajax跨域请求问题可参考\"常见问题->线下部署\"中的跨域请求相关资料。\n<br/>\n<br/>\n技术交流群:305629848', 'open');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='变更记录';

-- ----------------------------
-- Records of t_proj_log
-- ----------------------------

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
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_proj_id` (`proj_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='项目成员';

-- ----------------------------
-- Records of t_proj_mem
-- ----------------------------
INSERT INTO `t_proj_mem` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', '1', 'admin', null);

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
-- Records of t_proj_privilege
-- ----------------------------

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
-- Records of t_proj_role
-- ----------------------------

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
  KEY `idx_proj_role_id` (`proj_role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目角色-权限';

-- ----------------------------
-- Records of t_proj_role_privilege
-- ----------------------------

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
  KEY `idx_doc_id` (`doc_id`) USING BTREE,
  KEY `idx_module_id` (`module_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='响应数据结构';

-- ----------------------------
-- Records of t_resp_schema
-- ----------------------------
INSERT INTO `t_resp_schema` VALUES ('1', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', null, 'ErrorCode', null, '错误码', '[{\"code\":\"errorCode\",\"description\":\"错误编码\",\"type\":\"sys_string\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"errorMsg\",\"description\":\"错误提示信息\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', 'sys_object', null);
INSERT INTO `t_resp_schema` VALUES ('2', '2016-10-18 17:00:00', '2016-10-18 17:00:00', '1', null, 'SimpleUserInfo', null, '简单用户信息', '[{\"code\":\"nickName\",\"description\":\"昵称\",\"type\":\"sys_string\",\"nodeId\":\"10\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"email\",\"description\":\"邮箱\",\"type\":\"sys_string\",\"nodeId\":\"11\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]},{\"code\":\"age\",\"description\":\"年龄\",\"type\":\"sys_integer_int32\",\"nodeId\":\"12\",\"parentId\":\"\",\"childList\":[],\"childRefSchemaIdList\":[]}]', 'sys_object', null);

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
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模板';

-- ----------------------------
-- Records of t_suggest
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统消息';

-- ----------------------------
-- Records of t_sys_msg
-- ----------------------------

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
  UNIQUE KEY `idx_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本信息';

-- ----------------------------
-- Records of t_user_basic
-- ----------------------------
INSERT INTO `t_user_basic` VALUES ('1', '2016-10-18 16:46:02', '2016-10-18 16:46:02', null, 'admin@qq.com', 'b5cf498b70a176efeacbc5b07d88e0da76a7f4cb', '1', 'admin', '0', null, '0:0:0:0:0:0:0:1');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户统计';

-- ----------------------------
-- Records of t_user_cube
-- ----------------------------

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
  UNIQUE KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户详细信息';

-- ----------------------------
-- Records of t_user_detail
-- ----------------------------
INSERT INTO `t_user_detail` VALUES ('1', '2016-10-18 16:46:02', '2016-10-18 16:46:02', '1', null, null, null, null, null, null, 'admin', null, null, null);

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
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户扩展信息';

-- ----------------------------
-- Records of t_user_ext
-- ----------------------------
INSERT INTO `t_user_ext` VALUES ('1', '2016-10-18 16:46:46', '2016-10-18 16:46:46', '1', '2016-10-18 16:58:35');

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
  UNIQUE KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户登陆信息';

-- ----------------------------
-- Records of t_user_login
-- ----------------------------
INSERT INTO `t_user_login` VALUES ('1', '2016-10-18 16:46:02', '2016-10-18 16:51:54', '2016-10-18 16:58:34', '0', '3', '0:0:0:0:0:0:0:1', '1', 'email', 'online', null);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户消息';

-- ----------------------------
-- Records of t_user_msg
-- ----------------------------

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
  UNIQUE KEY `idx_token` (`token`) USING BTREE,
  UNIQUE KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='登陆凭证';

-- ----------------------------
-- Records of t_user_token
-- ----------------------------
INSERT INTO `t_user_token` VALUES ('1', '2016-10-18 16:46:47', '2016-10-18 16:46:47', '1', '0:0:0:0:0:0:0:1', '4258e33ced2155af54fc0ad8575c854d', '2016-10-25 16:58:35');

