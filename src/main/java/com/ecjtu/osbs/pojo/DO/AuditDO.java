package com.ecjtu.osbs.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 业务-审核实体类
 *
 * @author CaoLongHui
 * @since 2024/3/9 15:43
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("biz_audit")
public class AuditDO {

    /**
     * 审核id
     */
    @TableId
    private Integer id;

    /**
     * 预约id
     */
    private Integer reserveId;

    /**
     * 审核状态 0-待审核 1-审核通过 (-1)-审核不通过
     */
    private String status;

    /**
     * 审核意见
     */
    private String comment;

    /**
     * 审核人员
     */
    private String auditPerson;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
}
