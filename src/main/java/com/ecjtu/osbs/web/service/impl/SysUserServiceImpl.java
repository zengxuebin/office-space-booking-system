package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.SysUserDO;
import com.ecjtu.osbs.web.dao.SysUserDao;
import com.ecjtu.osbs.web.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * 用户service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 02:25
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserDO> implements SysUserService {
}
