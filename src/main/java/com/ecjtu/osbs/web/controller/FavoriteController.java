package com.ecjtu.osbs.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.pojo.DO.SpaceDO;
import com.ecjtu.osbs.pojo.DO.SpaceEquipmentDO;
import com.ecjtu.osbs.pojo.DO.SpaceLocationDO;
import com.ecjtu.osbs.pojo.DO.UserFavoriteDO;
import com.ecjtu.osbs.pojo.DTO.FavoriteQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.VO.OptionVO;
import com.ecjtu.osbs.pojo.VO.SpaceVO;
import com.ecjtu.osbs.util.SecurityUtil;
import com.ecjtu.osbs.web.service.SpaceEquipmentService;
import com.ecjtu.osbs.web.service.SpaceLocationService;
import com.ecjtu.osbs.web.service.SpaceService;
import com.ecjtu.osbs.web.service.UserFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收藏控制层
 *
 * @author CaoLongHui
 * @since 2024/5/22 00:46
 */
@RestController
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    private UserFavoriteService favoriteService;
    
    @Autowired
    private SpaceService spaceService;

    @Autowired
    private SpaceLocationService locationService;

    @Autowired
    private SpaceEquipmentService equipmentService;

    /**
     * 分页查询收藏列表
     *
     * @param pageInfo 查询参数
     * @return 收藏 列表
     */
    @PostMapping("page")
    public ResponseResult<IPage<SpaceVO>> getPageFavorite(@RequestBody PageInfo<FavoriteQueryDTO> pageInfo) {
        FavoriteQueryDTO entity = pageInfo.getEntity();
        Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
        LambdaQueryWrapper<UserFavoriteDO> favoriteQueryWrapper = new LambdaQueryWrapper<>();
        favoriteQueryWrapper.eq(UserFavoriteDO::getUserId, userId);
        List<Integer> favoriteSpaceIdList = favoriteService.list(favoriteQueryWrapper).stream()
                .map(UserFavoriteDO::getSpaceId)
                .collect(Collectors.toList());
        LambdaQueryWrapper<SpaceDO> queryWrapper = new LambdaQueryWrapper<>();

        // 收藏列表为空 直接返回空对象
        if (favoriteSpaceIdList.isEmpty()) {
            return ResponseResult.success(new Page<>());
        }

        queryWrapper.in(SpaceDO::getId, favoriteSpaceIdList)
                .eq(entity.getCategoryId() == 1, SpaceDO::getCategoryId, entity.getCategoryId())
                .ne(entity.getCategoryId() != 1, SpaceDO::getCategoryId, 1);
        Page<SpaceDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        IPage<SpaceDO> doPage = spaceService.page(page, queryWrapper);

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
        equipmentQueryWrapper.eq(SpaceEquipmentDO::getCategoryId, entity.getCategoryId());
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

            spaceVO.setDescription(spaceDO.getDescription());
            spaceVO.setCapacity(Long.valueOf(spaceDO.getCapacity()));
            spaceVO.setStatus(spaceDO.getStatus());
            spaceVO.setPricePerHour(spaceDO.getPricePerHour());
            spaceVO.setEquipmentList(equipmentList);

            spaceVO.setFavorite(true);
            return spaceVO;
        });
        return ResponseResult.success(voPage);
    }

    /**
     * 收藏共享空间
     *
     * @param spaceId 共享空间id
     * @return 是否收藏成功
     */
    @PostMapping("{spaceId}")
    public ResponseResult<Void> favoriteSpace(@PathVariable Integer spaceId) {
        Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
        UserFavoriteDO userFavoriteDO = new UserFavoriteDO();
        userFavoriteDO.setUserId(userId);
        userFavoriteDO.setSpaceId(spaceId);
        userFavoriteDO.setFavoriteTime(LocalDateTime.now());
        favoriteService.save(userFavoriteDO);
        return ResponseResult.success();
    }

    /**
     * 取消收藏共享空间
     *
     * @param spaceId 共享空间id
     * @return 是否收藏成功
     */
    @DeleteMapping("{spaceId}")
    public ResponseResult<Void> cancelFavoriteSpace(@PathVariable Integer spaceId) {
        Integer userId = SecurityUtil.getUserDetailsInfo().getSysUserDTO().getId();
        favoriteService.remove((Wrappers.<UserFavoriteDO>lambdaQuery()
                .eq(UserFavoriteDO::getUserId, userId)
                .eq(UserFavoriteDO::getSpaceId, spaceId)));
        return ResponseResult.success();
    }
}
