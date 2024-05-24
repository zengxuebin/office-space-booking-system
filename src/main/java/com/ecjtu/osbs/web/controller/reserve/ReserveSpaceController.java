package com.ecjtu.osbs.web.controller.reserve;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.enums.AuditStatusEnum;
import com.ecjtu.osbs.enums.ReservationStatusEnum;
import com.ecjtu.osbs.pojo.DO.AuditDO;
import com.ecjtu.osbs.pojo.DO.CreditScoreDO;
import com.ecjtu.osbs.pojo.DO.ReserveDO;
import com.ecjtu.osbs.pojo.DO.ReserveUserDO;
import com.ecjtu.osbs.pojo.DTO.reserve.OfficeSpaceDTO;
import com.ecjtu.osbs.pojo.DTO.reserve.PublicSpaceDTO;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.util.SecurityUtil;
import com.ecjtu.osbs.web.service.AuditService;
import com.ecjtu.osbs.web.service.CreditScoreService;
import com.ecjtu.osbs.web.service.ReserveService;
import com.ecjtu.osbs.web.service.ReserveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 预约控制器
 *
 * @author CaoLongHui
 * @since 2024/5/16 02:42
 */
@RestController
@RequestMapping("reserve/space")
public class ReserveSpaceController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private ReserveUserService reserveUserService;

    @Autowired
    private CreditScoreService creditScoreService;

    /**
     * 预约共享工位
     *
     * @param officeSpaceDTO 预约单
     * @return void
     */
    @PostMapping("office")
    public ResponseResult<Void> reserveOfficeSpace(@RequestBody OfficeSpaceDTO officeSpaceDTO) {
        // 判断是否已经预约
        LambdaQueryWrapper<ReserveDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReserveDO::getUserId, officeSpaceDTO.getUserId())
                .eq(ReserveDO::getStartTime, officeSpaceDTO.getReserveDate().atStartOfDay())
                .eq(ReserveDO::getEndTime, officeSpaceDTO.getReserveDate().atTime(23, 59, 59));
        if (!reserveService.list(queryWrapper).isEmpty()){
            return ResponseResult.fail(ResponseCode.ERROR_CODE, "该时段该用户已经预约过共享空间");
        }

        ReserveDO reserveDO = new ReserveDO();
        reserveDO.setUserId(officeSpaceDTO.getUserId());
        reserveDO.setSpaceId(officeSpaceDTO.getSpaceId());
        reserveDO.setTopic(officeSpaceDTO.getTopic());

        // 预约区间
        LocalDate reserveDate = officeSpaceDTO.getReserveDate();
        reserveDO.setStartTime(reserveDate.atStartOfDay());
        reserveDO.setEndTime(reserveDate.atTime(23, 59, 59));

        // 添加到预约单
        LocalDateTime now = LocalDateTime.now();
        reserveDO.setReserveTime(now);
        reserveDO.setStatus(1);
        reserveService.save(reserveDO);

        AuditDO auditDO = new AuditDO();
        auditDO.setReserveId(reserveDO.getId());
        AuditStatusEnum noApprovalNeeded = AuditStatusEnum.NO_APPROVAL_NEEDED;
        auditDO.setComment(noApprovalNeeded.getDescription());
        auditDO.setStatus(noApprovalNeeded.getCode());
        auditDO.setAuditPerson("admin");
        auditDO.setAuditTime(now);
        auditService.save(auditDO);
        return ResponseResult.success();
    }


    /**
     * 预约工共场馆
     *
     * @param publicSpaceDTO 预约单
     * @return void
     */
    @PostMapping("public")
    public ResponseResult<Void> reserveOfficeSpace(@RequestBody PublicSpaceDTO publicSpaceDTO) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime reserveStartTime = LocalDateTime.parse(publicSpaceDTO.getReserveStartTime(), dateTimeFormatter);
        LocalDateTime reserveEndTime = LocalDateTime.parse(publicSpaceDTO.getReserveEndTime(), dateTimeFormatter);

        // 判断是否已经预约
        LambdaQueryWrapper<ReserveDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReserveDO::getUserId, publicSpaceDTO.getUserId())
                // 开始时间在给定范围内
                .between(ReserveDO::getStartTime, reserveStartTime, reserveEndTime)
                .or()
                // 结束时间在给定范围内
                .between(ReserveDO::getEndTime, reserveStartTime, reserveEndTime)
                // startTime可能在目标时间段内
                .lt(ReserveDO::getEndTime, reserveStartTime)
                // endTime可能在目标时间段内
                .gt(ReserveDO::getStartTime, reserveEndTime);
        if (!reserveService.list(queryWrapper).isEmpty()){
            return ResponseResult.fail(ResponseCode.ERROR_CODE, "该时段该用户已经预约过共享空间");
        }

        ReserveDO reserveDO = new ReserveDO();
        reserveDO.setUserId(publicSpaceDTO.getUserId());
        reserveDO.setSpaceId(publicSpaceDTO.getSpaceId());
        reserveDO.setTopic(publicSpaceDTO.getTopic());
        reserveDO.setStartTime(reserveStartTime);
        reserveDO.setEndTime(reserveEndTime);

        // 添加到预约单
        LocalDateTime now = LocalDateTime.now();
        reserveDO.setReserveTime(now);
        reserveDO.setStatus(1);
        reserveService.save(reserveDO);

        // 添加到审核单
        AuditDO auditDO = new AuditDO();
        auditDO.setReserveId(reserveDO.getId());
        AuditStatusEnum pending = AuditStatusEnum.PENDING;
        auditDO.setComment(pending.getDescription());
        auditDO.setStatus(pending.getCode());
        auditService.save(auditDO);

        // 受邀用户
        List<Integer> reserveUserIdList = publicSpaceDTO.getReserveUserIdList();
        if (!reserveUserIdList.isEmpty()) {
            List<ReserveUserDO> reserveUserDOList = new ArrayList<>();
            reserveUserIdList.forEach(userId -> {
                ReserveUserDO reserveUserDO = new ReserveUserDO();
                reserveUserDO.setReserveId(reserveDO.getId());
                reserveUserDO.setUserId(userId);
                reserveUserDO.setStatus(ReservationStatusEnum.PENDING.getCode());
                reserveUserDOList.add(reserveUserDO);
            });
            reserveUserService.saveBatch(reserveUserDOList);
        }
        return ResponseResult.success();
    }

    /**
     * 取消预约
     *
     * @param reserveId 预约id
     * @return 取消预约
     */
    @PostMapping("cancel/{reserveId}")
    public ResponseResult<Void> cancelReserve(@PathVariable Integer reserveId) {
        AuditStatusEnum userCanceled = AuditStatusEnum.USER_CANCELED;
        LambdaQueryWrapper<AuditDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuditDO::getReserveId, reserveId);
        AuditDO auditDO = auditService.getOne(queryWrapper);
        auditDO.setStatus(userCanceled.getCode());
        auditDO.setComment(userCanceled.getDescription());
        auditService.updateById(auditDO);

        // 受邀用户也被取消
        LambdaQueryWrapper<ReserveUserDO> reserveUserQueryWrapper = new LambdaQueryWrapper<>();
        reserveUserQueryWrapper.eq(ReserveUserDO::getReserveId, reserveId);
        List<ReserveUserDO> reserveUserList = reserveUserService.list(reserveUserQueryWrapper);
        if (!reserveUserList.isEmpty()) {
            reserveUserList.forEach(reserveUserDO -> {
                reserveUserDO.setStatus(ReservationStatusEnum.CANCELLED.getCode());
            });
            reserveUserService.updateBatchById(reserveUserList);
        }

        // 扣除信誉分
        Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
        LambdaQueryWrapper<CreditScoreDO> creditScoreQueryWrapper = new LambdaQueryWrapper<>();
        creditScoreQueryWrapper.eq(CreditScoreDO::getUserId, userId);
        CreditScoreDO creditScoreDO = creditScoreService.getOne(creditScoreQueryWrapper);
        creditScoreDO.setScore(Math.max(creditScoreDO.getScore() - 5, 0));
        creditScoreDO.setLastUpdateTime(LocalDateTime.now());
        creditScoreService.updateById(creditScoreDO);
        return ResponseResult.success();
    }
}
