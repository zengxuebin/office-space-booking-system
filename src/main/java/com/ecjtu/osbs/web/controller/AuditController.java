package com.ecjtu.osbs.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.enums.AuditStatusEnum;
import com.ecjtu.osbs.pojo.DO.AuditDO;
import com.ecjtu.osbs.pojo.DTO.AuditQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.util.SecurityUtil;
import com.ecjtu.osbs.web.service.AuditService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 审核控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    /**
     * 分页查询审核
     *
     * @param pageInfo 查询参数
     * @return 审核列表
     */
    @PostMapping("page")
    public ResponseResult<Page<AuditDO>> getAuditList(@RequestBody PageInfo<AuditQueryDTO> pageInfo) {
        AuditQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<AuditDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.eq(StringUtils.isNotBlank(entity.getStatus()), AuditDO::getStatus, entity.getStatus());
        }
        Page<AuditDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(auditService.page(page, queryWrapper));
    }

    /**
     * 通过审核
     *
     * @param reserveId 预约单号
     * @return 是否审核成功
     */
    @PostMapping("approve")
    public ResponseResult<Void> approveAudit(@RequestBody Integer reserveId) {
        // 获取审核id
        LambdaQueryWrapper<AuditDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuditDO::getReserveId, reserveId);
        Integer id = auditService.getOne(queryWrapper).getId();

        int approvedCode = AuditStatusEnum.APPROVED.getCode();
        AuditDO auditDO = new AuditDO();
        auditDO.setId(id);
        auditDO.setStatus(approvedCode);
        auditDO.setComment(auditDO.getComment());
        auditDO.setAuditPerson(SecurityUtil.getUserDetailsInfo().getUsername());
        auditDO.setAuditTime(LocalDateTime.now());
        auditService.updateById(auditDO);
        return ResponseResult.success();
    }

    /**
     * 批量通过审核
     *
     * @param reserveIds 预约单号列表
     * @return 是否审核成功
     */
    @PostMapping("batchApprove")
    public ResponseResult<Void> batchApproveAudit(@RequestBody List<Integer> reserveIds) {
        // 获取审核id列表
        LambdaQueryWrapper<AuditDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(!reserveIds.isEmpty(), AuditDO::getReserveId, reserveIds);
        List<Integer> idList = auditService.list()
                .stream().map(AuditDO::getId)
                .collect(Collectors.toList());


        List<AuditDO> auditDOList = new ArrayList<>(idList.size());
        idList.forEach(id -> {
            AuditDO auditDO = new AuditDO();
            auditDO.setId(id);
            auditDO.setStatus(AuditStatusEnum.APPROVED.getCode());
            auditDO.setComment("同意");
            auditDO.setAuditPerson(SecurityUtil.getUserDetailsInfo().getUsername());
            auditDO.setAuditTime(LocalDateTime.now());
            auditDOList.add(auditDO);
        });
        auditService.updateBatchById(auditDOList);
        return ResponseResult.success();
    }

    /**
     * 拒绝审核
     *
     * @param reserveId 预约单号
     * @return 是否审核成功
     */
    @PostMapping("reject")
    public ResponseResult<Void> rejectAudit(@RequestBody Integer reserveId) {
        // 获取审核id
        LambdaQueryWrapper<AuditDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuditDO::getReserveId, reserveId);
        Integer id = auditService.getOne(queryWrapper).getId();

        int approvedCode = AuditStatusEnum.REJECTED.getCode();
        AuditDO auditDO = new AuditDO();
        auditDO.setId(id);
        auditDO.setStatus(approvedCode);
        auditDO.setComment("拒绝");
        auditDO.setAuditPerson(SecurityUtil.getUserDetailsInfo().getUsername());
        auditDO.setAuditTime(LocalDateTime.now());
        auditService.updateById(auditDO);


        return ResponseResult.success();
    }

    /**
     * 批量拒绝审核
     *
     * @param reserveIds 预约单号列表
     * @return 是否审核成功
     */
    @PostMapping("batchReject")
    public ResponseResult<Void> batchRejectAudit(@RequestBody List<Integer> reserveIds) {
        // 获取审核id列表
        LambdaQueryWrapper<AuditDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(!reserveIds.isEmpty(), AuditDO::getReserveId, reserveIds);
        List<Integer> idList = auditService.list()
                .stream().map(AuditDO::getId)
                .collect(Collectors.toList());

        List<AuditDO> auditDOList = new ArrayList<>(idList.size());
        idList.forEach(id -> {
            AuditDO auditDO = new AuditDO();
            auditDO.setId(id);
            auditDO.setStatus(AuditStatusEnum.REJECTED.getCode());
            auditDO.setComment("拒绝");
            auditDO.setAuditPerson(SecurityUtil.getUserDetailsInfo().getUsername());
            auditDO.setAuditTime(LocalDateTime.now());
            auditDOList.add(auditDO);
        });
        auditService.updateBatchById(auditDOList);
        return ResponseResult.success();
    }

}
