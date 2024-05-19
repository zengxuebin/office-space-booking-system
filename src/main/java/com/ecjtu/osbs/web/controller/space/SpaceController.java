package com.ecjtu.osbs.web.controller.space;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.pojo.DO.SpaceDO;
import com.ecjtu.osbs.pojo.DTO.space.SpaceQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.SpaceService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 共享空间管理控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("space")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    /**
     * 分页查询共享空间
     *
     * @param pageInfo 查询参数
     * @return 共享空间列表
     */
    @PostMapping("page")
    public ResponseResult<Page<SpaceDO>> getSpaceCategoryList(@RequestBody PageInfo<SpaceQueryDTO> pageInfo) {
        SpaceQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<SpaceDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.like(StringUtils.isNotBlank(entity.getSpaceName()), SpaceDO::getSpaceName, entity.getSpaceName())
                    .eq(StringUtils.isNotBlank(entity.getCategoryId()), SpaceDO::getCategoryId, entity.getCategoryId())
                    .eq(StringUtils.isNotBlank(entity.getLocationId()), SpaceDO::getLocationId, entity.getLocationId());
        }
        Page<SpaceDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(spaceService.page(page, queryWrapper));
    }

    /**
     * 新增共享空间
     *
     * @param spaceDO 共享空间参数
     * @return 是否添加成功
     */
    @PostMapping("add")
    public ResponseResult<Void> saveSpaceCategory(@RequestBody SpaceDO spaceDO) {
        LambdaQueryWrapper<SpaceDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SpaceDO::getSpaceName, spaceDO.getSpaceName())
                .eq(SpaceDO::getLocationId, spaceDO.getLocationId())
                .eq(SpaceDO::getCategoryId, spaceDO.getCategoryId());
        if (spaceService.count(queryWrapper) > 0) {
            return ResponseResult.fail(ResponseCode.EXISTS_CODE, "共享空间已存在");
        }

        spaceService.save(spaceDO);
        return ResponseResult.success();
    }

    /**
     * 更新共享空间
     *
     * @param spaceDO 共享空间参数
     * @return 是否更新成功
     */
    @PostMapping("update")
    public ResponseResult<Void> updateSpaceCategory(@RequestBody SpaceDO spaceDO) {
        spaceService.updateById(spaceDO);
        return ResponseResult.success();
    }

    /**
     * 删除共享空间
     *
     * @param id 共享空间id
     * @return 是否删除成功
     */
    @PostMapping("delete")
    public ResponseResult<Void> deleteSpaceCategory(@RequestBody Integer id) {
        spaceService.removeById(id);
        return ResponseResult.success();
    }

    /**
     * 批量删除共享空间
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ResponseResult<Void> batchDeleteSpaceCategory(@RequestBody List<Integer> ids) {
        spaceService.removeByIds(ids);
        return ResponseResult.success();
    }
}
