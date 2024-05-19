package com.ecjtu.osbs.pojo.DTO.space;

import lombok.Data;

/**
 * 共享空间查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/17 22:19
 */
@Data
public class SpaceQueryDTO {

    /**
     * 空间名称
     */
    private String spaceName;

    /**
     * 位置id
     */
    private String locationId;

    /**
     * 类别id
     */
    private String categoryId;

}
