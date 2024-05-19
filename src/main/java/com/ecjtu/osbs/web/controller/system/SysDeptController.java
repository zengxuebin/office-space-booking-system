package com.ecjtu.osbs.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.pojo.DO.SysDeptDO;
import com.ecjtu.osbs.pojo.DTO.system.SysDeptQuery;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.SysDeptService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门管理控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("system/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 分页查询部门
     *
     * @param pageInfo 查询参数
     * @return 部门列表
     */
    @PostMapping("page")
    public ResponseResult<Page<SysDeptDO>> getSysDeptList(@RequestBody PageInfo<SysDeptQuery> pageInfo) {
        SysDeptQuery entity = pageInfo.getEntity();
        LambdaQueryWrapper<SysDeptDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.like(StringUtils.isNotBlank(entity.getDeptName()), SysDeptDO::getDeptName, entity.getDeptName())
                    .like(StringUtils.isNotBlank(entity.getLeader()), SysDeptDO::getLeader, entity.getLeader())
                    .eq(StringUtils.isNotBlank(entity.getStatus()), SysDeptDO::getStatus, entity.getStatus());
        }
        Page<SysDeptDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(sysDeptService.page(page, queryWrapper));
    }

    /**
     * 新增部门
     *
     * @param sysDeptDO 部门参数
     * @return 是否添加成功
     */
    @PostMapping("add")
    public ResponseResult<Void> saveSysDept(@RequestBody SysDeptDO sysDeptDO) {
        LambdaQueryWrapper<SysDeptDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDeptDO::getDeptName, sysDeptDO.getDeptName());
        if (sysDeptService.count(queryWrapper) > 0) {
            return ResponseResult.fail(ResponseCode.EXISTS_CODE, "部门名称已存在");
        }
        sysDeptService.save(sysDeptDO);
        return ResponseResult.success();
    }

    /**
     * 更新部门
     *
     * @param sysDeptDO 部门参数
     * @return 是否更新成功
     */
    @PostMapping("update")
    public ResponseResult<Void> updateSysDept(@RequestBody SysDeptDO sysDeptDO) {
        sysDeptService.updateById(sysDeptDO);
        return ResponseResult.success();
    }

    /**
     * 删除部门
     *
     * @param id 部门id
     * @return 是否删除成功
     */
    @PostMapping("delete")
    public ResponseResult<Void> deleteSysDept(@RequestBody Integer id) {
        if (id.equals(1)) {
            return ResponseResult.fail(ResponseCode.FAILURE_CODE, "超级部门不能被删除");
        }
        sysDeptService.removeById(id);
        return ResponseResult.success();
    }

    /**
     * 批量删除部门
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ResponseResult<Void> batchDeleteSysDept(@RequestBody List<Integer> ids) {
        sysDeptService.removeByIds(ids);
        return ResponseResult.success();
    }

}
