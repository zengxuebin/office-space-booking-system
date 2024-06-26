package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 系统-用户实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("sys_user")
public class SysUserDO {

    /**
     * 用户编号
     */
    @TableId
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
     * 角色
     */
    private String role;

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

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
