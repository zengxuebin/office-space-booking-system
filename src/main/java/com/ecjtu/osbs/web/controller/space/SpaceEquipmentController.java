package com.ecjtu.osbs.web.controller.space;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.pojo.DO.SpaceEquipmentDO;
import com.ecjtu.osbs.pojo.DTO.space.EquipmentDTO;
import com.ecjtu.osbs.pojo.DTO.space.EquipmentQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.SpaceEquipmentService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 空间设备管理控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("space/equipment")
public class SpaceEquipmentController {

    @Autowired
    private SpaceEquipmentService equipmentService;

    /**
     * 分页查询空间设备
     *
     * @param pageInfo 查询参数
     * @return 空间设备列表
     */
    @PostMapping("page")
    public ResponseResult<Page<SpaceEquipmentDO>> getSpaceEquipmentList(@RequestBody PageInfo<EquipmentQueryDTO> pageInfo) {
        EquipmentQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<SpaceEquipmentDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.like(StringUtils.isNotBlank(entity.getName()), SpaceEquipmentDO::getName, entity.getName())
                    .eq(StringUtils.isNotBlank(entity.getCategoryId()), SpaceEquipmentDO::getCategoryId, entity.getCategoryId());
        }
        Page<SpaceEquipmentDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(equipmentService.page(page, queryWrapper));
    }

    /**
     * 新增空间设备
     *
     * @param equipmentDTO 空间设备参数
     * @return 是否添加成功
     */
    @PostMapping("add")
    public ResponseResult<Void> saveSpaceEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        SpaceEquipmentDO spaceCategoryDO  = new SpaceEquipmentDO();
        BeanUtils.copyProperties(equipmentDTO, spaceCategoryDO);

        LambdaQueryWrapper<SpaceEquipmentDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SpaceEquipmentDO::getName, spaceCategoryDO.getName());
        if (equipmentService.count(queryWrapper) > 0) {
            return ResponseResult.fail(ResponseCode.EXISTS_CODE, "空间设备已存在");
        }

        LocalDateTime now = LocalDateTime.now();
        spaceCategoryDO.setCreateTime(now);
        spaceCategoryDO.setUpdateTime(now);
        equipmentService.save(spaceCategoryDO);
        return ResponseResult.success();
    }

    /**
     * 更新空间设备
     *
     * @param equipmentDTO 空间设备参数
     * @return 是否更新成功
     */
    @PostMapping("update")
    public ResponseResult<Void> updateSpaceEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        SpaceEquipmentDO spaceCategoryDO  = new SpaceEquipmentDO();
        BeanUtils.copyProperties(equipmentDTO, spaceCategoryDO);
        spaceCategoryDO.setUpdateTime(LocalDateTime.now());

        equipmentService.updateById(spaceCategoryDO);
        return ResponseResult.success();
    }

    /**
     * 删除空间设备
     *
     * @param id 空间设备id
     * @return 是否删除成功
     */
    @PostMapping("delete")
    public ResponseResult<Void> deleteSpaceEquipment(@RequestBody Integer id) {
        equipmentService.removeById(id);
        return ResponseResult.success();
    }

    /**
     * 批量删除空间设备
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ResponseResult<Void> batchDeleteSpaceEquipment(@RequestBody List<Integer> ids) {
        equipmentService.removeByIds(ids);
        return ResponseResult.success();
    }
}
