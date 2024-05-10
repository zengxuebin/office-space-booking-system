package com.ecjtu.osbs.web.service.impl;

import com.ecjtu.osbs.constant.RedisKey;
import com.ecjtu.osbs.pojo.DO.SysUserDO;
import com.ecjtu.osbs.pojo.UserDetailsInfo;
import com.ecjtu.osbs.util.JwtUtil;
import com.ecjtu.osbs.util.RedisUtil;
import com.ecjtu.osbs.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 认证业务接口实现层
 *
 * @author CaoLongHui
 * @since 2024/3/9 21:50
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @Override
    @Transactional(readOnly = true)
    public String login(String username, String password) {
        // 验证用户密码
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        // 该方法会调用loadUserByUsername方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 将用户信息存入redis
        UserDetailsInfo userDetailsInfo = (UserDetailsInfo) authenticate.getPrincipal();
        String userKey = RedisKey.LOGIN_USER + username;
        redisUtil.setCacheObject(userKey, userDetailsInfo, 120L, TimeUnit.MINUTES);

        // 生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetailsInfo.getSysUserDO().getId());
        return JwtUtil.generateToken(username, claims);
    }
}
