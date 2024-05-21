package com.ecjtu.osbs.enums;

import lombok.Getter;

/**
 * 占座率
 *
 * @author CaoLongHui
 * @since 2024/5/21 01:15
 */
@Getter
public enum OccupancyStatusEnum {

    EMPTY("空闲",0,33),

    BUSY("忙绿",33,66),

    CROWDED("拥挤",66,100);

    private final String description;
    private final int lowerBound;
    private final int upperBound;

    OccupancyStatusEnum(String description, int lowerBound, int upperBound) {
        this.description = description;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * 根据占座率计算状态
     *
     * @param occupancyRate 占座率（整数百分比）
     * @return 对应的占用状态
     */
    public static OccupancyStatusEnum calculateStatus(int occupancyRate) {
        for (OccupancyStatusEnum status : values()) {
            if (occupancyRate >= status.lowerBound && occupancyRate < status.upperBound) {
                return status;
            }
        }
        // 如果占座率超出所有范围，返回一个默认值或抛出异常
        return CROWDED;
    }
}
