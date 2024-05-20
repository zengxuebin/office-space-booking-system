package com.ecjtu.osbs.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.pojo.DO.AccountDO;
import com.ecjtu.osbs.pojo.DTO.AccountQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.AccountService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 分页查询账户
     *
     * @param pageInfo 查询参数
     * @return 账户列表
     */
    @PostMapping("page")
    public ResponseResult<Page<AccountDO>> getAccountList(@RequestBody PageInfo<AccountQueryDTO> pageInfo) {
        AccountQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.eq(StringUtils.isNotBlank(entity.getUserId()), AccountDO::getUserId, entity.getUserId())
                    .eq(StringUtils.isNotBlank(entity.getStatus()), AccountDO::getStatus, entity.getStatus())
                    .like(StringUtils.isNotBlank(entity.getAccountName()), AccountDO::getAccountName, entity.getAccountName());
        }
        Page<AccountDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(accountService.page(page, queryWrapper));
    }
}
