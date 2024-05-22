package com.ecjtu.osbs.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.pojo.DO.CreditScoreDO;
import com.ecjtu.osbs.pojo.DTO.CreditScoreQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.util.SecurityUtil;
import com.ecjtu.osbs.web.service.CreditScoreService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 信誉分控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("creditScore")
public class CreditScoreController {

    @Autowired
    private CreditScoreService creditScoreService;

    /**
     * 我的信誉分
     * @return 信誉分
     */
    @GetMapping("myCredit")
    public ResponseResult<CreditScoreDO> getMyCreditScore() {
        Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
        LambdaQueryWrapper<CreditScoreDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CreditScoreDO::getUserId, userId);
        CreditScoreDO creditScoreDO = creditScoreService.getOne(queryWrapper);
        return ResponseResult.success(creditScoreDO);
    }

    /**
     * 分页查询信誉分
     *
     * @param pageInfo 查询参数
     * @return 信誉分列表
     */
    @PostMapping("page")
    public ResponseResult<Page<CreditScoreDO>> getCreditScoreList(@RequestBody PageInfo<CreditScoreQueryDTO> pageInfo) {
        CreditScoreQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<CreditScoreDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.eq(StringUtils.isNotBlank(entity.getUserId()), CreditScoreDO::getUserId, entity.getUserId())
                    .eq(StringUtils.isNotBlank(entity.getLevel()), CreditScoreDO::getLevel, entity.getLevel());
        }
        Page<CreditScoreDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(creditScoreService.page(page, queryWrapper));
    }
}
