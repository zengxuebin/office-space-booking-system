package com.ecjtu.osbs.pojo.DTO.system;

import lombok.Data;

/**
 * 部门查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:41
 */
@Data
public class SysDeptQuery {

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 部门状态
     */
    private String status;

}
