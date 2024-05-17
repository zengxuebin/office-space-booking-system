package com.ecjtu.osbs.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.constant.ResponseCode;
import com.ecjtu.osbs.enums.RoleEnum;
import com.ecjtu.osbs.pojo.DO.SysUserDO;
import com.ecjtu.osbs.pojo.DTO.system.SysUserDTO;
import com.ecjtu.osbs.pojo.DTO.system.SysUserQuery;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.util.SecurityUtil;
import com.ecjtu.osbs.web.service.SysUserService;
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
 * 用户管理控制层
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:35
 */
@RestController
@RequestMapping("system/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询用户
     *
     * @param pageInfo 查询参数
     * @return 用户列表
     */
    @PostMapping("page")
    public ResponseResult<Page<SysUserDO>> getSysUserList(@RequestBody PageInfo<SysUserQuery> pageInfo) {
        SysUserQuery entity = pageInfo.getEntity();
        LambdaQueryWrapper<SysUserDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.like(StringUtils.isNotBlank(entity.getUsername()), SysUserDO::getUsername, entity.getUsername())
                    .like(StringUtils.isNotBlank(entity.getPhoneNumber()), SysUserDO::getPhoneNumber, entity.getPhoneNumber())
                    .like(StringUtils.isNotBlank(entity.getDeptId()), SysUserDO::getDeptId, entity.getDeptId());
        }
        Page<SysUserDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(sysUserService.page(page, queryWrapper));
    }

    /**
     * 新增用户
     *
     * @param sysUserDTO 用户参数
     * @return 是否添加成功
     */
    @PostMapping("add")
    public ResponseResult<Void> saveSysUser(@RequestBody SysUserDTO sysUserDTO) {
        SysUserDO sysUserDO = new SysUserDO();
        BeanUtils.copyProperties(sysUserDTO, sysUserDO);
        LambdaQueryWrapper<SysUserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserDO::getUsername, sysUserDO.getUsername());
        if (sysUserService.count(queryWrapper) > 0) {
            return ResponseResult.fail(ResponseCode.USERNAME_EXISTS_CODE, "用户名已存在");
        }

        LocalDateTime now = LocalDateTime.now();
        // 对密码进行加密
        sysUserDO.setPassword(SecurityUtil.encryptPassword(sysUserDO.getPassword()));
        // 只能创建普通用户
        sysUserDO.setRole(RoleEnum.USER.getValue());
        sysUserDO.setRegisterTime(now);
        sysUserDO.setUpdateTime(now);
        sysUserService.save(sysUserDO);
        return ResponseResult.success();
    }

    /**
     * 更新用户
     *
     * @param sysUserDTO 用户参数
     * @return 是否更新成功
     */
    @PostMapping("update")
    public ResponseResult<Void> updateSysUser(@RequestBody SysUserDTO sysUserDTO) {
        SysUserDO sysUserDO = new SysUserDO();
        BeanUtils.copyProperties(sysUserDTO, sysUserDO);
        sysUserDO.setUpdateTime(LocalDateTime.now());
        sysUserService.updateById(sysUserDO);
        return ResponseResult.success();
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    @PostMapping("delete")
    public ResponseResult<Void> deleteSysUser(@RequestBody Integer id) {
        if (RoleEnum.ADMIN.getValue().equals(id.toString())) {
            return ResponseResult.fail(ResponseCode.FAILURE_CODE, "管理员不能删除");
        }
        sysUserService.removeById(id);
        return ResponseResult.success();
    }

    /**
     * 批量删除用户
     *
     * @param ids id列表
     * @return 是否删除
     */
    @PostMapping("batchDelete")
    public ResponseResult<Void> batchDeleteSysUser(@RequestBody List<Integer> ids) {
        sysUserService.removeByIds(ids);
        return ResponseResult.success();
    }
}
