package com.ecjtu.osbs.pojo.DTO.system;

import lombok.Data;

/**
 * 字典查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:41
 */
@Data
public class SysDictQuery {

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典标签
     */
    private String dictLabel;

}
