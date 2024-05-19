package com.ecjtu.osbs.pojo.DTO.space;

import lombok.Data;

/**
 * 设备查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/19 17:25
 */
@Data
public class EquipmentQueryDTO {

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别id
     */
    private String categoryId;

}
