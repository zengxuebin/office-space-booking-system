package com.ecjtu.osbs.pojo.DTO.reserve;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * 共享工位查询参数
 *
 * @author CaoLongHui
 * @since 2024/5/15 21:36
 */
@Data
public class OfficeSpaceQueryDTO {


    /**
     * 区域id
     */
    private String locationId;

    /**
     * 预约日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDate;

    /**
     * 共享空间名称
     */
    private String spaceName;

    /**
     * 可容纳人数
     */
    private Integer capacity;

}
