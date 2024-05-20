package com.ecjtu.osbs.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.pojo.DO.OrderDO;
import com.ecjtu.osbs.pojo.DTO.OrderQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.OrderService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 分页查询订单
     *
     * @param pageInfo 查询参数
     * @return 订单列表
     */
    @PostMapping("page")
    public ResponseResult<Page<OrderDO>> getOrderList(@RequestBody PageInfo<OrderQueryDTO> pageInfo) {
        OrderQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<OrderDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.eq(StringUtils.isNotBlank(entity.getStatus()), OrderDO::getStatus, entity.getStatus());
        }
        Page<OrderDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(orderService.page(page, queryWrapper));
    }

}
