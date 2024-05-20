package com.ecjtu.osbs.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.pojo.DO.TransactionDO;
import com.ecjtu.osbs.pojo.DTO.TransactionQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.TransactionService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 交易控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * 分页查询交易
     *
     * @param pageInfo 查询参数
     * @return 交易列表
     */
    @PostMapping("page")
    public ResponseResult<Page<TransactionDO>> getTransactionList(@RequestBody PageInfo<TransactionQueryDTO> pageInfo) {
        TransactionQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<TransactionDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.eq(StringUtils.isNotBlank(entity.getAccountId()), TransactionDO::getAccountId, entity.getAccountId())
                    .eq(StringUtils.isNotBlank(entity.getStatus()), TransactionDO::getStatus, entity.getStatus())
                    .eq(StringUtils.isNotBlank(entity.getType()), TransactionDO::getType, entity.getType());
        }
        Page<TransactionDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(transactionService.page(page, queryWrapper));
    }
}
