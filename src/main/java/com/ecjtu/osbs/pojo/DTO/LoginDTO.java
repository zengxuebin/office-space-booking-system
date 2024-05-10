package com.ecjtu.osbs.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:27
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {

    /**
     * 登录账号
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 登录密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

}
