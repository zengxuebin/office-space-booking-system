package com.ecjtu.osbs.pojo.DTO.reserve;

import lombok.Data;

/**
 * 公共场馆查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/15 21:36
 */
@Data
public class PublicSpaceQueryDTO {


    /**
     * 区域id
     */
    private String locationId;

    /**
     * 开始时间
     */
    private String reserveStartTime;

    /**
     * 结束时间
     */
    private String reserveEndTime;

    /**
     * 共享空间名称
     */
    private String spaceName;

    /**
     * 可容纳人数
     */
    private Integer capacity;

    /**
     * 空间类型
     */
    private Integer categoryId;

}
