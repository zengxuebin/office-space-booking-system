package com.ecjtu.osbs.pojo.DTO.space;

import lombok.Data;

/**
 * 位置查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/19 17:25
 */
@Data
public class LocationQueryDTO {

    /**
     * 所属区域
     */
    private String area;

    /**
     * 楼宇名称
     */
    private String name;

    /**
     * 负责人
     */
    private String chargePerson;

}
