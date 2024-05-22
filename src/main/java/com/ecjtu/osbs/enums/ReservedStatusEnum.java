package com.ecjtu.osbs.enums;

import lombok.Getter;

/**
 * 被邀约状态枚举类
 *
 * @author CaoLongHui
 * @since 2024/5/2 01:29
 */
@Getter
public enum ReservedStatusEnum {

    PENDING("待确认", 0),
    REJECTED("已拒绝", -1),
    CONFIRMED("已确认", 1),
    CANCELLED("已取消", -2);

    private final String description;
    private final int code;

    ReservedStatusEnum(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public static ReservedStatusEnum getEnumByCode(int code) {
        for (ReservedStatusEnum value : ReservedStatusEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
