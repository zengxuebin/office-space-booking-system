package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统-部门实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("sys_dept")
public class SysDeptDO {

    /**
     * 部门id
     */
    @TableId
    private Integer id;

    /**
     * 父部门id
     */
    private Integer parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门负责人
     */
    private String leader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 部门状态 0-正常 1-停用
     */
    private String status;
}
