package com.ecjtu.osbs.util;

import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.exception.CustomException;
import com.ecjtu.osbs.pojo.UserDetailsInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全工具类
 *
 * @author CaoLongHui
 * @since 2024/3/9 16:00
 */
public class SecurityUtil {

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     源密码
     * @param encodedPassword 加密密码
     * @return true=相同 false=不相同
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户编号
     * @return true=管理员 false=非管理员
     */
    public static boolean isAdmin(Integer userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 获取Authentication
     * @return 身份认证
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取登陆用户信息
     *
     * @return 用户信息
     */
    public static UserDetailsInfo getUserDetailsInfo() {
        try {
            return (UserDetailsInfo) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new CustomException(ResponseCode.UNAUTHORIZED_CODE, "获取用户信息异常");
        }
    }
}
