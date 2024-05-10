package com.ecjtu.osbs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecjtu.osbs.pojo.DO.OrderDO;
import com.ecjtu.osbs.web.dao.OrderDao;
import com.ecjtu.osbs.web.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * 用户订单service接口实现层
 *
 * @author CaoLongHui
 * @since 2024/4/3 02:08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderDO> implements OrderService {
}
