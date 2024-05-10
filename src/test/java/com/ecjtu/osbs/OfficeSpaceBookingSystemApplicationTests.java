package com.ecjtu.osbs;

import com.ecjtu.osbs.util.JwtUtil;
import com.ecjtu.osbs.util.SecurityUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class OfficeSpaceBookingSystemApplicationTests {

    @Test
    void contextLoads() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", "张三");
        String token = JwtUtil.generateToken("mysterious", claims);
        System.out.println(token);
        Claims claims1 = JwtUtil.getClaimsByToken(token);
        System.out.println(claims1.getSubject());
    }

    @Test
    void testEncrypt() {
        System.out.println(SecurityUtil.encryptPassword("caolonghui"));
    }

}
