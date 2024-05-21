package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 业务-预约实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_reserve")
public class ReserveDO {

    /**
     * 预约id
     */
    @TableId
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 办公空间id
     */
    private Integer spaceId;

    /**
     * 预约主题
     */
    private String topic;

    /**
     * 预约时间
     */
    private LocalDateTime reserveTime;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 预约状态 0-待付款 1-待使用 (-1)-已取消 2-待评价 3-已完成
     */
    private Integer status;
}
