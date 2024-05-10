package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 业务-信誉分变更历史实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_credit_score_change")
public class CreditScoreChangeDO {

    /**
     * 变更历史id
     */
    @TableId
    private Integer id;

    /**
     * 信誉分id
     */
    private Integer creditScoreId;

    /**
     * 变更后信誉分
     */
    private Integer afterScore;

    /**
     * 变更原因
     */
    private String changeReason;

    /**
     * 变更时间
     */
    private LocalDateTime changeTime;

}
