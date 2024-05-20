package com.ecjtu.osbs.web.controller;

import com.ecjtu.osbs.pojo.DO.OrderDO;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.VO.SpaceSummaryVO;
import com.ecjtu.osbs.web.service.OrderService;
import com.ecjtu.osbs.web.service.ReserveService;
import com.ecjtu.osbs.web.service.SpaceService;
import com.ecjtu.osbs.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 后台首页控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 15:18
 */
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private OrderService orderService;

    /**
     * 获取汇总对象
     *
     * @return 汇总对象
     */
    @GetMapping("summary")
    public ResponseResult<SpaceSummaryVO> getSpaceSummary() {
        SpaceSummaryVO spaceSummaryVO = new SpaceSummaryVO();

        // 查询空间数量
        long spaceCount = spaceService.count();
        spaceSummaryVO.setTotalSpaceCount(spaceCount);

        // 查询预约次数
        long reserveCount = reserveService.count();
        spaceSummaryVO.setTotalReserveCount(reserveCount);

        // 累计预约人数
        long userCount = sysUserService.count();
        spaceSummaryVO.setTotalUserCount(userCount);

        BigDecimal totalAmount = orderService.list().stream()
                .map(OrderDO::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        spaceSummaryVO.setTotalAmount(totalAmount);

        return ResponseResult.success(spaceSummaryVO);
    }
}
