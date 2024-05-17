package com.ecjtu.osbs.constant;

/**
 * 返回结果集状态码
 *
 * @author CaoLongHui
 * @since 2024/3/7 23:59
 */
public class ResponseCode {

    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 失败状态码
     */
    public static final int FAILURE_CODE = 201;

    /**
     * 系统错误状态码
     */
    public static final int ERROR_CODE = 500;

    /**
     * 用户名或密码错误状态码
     */
    public static final int USERNAME_OR_PASSWORD_ERROR_CODE = 401;

    /**
     * 用户名已存在
     */
    public static final int USERNAME_EXISTS_CODE = 400;

    /**
     * token已过期
     */
    public static final int TOKEN_EXPIRED_CODE = 402;

    /**
     * token解析失败
     */
    public static final int TOKEN_PARSING_FAILED_CODE = 403;

    /**
     * 未登录
     */
    public static final int NOT_LOGIN_CODE = 404;
}
