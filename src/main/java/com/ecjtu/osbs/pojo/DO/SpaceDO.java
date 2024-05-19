package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 业务-办公空间实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_space")
public class SpaceDO {

    /**
     * 办公空间id
     */
    @TableId
    private Integer id;

    /**
     * 类别id
     */
    private Integer categoryId;

    /**
     * 位置id
     */
    private String locationId;

    /**
     * 办公空间名称
     */
    private String spaceName;

    /**
     * 办公空间描述
     */
    private String description;

    /**
     * 总容量
     */
    private Integer capacity;

    /**
     * 每小时价格
     */
    private BigDecimal pricePerHour;

    /**
     * 状态 0-开放 2-维护中
     */
    private String status;
}
