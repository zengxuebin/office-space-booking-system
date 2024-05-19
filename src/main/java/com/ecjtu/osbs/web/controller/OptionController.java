package com.ecjtu.osbs.web.controller;

import com.ecjtu.osbs.pojo.DO.SpaceCategoryDO;
import com.ecjtu.osbs.pojo.DO.SpaceLocationDO;
import com.ecjtu.osbs.pojo.DO.SysDeptDO;
import com.ecjtu.osbs.pojo.ResponseResult;
import com.ecjtu.osbs.pojo.VO.OptionVO;
import com.ecjtu.osbs.web.service.SpaceCategoryService;
import com.ecjtu.osbs.web.service.SpaceLocationService;
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

    @Autowired
    private SpaceCategoryService categoryService;

    @Autowired
    private SpaceLocationService locationService;

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

    /**
     * 获取空间类别选项
     *
     * @return 部门选项
     */
    @GetMapping("category")
    public ResponseResult<List<OptionVO>> getCategoryOption() {
        List<SpaceCategoryDO> list = categoryService.list();
        List<OptionVO> optionVOList = list.stream()
                .map(categoryDO -> {
                    OptionVO optionVO = new OptionVO();
                    optionVO.setLabel(categoryDO.getCategoryName());
                    optionVO.setValue(categoryDO.getId().toString());
                    return optionVO;
                }).collect(Collectors.toList());
        return ResponseResult.success(optionVOList);
    }

    /**
     * 获取空间位置选项
     *
     * @return 部门位置
     */
    @GetMapping("location")
    public ResponseResult<List<OptionVO>> getLocationOption() {
        List<SpaceLocationDO> list = locationService.list();
        List<OptionVO> optionVOList = list.stream()
                .map(locationDO -> {
                    OptionVO optionVO = new OptionVO();
                    optionVO.setLabel(locationDO.getArea() + locationDO.getName());
                    optionVO.setValue(locationDO.getId().toString());
                    return optionVO;
                }).collect(Collectors.toList());
        return ResponseResult.success(optionVOList);
    }

}
