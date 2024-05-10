package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务-办公空间设备实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_space_equipment")
public class SpaceEquipmentDO {

    /**
     * 设备id
     */
    @TableId
    private Integer id;

    /**
     * 空间id
     */
    private Integer spaceId;

    /**
     * 设备名称
     */
    private String name;

}
