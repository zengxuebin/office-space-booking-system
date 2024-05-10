package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.SysDeptDO;
import com.ecjtu.osbs.web.dao.SysDeptDao;
import com.ecjtu.osbs.web.service.SysDeptService;
import org.springframework.stereotype.Service;

/**
 * 字典service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 02:21
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDeptDao, SysDeptDO> implements SysDeptService {
}
