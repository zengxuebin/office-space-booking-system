package com.ecjtu.osbs.exception;

import com.ecjtu.osbs.pojo.ResponseResult;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 全局异常
 *
 * @author CaoLongHui
 * @since 2024/3/8 00:07
 */
@RestControllerAdvice
public class GlobalException {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> exception(Exception e) {
        LOGGER.error("全局异常信息 e=", e);
        return ResponseResult.error();
    }

    @ExceptionHandler(CustomException.class)
    public ResponseResult<Void> customExceptionHandler(CustomException e) {
        return ResponseResult.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseResult<Void> validExceptionHandler(BindException exception) {
        return ResponseResult.error(Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
    }
}
