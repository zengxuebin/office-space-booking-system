package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务-办公空间位置实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_location")
public class SpaceLocationDO {

    /**
     * 共享空间位置id
     */
    @TableId
    private Integer id;

    /**
     * 所属区域
     */
    private String area;

    /**
     * 楼宇名称
     */
    private String name;

    /**
     * 负责人
     */
    private String chargePerson;

    /**
     * 管理单位
     */
    private String managementUnit;

    /**
     * 联系方式
     */
    private String phone;

}
