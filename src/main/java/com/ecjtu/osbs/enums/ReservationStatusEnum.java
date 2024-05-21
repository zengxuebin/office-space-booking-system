package com.ecjtu.osbs.enums;

import lombok.Getter;

/**
 * 受邀用户预约状态
 *
 * @author CaoLongHui
 * @since 2024/5/22 00:14
 */
@Getter
public enum ReservationStatusEnum {

    PENDING(0, "待确认"),
    REJECTED(-1, "已拒绝"),
    CONFIRMED(1, "已确认"),
    CANCELLED(-2, "已取消");

    private final int code;
    private final String description;

    ReservationStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
