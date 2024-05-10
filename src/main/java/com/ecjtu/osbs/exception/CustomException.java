package com.ecjtu.osbs.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 *
 * @author CaoLongHui
 * @since 2024/3/8 00:05
 */
@Setter
public class CustomException extends RuntimeException {

    /**
     * 错误码
     */
    @Getter
    protected Integer code;

    /**
     * 异常信息
     */
    protected String message;

    public CustomException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public CustomException(Integer code, String message) {
        this(code, message, null);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
