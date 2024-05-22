package com.ecjtu.osbs.web.controller.reserve;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.enums.ReservedStatusEnum;
import com.ecjtu.osbs.pojo.DO.AuditDO;
import com.ecjtu.osbs.pojo.DO.ReserveDO;
import com.ecjtu.osbs.pojo.DO.SpaceDO;
import com.ecjtu.osbs.pojo.DTO.reserve.ReserveQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.VO.ReserveVO;
import com.ecjtu.osbs.util.SecurityUtil;
import com.ecjtu.osbs.web.service.AuditService;
import com.ecjtu.osbs.web.service.ReserveService;
import com.ecjtu.osbs.web.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 我发起的预约控制层
 *
 * @author CaoLongHui
 * @since 2024/5/22 22:30
 */
@RestController
@RequestMapping("reserve/create")
public class ReserveCreateController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private SpaceService spaceService;

    /**
     * 分页查询用户发起的预约
     *
     * @param pageInfo 查询参数
     * @return 用户列表
     */
    @PostMapping("page")
    public ResponseResult<IPage<ReserveVO>> getSysUserList(@RequestBody PageInfo<ReserveQueryDTO> pageInfo) {
        ReserveQueryDTO entity = pageInfo.getEntity();

        // 查询该用户的所有预约记录
        Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
        LambdaQueryWrapper<ReserveDO> reserveQueryWrapper = new LambdaQueryWrapper<>();
        reserveQueryWrapper.eq(ReserveDO::getUserId, userId);
        List<Integer> reserveIdList = reserveService.list(reserveQueryWrapper).stream()
                .map(ReserveDO::getId)
                .collect(Collectors.toList());

        // 如果用户预约记录为空 直接返回空分页对象
        if (reserveIdList.isEmpty()) {
            return ResponseResult.success(new Page<>());
        }

        // 查询符合条件的预约id
        LambdaQueryWrapper<AuditDO> auditQueryWrapper = new LambdaQueryWrapper<>();
        auditQueryWrapper.in(AuditDO::getReserveId, reserveIdList)
                .eq(AuditDO::getStatus, entity.getStatus());
        reserveIdList = auditService.list(auditQueryWrapper).stream()
                .map(AuditDO::getReserveId)
                .collect(Collectors.toList());

        if (reserveIdList.isEmpty()) {
            return ResponseResult.success(new Page<>());
        }

        Page<ReserveDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        reserveQueryWrapper = new LambdaQueryWrapper<ReserveDO>()
                .in(ReserveDO::getId, reserveIdList);
        IPage<ReserveDO> doPage = reserveService.page(page, reserveQueryWrapper);


        // 返回展示对象
        IPage<ReserveVO> voPage = doPage.convert(reserveDO -> {
            ReserveVO vo = new ReserveVO();

            vo.setId(reserveDO.getId());
            vo.setTopic(reserveDO.getTopic());
            vo.setStartTime(reserveDO.getStartTime());
            vo.setEndTime(reserveDO.getEndTime());

            LambdaQueryWrapper<AuditDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(AuditDO::getReserveId, reserveDO.getId());
            AuditDO auditDO = auditService.getOne(queryWrapper);
            vo.setStatus(Objects.requireNonNull(ReservedStatusEnum.getEnumByCode(auditDO.getStatus())).getDescription());

            LambdaQueryWrapper<SpaceDO> spaceDOWrapper = new LambdaQueryWrapper<>();
            spaceDOWrapper.eq(SpaceDO::getId, reserveDO.getSpaceId());
            SpaceDO spaceDO = spaceService.getOne(spaceDOWrapper);
            vo.setLocation(spaceDO.getSpaceName() + " " + spaceDO.getDescription());
            return vo;
        });

        return ResponseResult.success(voPage);
    }

}
