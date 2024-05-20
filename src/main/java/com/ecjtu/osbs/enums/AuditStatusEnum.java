package com.ecjtu.osbs.enums;

import lombok.Getter;

/**
 * 审核状态枚举类
 *
 * @author CaoLongHui
 * @since 2024/5/2 01:29
 */
@Getter
public enum AuditStatusEnum {

    PENDING("待审核", 0),
    APPROVED("审核通过", 1),
    REJECTED("审核不过", -1);

    private final String description;
    private final int code;

    AuditStatusEnum(String description, int code) {
        this.description = description;
        this.code = code;
    }

}
