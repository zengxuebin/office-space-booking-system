INSERT INTO sys_dict (id, dict_name, dict_type, dict_value, dict_label) VALUES (47, '审核状态', 'biz_audit_status', '-2', '无需审核');

/* 执行一下 */
ALTER TABLE user_favorite CHANGE favoriteTime favorite_time DATETIME;