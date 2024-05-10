package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.SysRoleDO;
import com.ecjtu.osbs.web.dao.SysRoleDao;
import com.ecjtu.osbs.web.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 02:22
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleDO> implements SysRoleService {
}
