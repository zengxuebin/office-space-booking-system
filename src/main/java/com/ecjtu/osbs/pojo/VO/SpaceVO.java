package com.ecjtu.osbs.pojo.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 共享空间展示对象
 *
 * @author CaoLongHui
 * @since 2024/5/20 22:27
 */
@Data
public class SpaceVO {

    /**
     * 空间id
     */
    private Integer id;

    /**
     * 位置
     */
    private String location;

    /**
     * 空间名称
     */
    private String spaceName;

    /**
     * 空间描述
     */
    private String description;

    /**
     * 容量
     */
    private Long capacity;

    /**
     * 每小时价格
     */
    private BigDecimal pricePerHour;

    /**
     * 状态
     */
    private String status;

    /**
     * 空间设备列表
     */
    List<String> equipmentList;

    /**
     * 已用容量
     */
    private Long usedCapacity;

    /**
     * 占座率
     */
    private Integer occupancyRate;

    /**
     * 占座率描述
     */
    private String occupancyDescription;

    /**
     * 是否被收藏
     */
    private boolean favorite;

}
