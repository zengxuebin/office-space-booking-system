package com.ecjtu.osbs.pojo.DTO.space;

import lombok.Data;

/**
 * 类别查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/19 17:25
 */
@Data
public class CategoryQueryDTO {

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 是否需要审核
     */
    private String isAudit;

}
