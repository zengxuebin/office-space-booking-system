package com.ecjtu.osbs.util;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * jwt工具类
 *
 * @author CaoLongHui
 * @since 2024/3/9 11:01
 */
public class JwtUtil {

    /**
     * 密钥
     */
    private static final String SECRET = "ecjtu.osbs";

    /**
     * 默认过期时间-1天
     */
    private static final long DEFAULT_EXPIRE = 1000 * 60 * 60 * 24L;

    /**
     * jwt密钥
     */
    private static final SecretKey key = Jwts.SIG.HS256.key()
            .random(new SecureRandom(SECRET.getBytes(StandardCharsets.UTF_8)))
            .build();

    private JwtUtil() {
    }

    /**
     * 使用默认过期时间(1天) 生成一个jwt
     *
     * @param username 用户名
     * @param claims   JWT中的数据
     * @return token
     */
    public static String generateToken(String username, Map<String, Object> claims) {
        return generateToken(username, claims, DEFAULT_EXPIRE);
    }

    /**
     * 生成token
     *
     * @param username 用户名
     * @param claims   请求体数据
     * @param expire   过期时间 单位：毫秒
     * @return token
     */
    public static String generateToken(String username, Map<String, Object> claims, Long expire) {
        JwtBuilder builder = Jwts.builder();
        Date now = new Date();
        // 生成token
        builder.id(UUID.randomUUID().toString())
                // 签发者
                .issuer("CaoLongHui")
                // 数据
                .claims(claims)
                // 主题
                .subject(username)
                // 签发时间
                .issuedAt(now)
                // 过期时间
                .expiration(new Date(now.getTime() + expire))
                // 签名方式
                .signWith(key);
        return builder.compact();
    }

    /**
     * 解析token
     *
     * @param token jwt token
     * @return Claims
     */
    public static Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
