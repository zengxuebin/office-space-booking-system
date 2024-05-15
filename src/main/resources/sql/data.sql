/*
 * 初始化部门表数据
 */
INSERT INTO sys_dept (id, parent_id, dept_name, leader, phone, status) VALUES (1, NULL, 'ecjtu', 'boss', '12345678901', '0');
INSERT INTO sys_dept (id, parent_id, dept_name, leader, phone, status) VALUES (2, 1, 'first', 'zhangsan', '98765432101', '0');
INSERT INTO sys_dept (id, parent_id, dept_name, leader, phone, status) VALUES (3, 1, 'second', 'lisi', '32146578902', '0');
INSERT INTO sys_dept (id, parent_id, dept_name, leader, phone, status) VALUES (4, 1, 'third', 'wangwu', '32146578902', '0');
INSERT INTO sys_dept (id, parent_id, dept_name, leader, phone, status) VALUES (5, 1, 'fourth', 'zhaoliu', '13746582930', '0');

/*
 * 初始化角色表数据
 */
INSERT INTO sys_role (id, role_name, role_perm) VALUES (1, '管理员', 'admin');
INSERT INTO sys_role (id, role_name, role_perm) VALUES (2, '普通用户', 'common');

/*
 * 初始化用户表数据
 */
INSERT INTO sys_user (id, dept_id, username, password, role, email, phone_number, register_time, update_time) VALUES (1, 1, 'admin', '$2a$10$G93U9teNkMtJdxKydeR6NenozkWb6TYZo3IesNOSPrswuKrwueXwO', '1', '1812806483@qq.com', '13979799216', '2024-03-01 09:20:50', '2024-03-30 09:20:59');
INSERT INTO sys_user (id, dept_id, username, password, role, email, phone_number, register_time, update_time) VALUES (2, 1, 'caolonghui', '$2a$10$oUVmWa6qSJCnE.iTogGY3Opd4TCLx1LnEWIl31wVtb5zbDI9SZyEe', '2', '1356639826@qq.com', '13879536851', '2024-03-01 09:22:50', '2024-03-30 09:22:58');

/*
 * 初始化码值表
 */
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (1, '用户类型', 'sys_user_type', '1', '管理员');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (2, '用户类型', 'sys_user_type', '2', '普通用户');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (3, '部门状态', 'sys_dept_status', '0', '正常');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (4, '部门状态', 'sys_dept_status', '1', '停用');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (10, '共享空间状态', 'biz_space_status', '0', '空闲');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (11, '共享空间状态', 'biz_space_status', '1', '已预订');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (12, '共享空间状态', 'biz_space_status', '2', '维护中');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (13, '预约状态', 'biz_reserve_status', '0', '待付款');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (14, '预约状态', 'biz_reserve_status', '1', '待使用');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (15, '预约状态', 'biz_reserve_status', '2', '待评价');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (16, '预约状态', 'biz_reserve_status', '3', '已完成');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (17, '预约状态', 'biz_reserve_status', '-1', '已取消');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (18, '被预约状态', 'biz_reserve_user_status', '0', '待确认');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (19, '被预约状态', 'biz_reserve_user_status', '-1', '已拒绝');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (20, '被预约状态', 'biz_reserve_user_status', '1', '已确认');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (21, '被预约状态', 'biz_reserve_user_status', '-2', '已取消');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (22, '订单状态', 'biz_order_status', '0', '待支付');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (23, '订单状态', 'biz_order_status', '1', '已支付');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (24, '订单状态', 'biz_order_status', '-1', '支付失败');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (25, '信誉分级别', 'biz_credit_level', '1', '不良');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (26, '信誉分级别', 'biz_credit_level', '2', '一般');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (27, '信誉分级别', 'biz_credit_level', '3', '不错');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (28, '信誉分级别', 'biz_credit_level', '4', '良好');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (29, '信誉分级别', 'biz_credit_level', '5', '优秀');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (30, '信誉分级别', 'biz_credit_level', '6', '卓越');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (31, '审核状态', 'biz_audit_status', '0', '待审核');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (32, '审核状态', 'biz_audit_status', '-1', '审核不通过');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (33, '审核状态', 'biz_audit_status', '1', '审核通过');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (34, '账户状态', 'biz_account_status', '0', '正常');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (35, '账户状态', 'biz_account_status', '1', '冻结');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (36, '账户状态', 'biz_account_status', '2', '注销');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (37, '交易类型', 'biz_transaction_type', '0', '充值');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (38, '交易类型', 'biz_transaction_type', '1', '扣款');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (39, '交易类型', 'biz_transaction_type', '2', '退款');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (40, '交易状态', 'biz_transaction_status', '0', '正常');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (41, '交易状态', 'biz_transaction_status', '1', '失败');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (42, '是否需要审批', 'biz_is_audit', '0', '需要审批');
INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (43, '是否需要审批', 'biz_is_audit', '1', '免审批');

