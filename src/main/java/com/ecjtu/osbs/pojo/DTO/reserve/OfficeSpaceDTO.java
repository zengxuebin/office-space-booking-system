package com.ecjtu.osbs.pojo.DTO.reserve;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * 工位预约实体类
 *
 * @author CaoLongHui
 * @since 2024/5/21 02:59
 */
@Data
public class OfficeSpaceDTO {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 办公空间id
     */
    private Integer spaceId;

    /**
     * 预约主题
     */
    private String topic;

    /**
     * 预约日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDate;

}
