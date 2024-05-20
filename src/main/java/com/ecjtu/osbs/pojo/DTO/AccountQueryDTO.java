package com.ecjtu.osbs.pojo.DTO;

import lombok.Data;

/**
 * 账户查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/20 00:01
 */
@Data
public class AccountQueryDTO {

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 所属用户id
     */
    private String userId;

    /**
     * 账户状态
     */
    private String status;

}
