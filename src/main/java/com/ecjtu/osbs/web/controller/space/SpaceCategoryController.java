package com.ecjtu.osbs.web.controller.space;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.pojo.DO.SpaceCategoryDO;
import com.ecjtu.osbs.pojo.DTO.space.CategoryDTO;
import com.ecjtu.osbs.pojo.DTO.space.CategoryQueryDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.SpaceCategoryService;
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
 * 空间类别管理控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("space/category")
public class SpaceCategoryController {

    @Autowired
    private SpaceCategoryService categoryService;

    /**
     * 分页查询空间类别
     *
     * @param pageInfo 查询参数
     * @return 空间类别列表
     */
    @PostMapping("page")
    public ResponseResult<Page<SpaceCategoryDO>> getSpaceCategoryList(@RequestBody PageInfo<CategoryQueryDTO> pageInfo) {
        CategoryQueryDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<SpaceCategoryDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.like(StringUtils.isNotBlank(entity.getCategoryName()), SpaceCategoryDO::getCategoryName, entity.getCategoryName())
                    .eq(StringUtils.isNotBlank(entity.getIsAudit()), SpaceCategoryDO::getIsAudit, entity.getIsAudit());
        }
        Page<SpaceCategoryDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(categoryService.page(page, queryWrapper));
    }

    /**
     * 新增空间类别
     *
     * @param categoryDTO 空间类别参数
     * @return 是否添加成功
     */
    @PostMapping("add")
    public ResponseResult<Void> saveSpaceCategory(@RequestBody CategoryDTO categoryDTO) {
        SpaceCategoryDO spaceCategoryDO  = new SpaceCategoryDO();
        BeanUtils.copyProperties(categoryDTO, spaceCategoryDO);

        LambdaQueryWrapper<SpaceCategoryDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SpaceCategoryDO::getCategoryName, spaceCategoryDO.getCategoryName());
        if (categoryService.count(queryWrapper) > 0) {
            return ResponseResult.fail(ResponseCode.EXISTS_CODE, "空间类已存在");
        }

        LocalDateTime now = LocalDateTime.now();
        spaceCategoryDO.setCreateTime(now);
        spaceCategoryDO.setUpdateTime(now);
        categoryService.save(spaceCategoryDO);
        return ResponseResult.success();
    }

    /**
     * 更新空间类别
     *
     * @param categoryDTO 空间类别参数
     * @return 是否更新成功
     */
    @PostMapping("update")
    public ResponseResult<Void> updateSpaceCategory(@RequestBody CategoryDTO categoryDTO) {
        SpaceCategoryDO spaceCategoryDO  = new SpaceCategoryDO();
        BeanUtils.copyProperties(categoryDTO, spaceCategoryDO);
        spaceCategoryDO.setUpdateTime(LocalDateTime.now());

        categoryService.updateById(spaceCategoryDO);
        return ResponseResult.success();
    }

    /**
     * 删除空间类别
     *
     * @param id 空间类别id
     * @return 是否删除成功
     */
    @PostMapping("delete")
    public ResponseResult<Void> deleteSpaceCategory(@RequestBody Integer id) {
        categoryService.removeById(id);
        return ResponseResult.success();
    }

    /**
     * 批量删除空间类别
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ResponseResult<Void> batchDeleteSpaceCategory(@RequestBody List<Integer> ids) {
        categoryService.removeByIds(ids);
        return ResponseResult.success();
    }
}
