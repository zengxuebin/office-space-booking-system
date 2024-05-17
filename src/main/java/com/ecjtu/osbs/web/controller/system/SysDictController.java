package com.ecjtu.osbs.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.pojo.DO.SysDictDO;
import com.ecjtu.osbs.pojo.DTO.system.SysDictQuery;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.VO.OptionVO;
import com.ecjtu.osbs.web.service.SysDictService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典管理控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("system/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * 分页查询字典
     *
     * @param pageInfo 查询参数
     * @return 字典列表
     */
    @PostMapping("page")
    public ResponseResult<Page<SysDictDO>> getSysDictList(@RequestBody PageInfo<SysDictQuery> pageInfo) {
        SysDictQuery entity = pageInfo.getEntity();
        LambdaQueryWrapper<SysDictDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.like(StringUtils.isNotBlank(entity.getDictName()), SysDictDO::getDictName, entity.getDictName())
                    .like(StringUtils.isNotBlank(entity.getDictType()), SysDictDO::getDictType, entity.getDictType())
                    .like(StringUtils.isNotBlank(entity.getDictLabel()), SysDictDO::getDictLabel, entity.getDictLabel());
        }
        Page<SysDictDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(sysDictService.page(page, queryWrapper));
    }

    /**
     * 新增字典
     *
     * @param sysDictDO 字典参数
     * @return 是否添加成功
     */
    @PostMapping("add")
    public ResponseResult<Void> saveSysDict(@RequestBody SysDictDO sysDictDO) {
        LambdaQueryWrapper<SysDictDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictDO::getDictType, sysDictDO.getDictType())
                .eq(SysDictDO::getDictValue, sysDictDO.getDictValue());
        if (sysDictService.count(queryWrapper) > 0) {
            return ResponseResult.fail(ResponseCode.EXISTS_CODE, "字典名已存在");
        }
        sysDictService.save(sysDictDO);
        return ResponseResult.success();
    }

    /**
     * 更新字典
     *
     * @param sysDictDO 字典参数
     * @return 是否更新成功
     */
    @PostMapping("update")
    public ResponseResult<Void> updateSysDict(@RequestBody SysDictDO sysDictDO) {
        sysDictService.updateById(sysDictDO);
        return ResponseResult.success();
    }

    /**
     * 删除字典
     *
     * @param id 字典id
     * @return 是否删除成功
     */
    @PostMapping("delete")
    public ResponseResult<Void> deleteSysDict(@RequestBody Integer id) {
        sysDictService.removeById(id);
        return ResponseResult.success();
    }

    /**
     * 批量删除字典
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ResponseResult<Void> batchDeleteSysDict(@RequestBody List<Integer> ids) {
        sysDictService.removeByIds(ids);
        return ResponseResult.success();
    }

    /**
     * 获取字典选项
     *
     * @param dictType 字典类型
     * @return 字典选项
     */
    @GetMapping("/{dictType}")
    public ResponseResult<List<OptionVO>> getDictOption(@PathVariable String dictType) {
        LambdaQueryWrapper<SysDictDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictDO::getDictType, dictType);
        List<SysDictDO> list = sysDictService.list(queryWrapper);
        List<OptionVO> optionVOList = list.stream()
                .map(sysDictDO -> {
                    OptionVO optionVO = new OptionVO();
                    optionVO.setLabel(sysDictDO.getDictLabel());
                    optionVO.setValue(sysDictDO.getDictValue());
                    return optionVO;
                }).collect(Collectors.toList());
        return ResponseResult.success(optionVOList);
    }
}
