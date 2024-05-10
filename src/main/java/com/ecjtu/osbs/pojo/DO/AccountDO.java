package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 业务-账户实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_account")
public class AccountDO {

    /**
     * 账户id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 账户余额
     */
    private BigDecimal balance;

    /**
     * 账户状态 0-正常 1-冻结 2-注销
     */
    private String status;

    /**
     * 账户创建时间
     */
    private LocalDateTime createTime;
}
