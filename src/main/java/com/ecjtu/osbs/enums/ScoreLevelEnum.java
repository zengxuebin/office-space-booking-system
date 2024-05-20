package com.ecjtu.osbs.enums;

import lombok.Getter;

/**
 * @author CaoLongHui
 * @since 2024/5/20 18:42
 */
@Getter
public enum ScoreLevelEnum {

    EXCELLENT(5),
    GOOD(4),
    AVERAGE(3),
    POOR(2),
    BAD(1);

    private final int code;

    ScoreLevelEnum(int code) {
        this.code = code;
    }
}
