package com.ecjtu.osbs.web.controller.reserve;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.constant.SpaceCategory;
import com.ecjtu.osbs.enums.CapacityEnum;
import com.ecjtu.osbs.enums.OccupancyStatusEnum;
import com.ecjtu.osbs.enums.SpaceStatusEnum;
import com.ecjtu.osbs.pojo.DO.*;
import com.ecjtu.osbs.pojo.DTO.reserve.OfficeSpaceQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.VO.OptionVO;
import com.ecjtu.osbs.pojo.VO.SpaceVO;
import com.ecjtu.osbs.util.SecurityUtil;
import com.ecjtu.osbs.web.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 共享空间预约控制器
 *
 * @author CaoLongHui
 * @since 2024/5/15 21:33
 */
@RestController
@RequestMapping("space/office")
public class OfficeSpaceController {

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private SpaceLocationService locationService;

    @Autowired
    private SpaceEquipmentService equipmentService;

    @Autowired
    private UserFavoriteService favoriteService;

    /**
     * 分页查询可预约办公空间
     *
     * @param pageInfo 查询参数
     * @return 空间设备列表
     */
    @PostMapping("page")
    public ResponseResult<IPage<SpaceVO>> getOfficeSpaceList(@RequestBody PageInfo<OfficeSpaceQueryDTO> pageInfo) {
        OfficeSpaceQueryDTO entity = pageInfo.getEntity();

        Integer officeSpaceCategory = SpaceCategory.OPEN;

        CapacityEnum capacityEnum = CapacityEnum.getEnumByValue(entity.getCapacity());

        // 查询办公空间id列表
        LambdaQueryWrapper<SpaceDO> spaceQueryWrapper = new LambdaQueryWrapper<>();
        // 办公空间
        assert capacityEnum != null;
        spaceQueryWrapper.eq(SpaceDO::getCategoryId, officeSpaceCategory)
                .gt(SpaceDO::getCapacity, capacityEnum.getMin())
                .lt(SpaceDO::getCapacity, capacityEnum.getMax())
                // 开放中
                .eq(SpaceDO::getStatus, SpaceStatusEnum.OPEN.getCode())
                .eq(StringUtils.isNotBlank(entity.getLocationId()), SpaceDO::getLocationId, entity.getLocationId())
                .like(StringUtils.isNotBlank(entity.getSpaceName()), SpaceDO::getSpaceName, entity.getSpaceName());
        List<Integer> spaceIdList = spaceService.list(spaceQueryWrapper)
                .stream().map(SpaceDO::getId)
                .collect(Collectors.toList());

        // 将预约日期转化为时间段
        LocalDate reserveDate = entity.getReserveDate();
        LocalDateTime startReserveDate = reserveDate.atStartOfDay();
        LocalDateTime endReserveDate = reserveDate.atTime(23, 59, 59);

        // 无符合条件的工位空间
        if (spaceIdList.isEmpty()) {
            return ResponseResult.success(new Page<>());
        }

        // 查询已预约空间id列表
        LambdaQueryWrapper<ReserveDO> reserveQueryWrapper = new LambdaQueryWrapper<>();
        reserveQueryWrapper
                .in(ReserveDO::getSpaceId, spaceIdList)
                .eq(ReserveDO::getStartTime, startReserveDate)
                .eq(ReserveDO::getEndTime, endReserveDate);
        List<Integer> reservedSpaceIdList = reserveService.list(reserveQueryWrapper).stream()
                .map(ReserveDO::getSpaceId)
                .collect(Collectors.toList());

        // 过滤可用容量为空的空间
        List<Integer> avaivableSpaceIdList = spaceIdList.stream()
                .filter(spaceId -> {
                    long count = reservedSpaceIdList.stream()
                            .filter(spaceId::equals)
                            .count();
                    SpaceDO spaceDo = spaceService.getById(spaceId);
                    return count < spaceDo.getCapacity();
                })
                .collect(Collectors.toList());

        // 无符合条件的可预约工位空间
        if (spaceIdList.isEmpty()) {
            return ResponseResult.success(new Page<>());
        }

        // 查询可预约工位
        LambdaQueryWrapper<SpaceDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SpaceDO::getId, avaivableSpaceIdList);
        Page<SpaceDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());

        // 转化为展示对象
        Page<SpaceDO> doPage = spaceService.page(page, queryWrapper);

        // 位置列表
        List<SpaceLocationDO> list = locationService.list();
        List<OptionVO> optionVOList = list.stream()
                .map(locationDO -> {
                    OptionVO optionVO = new OptionVO();
                    optionVO.setLabel(locationDO.getArea() + locationDO.getName());
                    optionVO.setValue(locationDO.getId().toString());
                    return optionVO;
                }).collect(Collectors.toList());

        // 共享空间设备
        LambdaQueryWrapper<SpaceEquipmentDO> equipmentQueryWrapper = new LambdaQueryWrapper<>();
        equipmentQueryWrapper.eq(SpaceEquipmentDO::getCategoryId, officeSpaceCategory);
        List<String> equipmentList = equipmentService.list(equipmentQueryWrapper).stream()
                .map(SpaceEquipmentDO::getName)
                .collect(Collectors.toList());

        // 返回展示对象
        IPage<SpaceVO> voPage = doPage.convert(spaceDO -> {
            SpaceVO spaceVO = new SpaceVO();
            spaceVO.setId(spaceDO.getId());
            spaceVO.setSpaceName(spaceDO.getSpaceName());
            optionVOList.stream()
                    .filter(optionVO -> optionVO.getValue().equals(spaceDO.getLocationId()))
                    .findFirst()
                    .ifPresent(optionVO -> spaceVO.setLocation(optionVO.getLabel()));

            // 计算占座率
            Integer capacity = spaceDO.getCapacity();
            long reserveCount = reservedSpaceIdList.stream()
                    .filter(spaceId -> spaceId.equals(spaceDO.getId()))
                    .count();
            double occupancyRate = (double) reserveCount / capacity * 100;
            spaceVO.setOccupancyRate((int) Math.round(occupancyRate));
            spaceVO.setOccupancyDescription(OccupancyStatusEnum.calculateStatus(spaceVO.getOccupancyRate()).getDescription());

            spaceVO.setUsedCapacity(reserveCount);
            spaceVO.setDescription(spaceDO.getDescription());
            spaceVO.setCapacity(Long.valueOf(capacity));
            spaceVO.setStatus(spaceDO.getStatus());
            spaceVO.setPricePerHour(spaceDO.getPricePerHour());
            spaceVO.setEquipmentList(equipmentList);

            // 是否被收藏
            Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
            LambdaQueryWrapper<UserFavoriteDO> favoriteQueryWrapper = new LambdaQueryWrapper<>();
            favoriteQueryWrapper.eq(UserFavoriteDO::getSpaceId, spaceDO.getId())
                    .eq(UserFavoriteDO::getUserId, userId);
            spaceVO.setFavorite(favoriteService.count(favoriteQueryWrapper) > 0);
            return spaceVO;
        });
        return ResponseResult.success(voPage);
    }
}
