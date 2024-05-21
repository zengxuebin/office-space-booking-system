package com.ecjtu.osbs.enums;

import lombok.Getter;

/**
 * 空间状态枚举
 *
 * @author CaoLongHui
 * @since 2024/5/20 22:33
 */
@Getter
public enum SpaceStatusEnum {

    OPEN(0),
    MAINTENANCE(1);

    private final int code;

    SpaceStatusEnum(int code) {
        this.code = code;
    }
}
