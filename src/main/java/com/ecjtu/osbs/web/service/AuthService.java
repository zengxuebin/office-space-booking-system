package com.ecjtu.osbs.web.service;

/**
 * 认证业务接口层
 *
 * @author CaoLongHui
 * @since 2024/3/9 21:50
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);
}
