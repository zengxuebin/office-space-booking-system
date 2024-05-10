package com.ecjtu.osbs.pojo;

import com.ecjtu.osbs.enums.ResponseCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果集
 *
 * @author CaoLongHui
 * @since 2024/3/8 00:09
 */
@Getter
@Setter
public class ResponseResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage());
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<>(ResponseCodeEnum.FAILURE.getCode(), ResponseCodeEnum.FAILURE.getMessage());
    }

    public static <T> ResponseResult<T> fail(Integer code, String message) {
        return new ResponseResult<>(code, message);
    }

    public static <T> ResponseResult<T> error() {
        return new ResponseResult<>(ResponseCodeEnum.ERROR.getCode(), ResponseCodeEnum.ERROR.getMessage());
    }

    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<>(ResponseCodeEnum.ERROR.getCode(), message);
    }

}
