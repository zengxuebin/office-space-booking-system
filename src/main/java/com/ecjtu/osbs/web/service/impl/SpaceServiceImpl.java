package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.SpaceDO;
import com.ecjtu.osbs.web.dao.SpaceDao;
import com.ecjtu.osbs.web.service.SpaceService;
import org.springframework.stereotype.Service;

/**
 * 办公空间service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 02:16
 */
@Service
public class SpaceServiceImpl extends ServiceImpl<SpaceDao, SpaceDO> implements SpaceService {
}
