package com.ecjtu.osbs.service;

import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.exception.CustomException;
import com.ecjtu.osbs.pojo.DO.SysUserDO;
import com.ecjtu.osbs.pojo.UserDetailsInfo;
import com.ecjtu.osbs.web.dao.SysUserDao;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户信息业务实现类
 *
 * @author CaoLongHui
 * @since 2024/3/9 12:45
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 工具用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 用户名未找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO sysUserDO = sysUserDao.selectSysUserByUsername(username);
        if (ObjectUtils.isEmpty(sysUserDO)) {
            throw new CustomException(ResponseCode.USERNAME_OR_PASSWORD_ERROR_CODE, "用户名或密码错误");
        }

        // 本系统不接入权限
        return new UserDetailsInfo(sysUserDO, null);
    }
}
