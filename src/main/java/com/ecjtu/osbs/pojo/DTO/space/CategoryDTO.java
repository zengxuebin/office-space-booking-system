package com.ecjtu.osbs.pojo.DTO.space;

import lombok.Data;

/**
 * 空间类别修改删除对象
 *
 * @author CaoLongHui
 * @since 2024/5/19 18:37
 */
@Data
public class CategoryDTO {

    /**
     * 类别id
     */
    private Integer id;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 是否需要审核
     */
    private String isAudit;
}
