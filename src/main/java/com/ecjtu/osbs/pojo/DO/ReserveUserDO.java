package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务-受邀用户实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_reserve_user")
public class ReserveUserDO {

    /**
     * 受邀id
     */
    @TableId
    private Integer id;

    /**
     * 预约id
     */
    private Integer reserveId;

    /**
     * 受邀用户id
     */
    private Integer userId;

    /**
     * 受邀状态 0-待确认 (-1)-已拒绝 1-已确认
     */
    private String status;
}
