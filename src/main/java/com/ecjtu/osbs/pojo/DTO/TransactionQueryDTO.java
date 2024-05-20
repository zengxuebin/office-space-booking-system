package com.ecjtu.osbs.pojo.DTO;

import lombok.Data;

/**
 * 交易查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/20 00:01
 */
@Data
public class TransactionQueryDTO {

    /**
     * 账户id
     */
    private String accountId;

    /**
     * 交易类型
     */
    private String type;

    /**
     * 交易状态
     */
    private String status;

}
