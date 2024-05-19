package com.ecjtu.osbs.web.controller.space;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.pojo.DO.SpaceLocationDO;
import com.ecjtu.osbs.pojo.DTO.space.LocationQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.SpaceLocationService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 位置管理控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("space/location")
public class SpaceLocationController {

    @Autowired
    private SpaceLocationService locationService;

    /**
     * 分页查询位置
     *
     * @param pageInfo 查询参数
     * @return 位置列表
     */
    @PostMapping("page")
    public ResponseResult<Page<SpaceLocationDO>> getSpaceLocationList(@RequestBody PageInfo<LocationQueryDTO> pageInfo) {
        LocationQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<SpaceLocationDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.eq(StringUtils.isNotBlank(entity.getArea()), SpaceLocationDO::getArea, entity.getArea())
                    .like(StringUtils.isNotBlank(entity.getName()), SpaceLocationDO::getName, entity.getName())
                    .like(StringUtils.isNotBlank(entity.getChargePerson()), SpaceLocationDO::getChargePerson, entity.getChargePerson());
        }
        Page<SpaceLocationDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(locationService.page(page, queryWrapper));
    }

    /**
     * 新增位置
     *
     * @param spaceLocationDO 位置参数
     * @return 是否添加成功
     */
    @PostMapping("add")
    public ResponseResult<Void> saveSpaceLocation(@RequestBody SpaceLocationDO spaceLocationDO) {
        LambdaQueryWrapper<SpaceLocationDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SpaceLocationDO::getName, spaceLocationDO.getName());
        if (locationService.count(queryWrapper) > 0) {
            return ResponseResult.fail(ResponseCode.EXISTS_CODE, "楼宇名称已存在");
        }
        locationService.save(spaceLocationDO);
        return ResponseResult.success();
    }

    /**
     * 更新位置
     *
     * @param spaceLocationDO 位置参数
     * @return 是否更新成功
     */
    @PostMapping("update")
    public ResponseResult<Void> updateSpaceLocation(@RequestBody SpaceLocationDO spaceLocationDO) {
        locationService.updateById(spaceLocationDO);
        return ResponseResult.success();
    }

    /**
     * 删除位置
     *
     * @param id 位置id
     * @return 是否删除成功
     */
    @PostMapping("delete")
    public ResponseResult<Void> deleteSpaceLocation(@RequestBody Integer id) {
        locationService.removeById(id);
        return ResponseResult.success();
    }

    /**
     * 批量删除位置
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ResponseResult<Void> batchDeleteSpaceLocation(@RequestBody List<Integer> ids) {
        locationService.removeByIds(ids);
        return ResponseResult.success();
    }

}
