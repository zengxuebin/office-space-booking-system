/*
* 共享办公空间预约系统数据库
*/
DROP DATABASE IF EXISTS `office_space_booking`;
CREATE DATABASE `office_space_booking` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `office_space_booking`;

SET NAMES utf8mb4;

/*
 * 部门表
 */
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`        INT(11)     NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `parent_id` INT(11) COMMENT '父部门id',
    `dept_name` VARCHAR(50) NOT NULL COMMENT '部门名称',
    `leader`    VARCHAR(16) COMMENT '负责人',
    `phone`     VARCHAR(11) COMMENT '联系电话',
    `status`    CHAR(1) DEFAULT '0' COMMENT '部门状态(0-正常 1-停用)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 角色表
 */
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`        INT(11)     NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
    `role_perm` VARCHAR(50) NOT NULL COMMENT '角色标识',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 用户表
 */
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`            INT(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id，自增',
    `dept_id`       INT(11)      NOT NULL COMMENT '部门id',
    `username`      VARCHAR(50)  NOT NULL COMMENT '用户名(账号)',
    `password`      VARCHAR(255) NOT NULL COMMENT '密码',
    `role`          VARCHAR(16)  NOT NULL COMMENT '角色：0-管理员；1-普通用户',
    `email`         VARCHAR(50) COMMENT '邮箱',
    `phone_number`  VARCHAR(50) COMMENT '手机号',
    `register_time` DATETIME     NOT NULL COMMENT '注册时间',
    `update_time`   DATETIME     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 码值表
 */
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`         INT(11)     NOT NULL AUTO_INCREMENT COMMENT '码值id',
    `dict_name`  VARCHAR(50) NOT NULL COMMENT '码值名称',
    `dict_type`  VARCHAR(50) NOT NULL COMMENT '码值类型',
    `dict_value` VARCHAR(16) NOT NULL COMMENT '编码值',
    `dict_label` VARCHAR(50) COMMENT '标签值',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 办公空间类别表
 */
