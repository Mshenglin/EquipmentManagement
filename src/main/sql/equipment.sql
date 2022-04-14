--创建数据库
create database equipment;
-- 使用数据库
use equipment;
-- ----------------------------
-- Table structure for `d_admin`
-- ----------------------------
DROP TABLE IF EXISTS `all_user`;
CREATE TABLE `all_user` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `username` varchar(20) NOT NULL  COMMENT '用户名称',
                            `password` varchar(50) NOT NULL  COMMENT '密码',
                            `name` varchar(20) DEFAULT NULL  COMMENT '姓名',
                            `phone` bigint(20) DEFAULT NULL  COMMENT '手机号',
                            `department` varchar(20) DEFAULT NULL  COMMENT '所属部门',
                            `create_time` bigint(20)  NOT NULL COMMENT '创建时间',
                            `update_time` bigint(20)  NOT NULL COMMENT '修改时间',
                            `user_role` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '用户等级，0管理员，1普通用户',
                            PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户基础信息表';

-- 向用户表中添加数据
`all_user`
INSERT INTO `all_user` VALUES ('2', 'liyang', '123', '李阳', 15891273446,'电科',1645449938,1645449938,1);
INSERT INTO `all_user` VALUES ('3', 'wangshuai', '123', '王帅', 15891273446,'物联网',1645449938,1645449938,1);
INSERT INTO `all_user` VALUES ('4', 'zhangsan', '123', '张三', 15891273446,'通工',1645449938,1645449938,1);
-- 器材表
CREATE TABLE `equipment`(
                            `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `code` VARCHAR(20) NOT NULL  COMMENT '器材编号',
                            `name` VARCHAR(50) NOT NULL  COMMENT '名称',
                            `price` DOUBLE(20,5)  NOT NULL  COMMENT '价格',
                            `department` VARCHAR(20) DEFAULT NULL  COMMENT '所属部门',
                            `description` VARCHAR(50) DEFAULT NULL  COMMENT '描述',
                            `leader_id` INT(10) DEFAULT NULL  COMMENT '负责人id',
                            `equipment_type_id` INT(10) DEFAULT NULL  COMMENT '实验器材类型id',
                            `create_time` BIGINT(20)  NOT NULL COMMENT '创建时间',
                            `update_time` BIGINT(20)  NOT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='器材表';
INSERT INTO `equipment`VALUES('1','s-1','主机',200.0,'物联网',NULL,1,1,1645449938,1645449938);
INSERT INTO `equipment`VALUES('2','j-1','键盘',50.0,'物联网',NULL,1,1,1645449938,1645449938);
INSERT INTO `equipment`VALUES('3','x-1','显示器',50.0,'物联网',NULL,1,1,1645449938,1645449938);
INSERT INTO `equipment`VALUES('4','w-1','网线',20.0,'电科',NULL,1,1,1645449938,1645449938);
INSERT INTO `equipment`VALUES('5','d-1','51单片机',20.0,'通工',NULL,1,1,1645449938,1645449938);
-- 器材类型表
CREATE TABLE `equipment_type`(
                                 `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `name` VARCHAR(20) NOT NULL COMMENT '器材类型',
                                 PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='器材类型表';
INSERT INTO `equipment_type` VALUES('1','电脑部件');
INSERT INTO `equipment_type` VALUES('2','示波器');
INSERT INTO `equipment_type` VALUES('3','焊接部件');
INSERT INTO `equipment_type` VALUES('4','网线');
INSERT INTO `equipment_type` VALUES('5','单片机');
-- 器材操作日志表
CREATE TABLE `operational_logs` (
                                    `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `user_id` INT(10) NOT NULL  COMMENT '用户id',
                                    `equipment_id` INT(10) NOT NULL  COMMENT '实验器材id',
                                    `create_time` BIGINT(20)  NOT NULL COMMENT '操作时间',
                                    `operational_id` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '操作类型，0入库，1出库,3修改',
                                    `remark` VARCHAR(50)  DEFAULT NULL COMMENT '备注',
                                    PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';
-- 实验室器材类型操作日志表
CREATE TABLE `operational_type_logs` (
                                         `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                         `user_id` INT(10) NOT NULL  COMMENT '用户id',
                                         `equipment_type_id` INT(10) NOT NULL  COMMENT '实验器材类型id',
                                         `create_time` BIGINT(20)  NOT NULL COMMENT '操作时间',
                                         `operational_id` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '操作类型，0增加，1删除,3修改',
                                         `remark` VARCHAR(50)  DEFAULT NULL COMMENT '备注',
                                         PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='类型操作日志表';
