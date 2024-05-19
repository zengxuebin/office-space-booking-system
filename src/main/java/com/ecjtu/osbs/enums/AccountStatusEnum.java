package com.ecjtu.osbs.enums;

import lombok.Getter;

/**
 * 账户枚举类
 *
 * @author CaoLongHui
 * @since 2024/5/18 00:00
 */
@Getter
public enum AccountStatusEnum {


    /**
     * 正常
     */
    NORMAL("正常","0"),

    /**
     * 冻结
     */
    FROZEN("冻结","1"),

    /**
     * 注销
     */
    DELETED("注销","2");

    private final String name;
    private final String value;

    AccountStatusEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
}
