package com.jim.cloud.vo;

import lombok.Data;

/**
 * 订单信息
 *
 * @author jib
 * @date 2019/7/3 17:04
 */
@Data
public class OrderVo {

    private Integer amount;

    private Long totalPrice;

    private String userName;

    private String ticketName;

}
