package com.ecjtu.osbs.web.controller;

import com.ecjtu.osbs.pojo.DO.SysDeptDO;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.VO.OptionVO;
import com.ecjtu.osbs.web.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 选项控制层
 *
 * @author CaoLongHui
 * @since 2024/5/16 00:52
 */
@RestController
@RequestMapping("option")
public class OptionController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 获取部门选项
     *
     * @return 部门选项
     */
    @GetMapping("dept")
    public ResponseResult<List<OptionVO>> getDeptOption() {
        List<SysDeptDO> list = sysDeptService.list();
        List<OptionVO> optionVOList = list.stream()
                .map(sysDeptDO -> {
                    OptionVO optionVO = new OptionVO();
                    optionVO.setLabel(sysDeptDO.getDeptName());
                    optionVO.setValue(sysDeptDO.getId().toString());
                    return optionVO;
                }).collect(Collectors.toList());
        return ResponseResult.success(optionVOList);
    }

}
