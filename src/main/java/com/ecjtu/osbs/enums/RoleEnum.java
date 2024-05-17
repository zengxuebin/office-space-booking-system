package com.ecjtu.osbs.enums;

import lombok.Getter;

/**
 * @author CaoLongHui
 * @since 2024/5/18 00:00
 */
@Getter
public enum RoleEnum {


    /**
     * 管理员
     */
    ADMIN("管理员","1"),

    /**
     * 普通用户
     */
    USER("普通用户","2");

    private final String name;
    private final String value;

    RoleEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
}