DROP TABLE IF EXISTS `biz_space_category`;
CREATE TABLE `biz_space_category`
(
    `id`            INT(11)     NOT NULL AUTO_INCREMENT COMMENT '类别id',
    `category_name` VARCHAR(50) NOT NULL COMMENT '类别名称：0-开发式工位 1-独立办公室 2-会议室 3-体育场馆 4-报告厅',
    `is_audit`      CHAR(2) COMMENT '是否需要审批：0-需要审批 1-免审批',
    `create_time`   DATETIME    NOT NULL COMMENT '创建时间',
    `update_time`   DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 位置表
 */
DROP TABLE IF EXISTS `biz_location`;
CREATE TABLE `biz_location`
(
    `id`              INT(11)     NOT NULL AUTO_INCREMENT COMMENT '位置id',
    `area`            VARCHAR(36) COMMENT '所属区域',
    `name`            VARCHAR(64) COMMENT '楼宇名称',
    `charge_person`   VARCHAR(64) NOT NULL COMMENT '负责人',
    `management_unit` VARCHAR(11) COMMENT '管理单位',
    `phone`           CHAR(11) COMMENT '联系方式',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 办公空间表
 */
DROP TABLE IF EXISTS `biz_space`;
CREATE TABLE `biz_space`
(
    `id`             INT(11)     NOT NULL AUTO_INCREMENT COMMENT '办公空间id',
    `category_id`    INT(11)     NOT NULL COMMENT '类别id',
    `location_id`    INT(11) COMMENT '位置id',
    `space_name`     VARCHAR(50) NOT NULL COMMENT '办公空间名称',
    `description`    VARCHAR(255) COMMENT '办公空间描述',
    `capacity`       INT(11) COMMENT '容量',
    `price_per_hour` FLOAT(2) COMMENT '每小时价格',
    `status`         VARCHAR(16) NOT NULL COMMENT '状态：0-空闲 1-已预定 2-维护中',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `biz_space_category` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`location_id`) REFERENCES `biz_location` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 空间设备表
 */
DROP TABLE IF EXISTS `biz_space_equipment`;
CREATE TABLE `biz_space_equipment`
(
    `id`          INT(11)     NOT NULL AUTO_INCREMENT COMMENT '设备id',
    `category_id` INT(11)     NOT NULL COMMENT '空间id',
    `name`        VARCHAR(64) NOT NULL COMMENT '设备名称',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `biz_space_category` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 预约信息表
 */
DROP TABLE IF EXISTS `biz_reserve`;
CREATE TABLE `biz_reserve`
(
    `id`           INT(11)     NOT NULL AUTO_INCREMENT COMMENT '预约id',
    `user_id`      INT(11)     NOT NULL COMMENT '用户id',
    `space_id`     INT(11)     NOT NULL COMMENT '办公空间id',
    `reserve_time` DATETIME COMMENT '预约时间',
    `start_time`   DATETIME COMMENT '预约开始时间',
    `end_time`     DATETIME COMMENT '预约结束时间',
    `status`       VARCHAR(16) NOT NULL COMMENT '状态：0-待付款 1-待使用 (-1)-已取消 2-待评价 3-已完成',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `biz_space_category` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`space_id`) REFERENCES `biz_space` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 预约用户表-存储邀约他人信息
 */
DROP TABLE IF EXISTS `biz_reserve_user`;
CREATE TABLE `biz_reserve_user`
(
    `id`         INT(11)    NOT NULL AUTO_INCREMENT COMMENT '预约用户id',
    `reserve_id` INT(11)    NOT NULL COMMENT '预约id',
    `user_id`    INT(11)    NOT NULL COMMENT '用户id',
    `status`     VARCHAR(2) NOT NULL COMMENT '预约状态：0-待确认 (-1)-已拒绝 1-已确认 (-2)-已取消',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`reserve_id`) REFERENCES `biz_reserve` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 订单信息表
 */
DROP TABLE IF EXISTS `biz_order`;
CREATE TABLE `biz_order`
(
    `id`         INT(11)     NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `reserve_id` INT(11)     NOT NULL COMMENT '预约id',
    `amount`     FLOAT(2)    NOT NULL COMMENT '订单金额',
    `order_time` DATETIME COMMENT '下单时间',
    `pay_time`   DATETIME COMMENT '支付时间',
    `status`     VARCHAR(16) NOT NULL COMMENT '状态：0-待支付 1-已支付 (-1)-支付失败',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`reserve_id`) REFERENCES `biz_reserve` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 评价表
 */
DROP TABLE IF EXISTS `biz_evaluate`;
CREATE TABLE `biz_evaluate`
(
    `id`            INT(11)  NOT NULL AUTO_INCREMENT COMMENT '评价id',
    `user_id`       INT(11)  NOT NULL COMMENT '用户id',
    `space_id`      INT(11)  NOT NULL COMMENT '办公空间id',
    `rating`        FLOAT(2) COMMENT '评分',
    `content`       VARCHAR(255) COMMENT '评论内容',
    `evaluate_time` DATETIME NOT NULL COMMENT '评论时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `biz_space_category` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`space_id`) REFERENCES `biz_space` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 信誉分表
 */
DROP TABLE IF EXISTS `biz_credit_score`;
CREATE TABLE `biz_credit_score`
(
    `id`               INT(11)  NOT NULL AUTO_INCREMENT COMMENT '信誉分id',
    `user_id`          INT(11)  NOT NULL COMMENT '用户id',
    `score`            INT(11)  NOT NULL COMMENT '信誉分 初始信誉分为100',
    `level`            VARCHAR(12) COMMENT '信誉分等级',
    `last_update_time` DATETIME NOT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `biz_space_category` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 信誉分变更历史表
 */
DROP TABLE IF EXISTS `biz_credit_score_change`;
CREATE TABLE `biz_credit_score_change`
(
    `id`              INT(11)  NOT NULL AUTO_INCREMENT COMMENT '变更历史id',
    `credit_score_id` INT(11)  NOT NULL COMMENT '信誉分id',
    `after_score`     INT(11)  NOT NULL COMMENT '变更后信誉分',
    `change_reason`   VARCHAR(255) COMMENT '变更原因',
    `change_time`     DATETIME NOT NULL COMMENT '变更时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`credit_score_id`) REFERENCES `biz_credit_score` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 用户收藏表
 */
DROP TABLE IF EXISTS `biz_user_favorite`;
CREATE TABLE `biz_user_favorite`
(
    `id`           INT(11)  NOT NULL AUTO_INCREMENT COMMENT '收藏id',
    `user_id`      INT(11)  NOT NULL COMMENT '用户id',
    `space_id`     INT(11)  NOT NULL COMMENT '办公空间id',
    `favoriteTime` DATETIME NOT NULL COMMENT '收藏时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `biz_space_category` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`space_id`) REFERENCES `biz_space` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 预约审核表-部分空间需审核
 */
DROP TABLE IF EXISTS `biz_audit`;
CREATE TABLE `biz_audit`
(
    `id`           INT(11)     NOT NULL AUTO_INCREMENT COMMENT '审核id',
    `reserve_id`   INT(11)     NOT NULL COMMENT '预约id',
    `status`       VARCHAR(16) NOT NULL COMMENT '审核状态：0-待审核 1-通过 (-1)-不通过',
    `comment`      VARCHAR(64) COMMENT '审核意见',
    `audit_person` VARCHAR(64) NOT NULL COMMENT '审核人员',
    `audit_time`   DATETIME    NOT NULL COMMENT '审核时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`reserve_id`) REFERENCES `biz_reserve` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 用户账户表
 */
DROP TABLE IF EXISTS `biz_account`;
CREATE TABLE `biz_account`
(
    `id`          INT(11)  NOT NULL AUTO_INCREMENT COMMENT '账户id',
    `user_id`     INT(11)  NOT NULL COMMENT '用户id',
    `balance`     FLOAT(2) NOT NULL COMMENT '账户余额',
    `status`      VARCHAR(11) COMMENT '账户状态：0-正常 1-冻结 2-注销',
    `create_time` DATETIME NOT NULL COMMENT '账户创建时间',
    `update_time` DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*
 * 交易表
 */
DROP TABLE IF EXISTS `biz_transaction`;
CREATE TABLE `biz_transaction`
(
    `id`         INT(11)  NOT NULL AUTO_INCREMENT COMMENT '交易id',
    `account_id` INT(11)  NOT NULL COMMENT '账户id',
    `type`       VARCHAR(11) COMMENT '交易类型：0-充值 1-扣款 2-退款',
    `amount`     FLOAT(2) NOT NULL COMMENT '交易金额',
    `status`     VARCHAR(11) COMMENT '交易状态：0-正常 1-失败',
    `time`       DATETIME NOT NULL COMMENT '交易时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`account_id`) REFERENCES `biz_account` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
