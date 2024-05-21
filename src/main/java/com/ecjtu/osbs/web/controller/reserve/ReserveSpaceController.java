package com.ecjtu.osbs.web.controller.reserve;

import com.ecjtu.osbs.enums.AuditStatusEnum;
import com.ecjtu.osbs.pojo.DO.AuditDO;
import com.ecjtu.osbs.pojo.DO.ReserveDO;
import com.ecjtu.osbs.pojo.DTO.reserve.OfficeSpaceDTO;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.AuditService;
import com.ecjtu.osbs.web.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

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


    /**
     * 预约共享工位
     *
     * @param officeSpaceDTO 预约单
     * @return void
     */
    @PostMapping("office")
    public ResponseResult<Void> reserveOfficeSpace(@RequestBody OfficeSpaceDTO officeSpaceDTO) {
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
}
