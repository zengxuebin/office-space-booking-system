package com.ecjtu.osbs.pojo.DTO;

import lombok.Data;

/**
 * 信誉分查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/20 00:01
 */
@Data
public class CreditScoreQueryDTO {

    /**
     * 所属用户id
     */
    private String userId;

    /**
     * 信誉分等级
     */
    private String level;

}
