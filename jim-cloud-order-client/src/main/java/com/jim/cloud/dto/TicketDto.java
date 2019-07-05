package com.jim.cloud.dto;

import lombok.Data;

import java.util.Date;

/**
 * 远程调用ticket数据接收
 *
 * @author jib
 * @date 2019/7/4 10:30
 */
@Data
public class TicketDto {

    private Long id;

    private String name;

    private Integer laveNum;

    private Long price;

    private Date startTime;

    private Date endTime;

    private Byte status;
}
