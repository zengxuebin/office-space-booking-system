package com.ecjtu.osbs.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.enums.AuditStatusEnum;
import com.ecjtu.osbs.pojo.DO.AuditDO;
import com.ecjtu.osbs.pojo.DO.ReserveDO;
import com.ecjtu.osbs.pojo.DTO.ReserveQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.AuditService;
import com.ecjtu.osbs.web.service.ReserveService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 预约控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private AuditService auditService;

    /**
     * 分页查询预约待审核列表
     *
     * @param pageInfo 查询参数
     * @return 预约列表
     */
    @PostMapping("page")
    public ResponseResult<Page<ReserveDO>> getReserveList(@RequestBody PageInfo<ReserveQueryDTO> pageInfo) {
        // 查询待审核id列表
        LambdaQueryWrapper<AuditDO> auditQueryWrapper = new LambdaQueryWrapper<>();
        auditQueryWrapper.eq(AuditDO::getStatus, AuditStatusEnum.PENDING.getCode());
        List<Integer> pendingReserveId = auditService.list(auditQueryWrapper)
                .stream().map(AuditDO::getReserveId)
                .collect(Collectors.toList());

        // 列表为空直接返回
        if (pendingReserveId.isEmpty()) {
            return ResponseResult.success(new Page<>());
        }

        // 查询待审核预约空间
        ReserveQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<ReserveDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.eq(StringUtils.isNotBlank(entity.getStatus()), ReserveDO::getStatus, entity.getStatus())
                    .like(StringUtils.isNotBlank(entity.getTopic()), ReserveDO::getTopic, entity.getTopic())
                    .eq(StringUtils.isNotBlank(entity.getUserId()), ReserveDO::getUserId, entity.getUserId())
                    .in(ReserveDO::getId, pendingReserveId);
        }
        Page<ReserveDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(reserveService.page(page, queryWrapper));
    }

}
