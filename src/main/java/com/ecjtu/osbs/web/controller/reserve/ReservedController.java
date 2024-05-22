package com.ecjtu.osbs.web.controller.reserve;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.enums.ReservedStatusEnum;
import com.ecjtu.osbs.pojo.DO.ReserveDO;
import com.ecjtu.osbs.pojo.DO.ReserveUserDO;
import com.ecjtu.osbs.pojo.DO.SpaceDO;
import com.ecjtu.osbs.pojo.DTO.reserve.ReserveQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.VO.ReserveVO;
import com.ecjtu.osbs.util.SecurityUtil;
import com.ecjtu.osbs.web.service.AuditService;
import com.ecjtu.osbs.web.service.ReserveService;
import com.ecjtu.osbs.web.service.ReserveUserService;
import com.ecjtu.osbs.web.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 我收到的邀约控制层
 *
 * @author CaoLongHui
 * @since 2024/5/22 22:30
 */
@RestController
@RequestMapping("reserved")
public class ReservedController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private ReserveUserService reserveUserService;

    /**
     * 分页查询用户收到的邀约
     *
     * @param pageInfo 查询参数
     * @return 用户列表
     */
    @PostMapping("page")
    public ResponseResult<IPage<ReserveVO>> getSysUserList(@RequestBody PageInfo<ReserveQueryDTO> pageInfo) {
        ReserveQueryDTO entity = pageInfo.getEntity();
        Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
        LambdaQueryWrapper<ReserveUserDO> reserveUserQueryWrapper = new LambdaQueryWrapper<>();
        reserveUserQueryWrapper.eq(ReserveUserDO::getUserId, userId)
                .eq(ReserveUserDO::getStatus, entity.getStatus());
        List<Integer> reserveIdList = reserveUserService.list(reserveUserQueryWrapper).stream()
                .map(ReserveUserDO::getReserveId)
                .collect(Collectors.toList());

        if (reserveIdList.isEmpty()) {
            return ResponseResult.success(new Page<>());
        }

        LambdaQueryWrapper<ReserveDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ReserveDO::getId, reserveIdList);
        Page<ReserveDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        IPage<ReserveDO> doPage = reserveService.page(page, queryWrapper);

        // 返回展示对象
        IPage<ReserveVO> voPage = doPage.convert(reserveDO -> {
            ReserveVO vo = new ReserveVO();

            vo.setId(reserveDO.getId());
            vo.setTopic(reserveDO.getTopic());
            vo.setStartTime(reserveDO.getStartTime());
            vo.setEndTime(reserveDO.getEndTime());

            LambdaQueryWrapper<ReserveUserDO> userDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userDOLambdaQueryWrapper.eq(ReserveUserDO::getReserveId, reserveDO.getId())
                    .eq(ReserveUserDO::getUserId, userId);
            ReserveUserDO reserveUserDO = reserveUserService.getOne(userDOLambdaQueryWrapper);
            vo.setStatus(Objects.requireNonNull(ReservedStatusEnum.getEnumByCode(reserveUserDO.getStatus())).getDescription());

            LambdaQueryWrapper<SpaceDO> spaceDOWrapper = new LambdaQueryWrapper<>();
            spaceDOWrapper.eq(SpaceDO::getId, reserveDO.getSpaceId());
            SpaceDO spaceDO = spaceService.getOne(spaceDOWrapper);
            vo.setLocation(spaceDO.getSpaceName() + " " + spaceDO.getDescription());
            return vo;
        });

        return ResponseResult.success(voPage);
    }

    /**
     * 同意邀约
     *
     * @param reserveId 预约id
     * @return 同意
     */
    @PostMapping("approve/{reserveId}")
    public ResponseResult<Void> approveReserve(@PathVariable Integer reserveId) {
        Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
        LambdaQueryWrapper<ReserveUserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReserveUserDO::getUserId, userId)
                .eq(ReserveUserDO::getReserveId, reserveId);
        ReserveUserDO reserveUserDO = reserveUserService.getOne(queryWrapper);
        reserveUserDO.setStatus(ReservedStatusEnum.CONFIRMED.getCode());
        reserveUserService.updateById(reserveUserDO);
        return ResponseResult.success();
    }


    /**
     * 拒绝邀约
     * @param reserveId 邀约id
     * @return 拒绝
     */
    @PostMapping("reject/{reserveId}")
    public ResponseResult<Void> rejectReserve(@PathVariable Integer reserveId) {
        Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
        LambdaQueryWrapper<ReserveUserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReserveUserDO::getUserId, userId)
                .eq(ReserveUserDO::getReserveId, reserveId);
        ReserveUserDO reserveUserDO = reserveUserService.getOne(queryWrapper);
        reserveUserDO.setStatus(ReservedStatusEnum.REJECTED.getCode());
        reserveUserService.updateById(reserveUserDO);
        return ResponseResult.success();
    }

}
