package com.ecjtu.osbs.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecjtu.osbs.pojo.DO.SysUserDO;
import com.ecjtu.osbs.pojo.DTO.system.SysUserDTO;
import com.ecjtu.osbs.pojo.PageInfo;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.web.service.SysUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseResult<Page<SysUserDO>> getSysUserList(@RequestBody PageInfo<SysUserDTO> pageInfo) {
        SysUserDTO entity = pageInfo.getEntity();
        LambdaQueryWrapper<SysUserDO> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(entity)) {
            queryWrapper.like(StringUtils.isNotBlank(entity.getUsername()), SysUserDO::getUsername, entity.getUsername())
                    .like(StringUtils.isNotBlank(entity.getPhone()), SysUserDO::getPhoneNumber, entity.getPhone())
                    .like(StringUtils.isNotBlank(entity.getDeptId()), SysUserDO::getDeptId, entity.getDeptId());
        }
        Page<SysUserDO> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        return ResponseResult.success(sysUserService.page(page, queryWrapper));
    }
}
