package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.ReserveDO;
import com.ecjtu.osbs.web.dao.ReserveDao;
import com.ecjtu.osbs.web.service.ReserveService;
import org.springframework.stereotype.Service;

/**
 * 预约service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 02:10
 */
@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveDao, ReserveDO> implements ReserveService {
}
