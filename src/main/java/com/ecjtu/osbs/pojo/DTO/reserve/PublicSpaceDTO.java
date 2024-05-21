package com.ecjtu.osbs.pojo.DTO.reserve;

import lombok.Data;

import java.util.List;

/**
 * 公共场馆预约信息
 *
 * @author CaoLongHui
 * @since 2024/5/21 23:35
 */
@Data
public class PublicSpaceDTO {

    /**
     * 主题
     */
    private String topic;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 场馆id
     */
    private Integer spaceId;

    /**
     * 开始时间
     */
    private String reserveStartTime;

    /**
     * 结束时间
     */
    private String reserveEndTime;

    /**
     * 受邀用户id列表
     */
    private List<Integer> reserveUserIdList;
}
