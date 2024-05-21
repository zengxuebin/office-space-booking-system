package com.ecjtu.osbs.enums;

import lombok.Getter;

/**
 * 容量枚举
 *
 * @author CaoLongHui
 * @since 2024/5/20 21:48
 */
@Getter
public enum CapacityEnum {

    LESS_THAN_10(1, 1, 10),
    BETWEEN_11_50(2, 11, 50),
    BETWEEN_51_100(3, 51, 100),
    BETWEEN_101_150(4, 101, 150);

    private final int value;
    private final Integer min;

    private final Integer max;

    CapacityEnum(int value, Integer min, Integer max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public static CapacityEnum getEnumByValue(int value) {
        for (CapacityEnum companySizeEnum : values()) {
            if (companySizeEnum.getValue() == value) {
                return companySizeEnum;
            }
        }
        return null;
    }

}
