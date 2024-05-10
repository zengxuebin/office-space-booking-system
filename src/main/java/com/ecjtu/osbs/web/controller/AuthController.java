package com.ecjtu.osbs.web.controller;

import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.DTO.LoginDTO;
import com.ecjtu.osbs.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 认证控制层
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:22
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseResult<String> login(@Valid @RequestBody LoginDTO loginDTO){
        String token = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return ResponseResult.success(token);
    }

    @GetMapping("test")
    public ResponseResult<String> test() {
        return ResponseResult.success("test");
    }
}
