package com.jim.cloud.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 接口返参
 *
 * @author jib
 * @date 2019/6/21 17:48
 */
@Data
public class TicketVo {

    private Long id;

    @NotNull(message = "电影名称不能为空")
    @Size(max = 10, message = "电影名称长度不能超过10个字符")
    private String name;

    @NotNull(message = "会员级别不能为空")
    private Integer laveNum;

    @NotNull(message = "影票价格不能为空")
    private Long price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Byte status;
}
