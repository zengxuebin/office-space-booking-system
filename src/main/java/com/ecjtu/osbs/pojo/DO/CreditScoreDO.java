package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 业务-信誉分实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_credit_score")
public class CreditScoreDO {

    /**
     * 信誉分id
     */
    @TableId
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 信誉分-初始信誉分为100分
     */
    private Integer score;

    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;
}
