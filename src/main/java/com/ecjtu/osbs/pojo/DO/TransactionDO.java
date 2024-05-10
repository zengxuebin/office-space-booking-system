package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 业务-交易实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_transaction")
public class TransactionDO {

    /**
     * 交易id
     */
    @TableId
    private Integer id;

    /**
     * 账户id
     */
    private Integer accountId;

    /**
     * 交易类型 0-充值 1-扣款 2-退款
     */
    private String type;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 交易状态 0-正常 1-失败
     */
    private String status;

    /**
     * 交易时间
     */
    private LocalDateTime time;
}
