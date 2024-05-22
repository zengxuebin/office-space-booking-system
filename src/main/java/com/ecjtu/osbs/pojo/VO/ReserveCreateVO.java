package com.ecjtu.osbs.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 我发起的邀约展示对象
 *
 * @author CaoLongHui
 * @since 2024/5/22 22:52
 */
@Data
public class ReserveCreateVO {

    /**
     * 标识
     */
    private Integer id;

    /**
     * 预约主题
     */
    private String topic;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 预约状态
     */
    private String status;

    /**
     * 地点
     */
    private String location;
}
