package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统-角色实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("sys_role")
public class SysRoleDO {

    /**
     * 角色id
     */
    @TableId
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标识
     */
    private String rolePerm;
}
