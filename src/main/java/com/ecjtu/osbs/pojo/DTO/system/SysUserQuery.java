package com.ecjtu.osbs.pojo.DTO.system;

import lombok.Data;

/**
 * 用户查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:41
 */
@Data
public class SysUserQuery {

    /**
     * 用户名
     */
    private String username;

    /**
     * 所属部门
     */
    private String deptId;

    /**
     * 手机号
     */
    private String phoneNumber;

}
