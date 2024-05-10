package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.SpaceEquipmentDO;
import com.ecjtu.osbs.web.dao.SpaceEquipmentDao;
import com.ecjtu.osbs.web.service.SpaceEquipmentService;
import org.springframework.stereotype.Service;

/**
 * 办公空间设备service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 02:18
 */
@Service
public class SpaceEquipmentServiceImpl extends ServiceImpl<SpaceEquipmentDao, SpaceEquipmentDO>
        implements SpaceEquipmentService {
}
