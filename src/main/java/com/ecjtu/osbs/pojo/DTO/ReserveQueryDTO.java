package com.ecjtu.osbs.pojo.DTO;

import lombok.Data;

/**
 * 预约查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/20 00:01
 */
@Data
public class ReserveQueryDTO {

    /**
     * 预约主题
     */
    private String topic;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 预约状态
     */
    private String status;

}