/*
 * 初始化办公空间类别表
 */
INSERT INTO biz_space_category (id, category_name, is_audit) VALUES (1, '开放式工位', '1');
INSERT INTO biz_space_category (id, category_name, is_audit) VALUES (2, '独立办公室', '0');
INSERT INTO biz_space_category (id, category_name, is_audit) VALUES (3, '会议室', '0');
INSERT INTO biz_space_category (id, category_name, is_audit) VALUES (4, '体育场馆', '0');
INSERT INTO biz_space_category (id, category_name, is_audit) VALUES (5, '报告厅', '0');

/*
 * 初始化位置表
 */
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (1, '南区', '逸夫楼', '张三', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (2, '南区', '科技楼', '李四', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (3, '北区', '经管楼', '王五', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (4, '北区', '软件大楼', '赵六', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (5, '北区', '会议大楼', '钱七', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (6, '南区', '篮球场', '张三', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (7, '北区', '篮球场', '李四', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (8, '南区', '网球场', '李四', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (9, '北区', '网球场', '王五', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (10, '南区', '排球场', '王五', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (11, '北区', '排球场', '赵六', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (12, '南区', '羽毛球场', '钱七', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (13, '北区', '羽毛球场', '钱七', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (14, '南区', '学生报告厅', '孙八', '华东交通大学', '12345678901');
INSERT INTO biz_location (id, area, name, charge_person, management_unit, phone) VALUES (15, '北区', '学生报告厅', '孙八', '华东交通大学', '12345678901');

/*
 * 初始化办公空间表
 */
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (1, 1, 1, '共享空间-101', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (2, 1, 1, '共享空间-102', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (3, 1, 1, '共享空间-103', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (4, 1, 1, '共享空间-104', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (5, 1, 1, '共享空间-105', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (6, 1, 1, '共享空间-106', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (7, 1, 1, '共享空间-107', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (8, 1, 1, '共享空间-108', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (9, 1, 1, '共享空间-109', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (10, 1, 1, '共享空间-110', '逸夫楼一楼东大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (11, 1, 1, '共享空间-111', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (12, 1, 1, '共享空间-112', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (13, 1, 1, '共享空间-113', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (14, 1, 1, '共享空间-114', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (15, 1, 1, '共享空间-115', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (16, 1, 1, '共享空间-116', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (17, 1, 1, '共享空间-117', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (18, 1, 1, '共享空间-118', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (19, 1, 1, '共享空间-119', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (20, 1, 1, '共享空间-120', '逸夫楼一楼西大楼', 50, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (21, 1, 2, '共享空间-201', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (22, 1, 2, '共享空间-202', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (23, 1, 2, '共享空间-203', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (24, 1, 2, '共享空间-204', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (25, 1, 2, '共享空间-205', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (26, 1, 2, '共享空间-206', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (27, 1, 2, '共享空间-207', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (28, 1, 2, '共享空间-208', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (29, 1, 2, '共享空间-209', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (30, 1, 2, '共享空间-210', '科技楼二楼南大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (31, 1, 2, '共享空间-211', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (32, 1, 2, '共享空间-212', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (33, 1, 2, '共享空间-213', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (34, 1, 2, '共享空间-214', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (35, 1, 2, '共享空间-215', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (36, 1, 2, '共享空间-216', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (37, 1, 2, '共享空间-217', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (38, 1, 2, '共享空间-218', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (39, 1, 2, '共享空间-219', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (40, 1, 2, '共享空间-220', '科技楼二楼北大楼', 66, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (41, 1, 3, '共享空间-301', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (42, 1, 3, '共享空间-302', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (43, 1, 3, '共享空间-303', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (44, 1, 3, '共享空间-304', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (45, 1, 3, '共享空间-305', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (46, 1, 3, '共享空间-306', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (47, 1, 3, '共享空间-307', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (48, 1, 3, '共享空间-308', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (49, 1, 3, '共享空间-309', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (50, 1, 3, '共享空间-310', '经管楼三楼西侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (51, 1, 3, '共享空间-311', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (52, 1, 3, '共享空间-312', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (53, 1, 3, '共享空间-313', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (54, 1, 3, '共享空间-314', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (55, 1, 3, '共享空间-315', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (56, 1, 3, '共享空间-316', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (57, 1, 3, '共享空间-317', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (58, 1, 3, '共享空间-318', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (59, 1, 3, '共享空间-319', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (60, 1, 3, '共享空间-320', '经管楼三楼东侧', 88, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (61, 1, 4, '共享空间-401', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (62, 1, 4, '共享空间-402', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (63, 1, 4, '共享空间-403', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (64, 1, 4, '共享空间-404', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (65, 1, 4, '共享空间-405', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (66, 1, 4, '共享空间-406', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (67, 1, 4, '共享空间-407', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (68, 1, 4, '共享空间-408', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (69, 1, 4, '共享空间-409', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (70, 1, 4, '共享空间-410', '软件楼四楼南侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (71, 1, 4, '共享空间-411', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (72, 1, 4, '共享空间-412', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (73, 1, 4, '共享空间-413', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (74, 1, 4, '共享空间-414', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (75, 1, 4, '共享空间-415', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (76, 1, 4, '共享空间-416', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (77, 1, 4, '共享空间-417', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (78, 1, 4, '共享空间-418', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (79, 1, 4, '共享空间-419', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (80, 1, 4, '共享空间-420', '软件楼四楼北侧', 40, 0, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (81, 2, 4, '独立办公室-501', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (82, 2, 4, '独立办公室-502', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (83, 2, 4, '独立办公室-503', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (84, 2, 4, '独立办公室-504', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (85, 2, 4, '独立办公室-505', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (86, 2, 4, '独立办公室-506', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (87, 2, 4, '独立办公室-507', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (88, 2, 4, '独立办公室-508', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (89, 2, 4, '独立办公室-509', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (90, 2, 4, '独立办公室-510', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (91, 2, 4, '独立办公室-511', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (92, 2, 4, '独立办公室-512', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (93, 2, 4, '独立办公室-513', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (94, 2, 4, '独立办公室-514', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (95, 2, 4, '独立办公室-515', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (96, 2, 4, '独立办公室-516', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (97, 2, 4, '独立办公室-517', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (98, 2, 4, '独立办公室-518', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (99, 2, 4, '独立办公室-519', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (100, 2, 4, '独立办公室-520', '软件楼五楼', 1, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (101, 3, 5, '会议室-301', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (102, 3, 5, '会议室-302', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (103, 3, 5, '会议室-303', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (104, 3, 5, '会议室-304', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (105, 3, 5, '会议室-305', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (106, 3, 5, '会议室-306', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (107, 3, 5, '会议室-307', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (108, 3, 5, '会议室-308', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (109, 3, 5, '会议室-309', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (110, 3, 5, '会议室-310', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (111, 3, 5, '会议室-311', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (112, 3, 5, '会议室-312', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (113, 3, 5, '会议室-313', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (114, 3, 5, '会议室-314', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (115, 3, 5, '会议室-315', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (116, 3, 5, '会议室-316', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (117, 3, 5, '会议室-317', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (118, 3, 5, '会议室-318', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (119, 3, 5, '会议室-319', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (120, 3, 5, '会议室-320', '会议大楼三楼', 10, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (121, 3, 5, '会议室-401', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (122, 3, 5, '会议室-402', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (123, 3, 5, '会议室-403', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (124, 3, 5, '会议室-404', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (125, 3, 5, '会议室-405', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (126, 3, 5, '会议室-406', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (127, 3, 5, '会议室-407', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (128, 3, 5, '会议室-408', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (129, 3, 5, '会议室-409', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (130, 3, 5, '会议室-410', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (131, 3, 5, '会议室-411', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (132, 3, 5, '会议室-412', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (133, 3, 5, '会议室-413', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (134, 3, 5, '会议室-414', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (135, 3, 5, '会议室-415', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (136, 3, 5, '会议室-416', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (137, 3, 5, '会议室-417', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (138, 3, 5, '会议室-418', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (139, 3, 5, '会议室-419', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (140, 3, 5, '会议室-420', '会议大楼四楼', 30, 4, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (141, 3, 5, '会议室-501', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (142, 3, 5, '会议室-502', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (143, 3, 5, '会议室-503', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (144, 3, 5, '会议室-504', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (145, 3, 5, '会议室-505', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (146, 3, 5, '会议室-506', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (147, 3, 5, '会议室-507', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (148, 3, 5, '会议室-508', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (149, 3, 5, '会议室-509', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (150, 3, 5, '会议室-510', '会议大楼五楼', 50, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (151, 4, 6, '篮球场-101', '南区篮球场', 15, 10, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (152, 4, 6, '篮球场-102', '南区篮球场', 15, 10, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (153, 4, 6, '篮球场-103', '南区篮球场', 15, 10, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (154, 4, 6, '篮球场-104', '南区篮球场', 15, 10, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (155, 4, 7, '篮球场-105', '北区篮球场', 15, 10, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (156, 4, 7, '篮球场-106', '北区篮球场', 15, 10, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (157, 4, 7, '篮球场-107', '北区篮球场', 15, 10, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (158, 4, 7, '篮球场-108', '北区篮球场', 15, 10, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (159, 4, 8, '网球场-101', '南区网球场', 10, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (160, 4, 8, '网球场-102', '南区网球场', 10, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (161, 4, 8, '网球场-103', '南区网球场', 10, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (162, 4, 8, '网球场-104', '南区网球场', 10, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (163, 4, 9, '网球场-105', '北区网球场', 10, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (164, 4, 9, '网球场-106', '北区网球场', 10, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (165, 4, 9, '网球场-107', '北区网球场', 10, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (166, 4, 9, '网球场-108', '北区网球场', 10, 5, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (167, 4, 10, '排球场-101', '南区排球场', 20, 15, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (168, 4, 10, '排球场-102', '南区排球场', 20, 15, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (169, 4, 10, '排球场-103', '南区排球场', 20, 15, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (170, 4, 10, '排球场-104', '南区排球场', 20, 15, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (171, 4, 11, '排球场-105', '北区排球场', 20, 15, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (172, 4, 11, '排球场-106', '北区排球场', 20, 15, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (173, 4, 11, '排球场-107', '北区排球场', 20, 15, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (174, 4, 11, '排球场-108', '北区排球场', 20, 15, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (175, 4, 12, '羽毛球场-101', '南区羽毛球场', 5, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (176, 4, 12, '羽毛球场-102', '南区羽毛球场', 5, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (177, 4, 12, '羽毛球场-103', '南区羽毛球场', 5, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (178, 4, 12, '羽毛球场-104', '南区羽毛球场', 5, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (179, 4, 13, '羽毛球场-105', '北区羽毛球场', 5, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (180, 4, 13, '羽毛球场-106', '北区羽毛球场', 5, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (181, 4, 13, '羽毛球场-107', '北区羽毛球场', 5, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (182, 4, 13, '羽毛球场-108', '北区羽毛球场', 5, 3, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (183, 5, 14, '学术报告厅-101', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (184, 5, 14, '学术报告厅-102', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (185, 5, 14, '学术报告厅-103', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (186, 5, 14, '学术报告厅-104', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (187, 5, 14, '学术报告厅-105', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (188, 5, 14, '学术报告厅-106', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (189, 5, 14, '学术报告厅-107', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (190, 5, 14, '学术报告厅-108', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (191, 5, 14, '学术报告厅-109', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (192, 5, 14, '学术报告厅-110', '南区学生报告厅', 220, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (193, 5, 15, '学术报告厅-111', '北区学生报告厅', 250, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (194, 5, 15, '学术报告厅-112', '北区学生报告厅', 250, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (195, 5, 15, '学术报告厅-113', '北区学生报告厅', 250, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (196, 5, 15, '学术报告厅-114', '北区学生报告厅', 250, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (197, 5, 15, '学术报告厅-115', '北区学生报告厅', 250, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (198, 5, 15, '学术报告厅-116', '北区学生报告厅', 250, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (199, 5, 15, '学术报告厅-117', '北区学生报告厅', 250, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (200, 5, 15, '学术报告厅-118', '北区学生报告厅', 250, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (201, 5, 15, '学术报告厅-119', '北区学生报告厅', 250, 20, '0');
INSERT INTO biz_space (id, category_id, location_id, space_name, description, capacity, price_per_hour, status) VALUES (202, 5, 15, '学术报告厅-120', '北区学生报告厅', 250, 20, '0');

/*
 * 初始化空间设备表
 */
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (1, 1, '可移动座位');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (2, 1, '免费WIFI');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (3, 1, '座位插座');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (4, 2, '沙发');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (5, 2, '办公桌椅');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (6, 2, '茶具');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (7, 2, '空调');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (8, 3, '液晶屏');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (9, 3, '网络视频终端');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (10, 3, '白板');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (11, 3, '投影仪');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (12, 3, '长桌');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (13, 4, '免费WIFI');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (14, 5, '超大巨幕投影');
INSERT INTO biz_space_equipment (id, category_id, name) VALUES (15, 5, '报告会场服务');

/*
 * 初始化信誉分表
 */
INSERT INTO biz_credit_score (id, user_id, score, level, last_update_time) VALUES (1, 1, 100, '4', '2024-03-01 09:54:49');
INSERT INTO biz_credit_score (id, user_id, score, level, last_update_time) VALUES (2, 2, 100, '4', '2024-03-01 09:56:31');

/*
 * 初始化信誉分变更历史表
 */
INSERT INTO biz_credit_score_change (id, credit_score_id, after_score, change_reason, change_time) VALUES (1, 1, 100, '初始信誉分', '2024-03-01 09:58:49');
INSERT INTO biz_credit_score_change (id, credit_score_id, after_score, change_reason, change_time) VALUES (2, 2, 100, '初始信誉分', '2024-03-01 09:59:47');

/*
 * 初始化账户表
 */
INSERT INTO biz_account (id, user_id, balance, status, create_time) VALUES (1, 1, 10, '0', '2024-03-01 09:02:03');
INSERT INTO biz_account (id, user_id, balance, status, create_time) VALUES (2, 2, 10, '0', '2024-03-01 09:02:03');
