package com.ecjtu.osbs.pojo;

import lombok.Data;

/**
 * 分页查询对象
 *
 * @author CaoLongHui
 * @since 2024/5/15 22:47
 */
@Data
public class PageInfo<T> {

    /**
     * 分页查询的页数
     */
    private Integer pageNum;

    /**
     * 分页查询的条数
     */
    private Integer pageSize;

    /**
     * 查询条件
     */
    private T entity;

}
