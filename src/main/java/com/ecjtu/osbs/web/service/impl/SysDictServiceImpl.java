package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.SysDictDO;
import com.ecjtu.osbs.web.dao.SysDictDao;
import com.ecjtu.osbs.web.service.SysDictService;
import org.springframework.stereotype.Service;

/**
 * 字典service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 02:21
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictDO> implements SysDictService {
}
