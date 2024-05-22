package com.ecjtu.osbs.pojo.DTO.reserve;

import lombok.Data;

/**
 * 发起的预约查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/22 12:33
 */
@Data
public class ReserveCreateQueryDTO {

    /**
     * 预约状态
     */
    private Integer status;

}
