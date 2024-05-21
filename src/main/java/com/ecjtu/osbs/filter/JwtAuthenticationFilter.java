package com.ecjtu.osbs.filter;

import com.ecjtu.osbs.constant.LoginConstants;
import com.ecjtu.osbs.constant.RedisKey;
import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.exception.CustomException;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.UserDetailsInfo;
import com.ecjtu.osbs.util.JwtUtil;
import com.ecjtu.osbs.util.RedisUtil;
import com.ecjtu.osbs.util.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 登录认证过滤器
 *
 * @author CaoLongHui
 * @since 2024/3/9 14:12
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader(LoginConstants.AUTH);
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 判断token是否合法
        if (!token.startsWith(LoginConstants.BEARER)) {
            throw new CustomException(ResponseCode.TOKEN_PARSING_FAILED_CODE, "token错误");
        }
        token = token.substring(token.indexOf(" ") + 1);

        // 解析token
        Claims claims;
        try {
            claims = JwtUtil.getClaimsByToken(token);
        } catch (Exception e) {
            LOGGER.error("token解析异常", e);
            String jsonString = new ObjectMapper().writeValueAsString(ResponseResult.fail(
                    ResponseCode.TOKEN_PARSING_FAILED_CODE, "token非法或已失效，请重新登录"));
            WebUtil.renderString(response,jsonString);
            return;
        }

        String username = claims.getSubject();
        // 从redis获取用户信息
        String redisKey = RedisKey.LOGIN_USER + username;
        UserDetailsInfo userDetailsInfo = redisUtil.getCacheObject(redisKey);
        if (ObjectUtils.isEmpty(userDetailsInfo)) {
            filterChain.doFilter(request, response);
            throw new CustomException(ResponseCode.NOT_LOGIN_CODE, "用户未登录");
        }
        // 重置redis缓存时间
        redisUtil.expire(redisKey, 30, TimeUnit.HOURS);

        // 封装Authentication
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetailsInfo, null, userDetailsInfo.getAuthorities());

        // 存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
