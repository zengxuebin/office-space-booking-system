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
    REJECTED("审核未通过", -1),
    NO_APPROVAL_NEEDED("无需审核", -2),
    USER_CANCELED("用户取消", -3);

    private final String description;
    private final int code;

    AuditStatusEnum(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public static AuditStatusEnum getEnumByCode(int code) {
        for (AuditStatusEnum value : AuditStatusEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

}
