package com.ecjtu.osbs.pojo.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 空间汇总对象
 *
 * @author CaoLongHui
 * @since 2024/5/15 15:19
 */
@Data
public class SpaceSummaryVO {

    /**
     * 空间数量
     */
    private Long totalSpaceCount;

    /**
     * 预约次数
     */
    private Long totalReserveCount;

    /**
     * 累计预约人数
     */
    private Long totalUserCount;

    /**
     * 累计预约金额
     */
    private BigDecimal totalAmount;

    /**
     * 分类汇总列表
     */
    List<ChartVO> sortList;
}
