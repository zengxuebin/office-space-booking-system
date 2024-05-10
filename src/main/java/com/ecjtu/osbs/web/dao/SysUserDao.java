package com.ecjtu.osbs.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecjtu.osbs.pojo.DO.SysUserDO;

/**
 * 用户Dao层
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:50
 */
public interface SysUserDao extends BaseMapper<SysUserDO> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUserDO selectSysUserByUsername(String username);
}
