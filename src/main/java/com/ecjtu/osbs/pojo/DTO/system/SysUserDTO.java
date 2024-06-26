package com.ecjtu.osbs.pojo.DTO.system;

import lombok.Data;

/**
 * 用户修改删除对象
 *
 * @author CaoLongHui
 * @since 2024/5/18 00:23
 */
@Data
public class SysUserDTO {

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 手机号
     */
    private String phoneNumber;

}
