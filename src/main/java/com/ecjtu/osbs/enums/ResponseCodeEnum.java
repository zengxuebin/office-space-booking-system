package com.ecjtu.osbs.enums;

import com.ecjtu.osbs.constant.ResponseCode;

/**
 * 返回结果集状态码
 *
 * @author CaoLongHui
 * @since 2024/3/7 23:56
 */
public enum ResponseCodeEnum {

    /**
     * 操作成功
     */
    SUCCESS(ResponseCode.SUCCESS_CODE, "操作成功！"),
    /**
     * 操作失败
     */
    FAILURE(ResponseCode.FAILURE_CODE, "操作失败！"),

    /**
     * 系统错误码：5xx
     */
    ERROR(ResponseCode.ERROR_CODE, "系统异常，请稍后重试！"),

    /**
     * 权限错误码：4xx
     */
    USERNAME_OR_PASSWORD_ERROR(ResponseCode.USERNAME_OR_PASSWORD_ERROR_CODE, "用户名或密码错误");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
