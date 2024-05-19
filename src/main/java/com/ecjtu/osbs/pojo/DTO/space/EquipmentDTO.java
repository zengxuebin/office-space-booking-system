package com.ecjtu.osbs.pojo.DTO.space;

import lombok.Data;

/**
 * 空间设备修改删除对象
 *
 * @author CaoLongHui
 * @since 2024/5/19 18:37
 */
@Data
public class EquipmentDTO {

    /**
     * 设备id
     */
    private Integer id;

    /**
     * 类别id
     */
    private Integer categoryId;

    /**
     * 设备名称
     */
    private String name;

}
