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
                            `student_id` varchar(20) DEFAULT NULL  COMMENT '学号',
                            `department_id` varchar(20) DEFAULT NULL  COMMENT '所属部门id',
                            `create_time` int(11) unsigned NOT NULL COMMENT '创建时间',
                            `update_time` int(11) unsigned NOT NULL COMMENT '修改时间',
                            `user_role` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '用户等级，0管理员，1普通用户',
                            PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户基础信息表';

-- 向用户表中添加数据
INSERT INTO `all_user` VALUES ('1', 'mashenglin', '123', '马生林', 15891273446, 03472056,1,1645449938,1645449938,0);
INSERT INTO `all_user` VALUES ('2', 'liyang', '123', '李阳', 15891273446, 03472071,2,1645449938,1645449938,1);
INSERT INTO `all_user` VALUES ('3', 'wangshuai', '123', '王帅', 15891273446, 03472071,2,1645449938,1645449938,1);
INSERT INTO `all_user` VALUES ('4', 'zhangsan', '123', '张三', 15891273446, 03472071,2,1645449938,1645449938,1);