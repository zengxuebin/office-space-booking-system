package com.ecjtu.osbs.web.controller.reserve;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.enums.CapacityEnum;
import com.ecjtu.osbs.enums.SpaceStatusEnum;
import com.ecjtu.osbs.pojo.DO.ReserveDO;
import com.ecjtu.osbs.pojo.DO.SpaceDO;
import com.ecjtu.osbs.pojo.DO.SpaceEquipmentDO;
import com.ecjtu.osbs.pojo.DO.SpaceLocationDO;
import com.ecjtu.osbs.pojo.DTO.reserve.PublicSpaceQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.VO.OptionVO;
import com.ecjtu.osbs.pojo.VO.SpaceVO;
import com.ecjtu.osbs.web.service.ReserveService;
import com.ecjtu.osbs.web.service.SpaceEquipmentService;
import com.ecjtu.osbs.web.service.SpaceLocationService;
import com.ecjtu.osbs.web.service.SpaceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 共享空间预约控制器
 *
 * @author CaoLongHui
 * @since 2024/5/15 21:33
 */
@RestController
@RequestMapping("space/public")
public class PublicSpaceController {

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private SpaceLocationService locationService;

    @Autowired
    private SpaceEquipmentService equipmentService;

    /**
     * 分页查询可预约共享空间
     *
     * @param pageInfo 查询参数
     * @return 空间设备列表
     */
    @PostMapping("page")
    public ResponseResult<IPage<SpaceVO>> getPublicSpaceList(@RequestBody PageInfo<PublicSpaceQueryDTO> pageInfo) {
        PublicSpaceQueryDTO entity = pageInfo.getEntity();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime reserveStartTime = LocalDateTime.parse(entity.getReserveStartTime(), dateTimeFormatter);
        LocalDateTime reserveEndTime = LocalDateTime.parse(entity.getReserveEndTime(), dateTimeFormatter);

        CapacityEnum capacityEnum = CapacityEnum.getEnumByValue(entity.getCapacity());

        // 共享空间id列表
        LambdaQueryWrapper<SpaceDO> spaceQueryWrapper = new LambdaQueryWrapper<>();
        // 共享空间
        assert capacityEnum != null;
        spaceQueryWrapper.gt(SpaceDO::getCapacity, capacityEnum.getMin())
                .lt(SpaceDO::getCapacity, capacityEnum.getMax())
                .eq(SpaceDO::getCategoryId, entity.getCategoryId())
                // 开放中
                .eq(SpaceDO::getStatus, SpaceStatusEnum.OPEN.getCode())
                .eq(StringUtils.isNotBlank(entity.getLocationId()), SpaceDO::getLocationId, entity.getLocationId())
                .like(StringUtils.isNotBlank(entity.getSpaceName()), SpaceDO::getSpaceName, entity.getSpaceName());
        List<Integer> spaceIdList = spaceService.list(spaceQueryWrapper)
                .stream().map(SpaceDO::getId)
                .collect(Collectors.toList());

        // 无符合条件的共享空间 直接返回空
        if (spaceIdList.isEmpty()) {
            return ResponseResult.success(new Page<>());
        }

        // 查询已预约空间id列表
        LambdaQueryWrapper<ReserveDO> reserveQueryWrapper = new LambdaQueryWrapper<>();
        reserveQueryWrapper
                .in(ReserveDO::getSpaceId, spaceIdList)
                .gt(ReserveDO::getStartTime, reserveStartTime)
                .lt(ReserveDO::getEndTime, reserveEndTime);
        List<Integer> reservedSpaceIdList = reserveService.list(reserveQueryWrapper)
                .stream().map(ReserveDO::getId)
                .collect(Collectors.toList());

        // 过滤已预约id 剩下为可预约id
        List<Integer> avaivableSpaceIdList = spaceIdList.stream()
                .filter(spaceId -> !reservedSpaceIdList.contains(spaceId))
                .collect(Collectors.toList());

        // 无符合条件的可预约公共场馆
        if (spaceIdList.isEmpty()) {
            return ResponseResult.success(new Page<>());
        }

        // 查询可预约公共场馆
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

        // 返回展示对象
        IPage<SpaceVO> voPage = doPage.convert(spaceDO -> {
            SpaceVO spaceVO = new SpaceVO();
            spaceVO.setId(spaceDO.getId());
            spaceVO.setSpaceName(spaceDO.getSpaceName());
            optionVOList.stream()
                    .filter(optionVO -> optionVO.getValue().equals(spaceDO.getLocationId()))
                    .findFirst()
                    .ifPresent(optionVO -> spaceVO.setLocation(optionVO.getLabel()));
            spaceVO.setDescription(spaceDO.getDescription());
            spaceVO.setCapacity(Long.valueOf(spaceDO.getCapacity()));
            spaceVO.setStatus(spaceDO.getStatus());
            spaceVO.setPricePerHour(spaceDO.getPricePerHour());

            // 共享空间设备
            LambdaQueryWrapper<SpaceEquipmentDO> equipmentQueryWrapper = new LambdaQueryWrapper<>();
            equipmentQueryWrapper.eq(SpaceEquipmentDO::getCategoryId, entity.getCategoryId());
            List<String> equipmentList = equipmentService.list(equipmentQueryWrapper).stream()
                    .map(SpaceEquipmentDO::getName)
                    .collect(Collectors.toList());
            spaceVO.setEquipmentList(equipmentList);
            return spaceVO;
        });
        return ResponseResult.success(voPage);
    }
}
