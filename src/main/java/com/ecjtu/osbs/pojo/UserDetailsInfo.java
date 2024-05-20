package com.ecjtu.osbs.pojo;

import com.ecjtu.osbs.pojo.DTO.system.SysUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 登录用户信息
 *
 * @author CaoLongHui
 * @since 2024/3/9 12:34
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetailsInfo implements UserDetails {

    /**
     * 登录用户信息
     */
    private SysUserDTO sysUserDTO;

    /**
     * 用户权限信息
     */
    private List<String> permissions;

    /**
     * 获取用户权限 本系统不接入权限
     *
     * @return 用户权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取用户密码
     *
     * @return 用户密码
     */
    @Override
    public String getPassword() {
        return sysUserDTO.getPassword();
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return sysUserDTO.getUsername();
    }

    /**
     * 判断账户是否没有过期
     *
     * @return ture-无过期 false-过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断用户是否没有被锁定 用于多次登录失败
     *
     * @return true-没有锁定 false-锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用于判断用户的凭证（密码）是否没有过期
     *
     * @return true-无过期 false-过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断用户是否启用
     *
     * @return true-启用 false-禁用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
