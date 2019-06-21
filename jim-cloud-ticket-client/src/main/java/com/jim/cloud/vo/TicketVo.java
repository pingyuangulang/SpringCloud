package com.jim.cloud.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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

    private String name;

    private Integer laveNum;

    private Long price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Byte status;
}
