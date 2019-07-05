package com.jim.cloud.controller;

import com.jim.cloud.service.OrderService;
import com.jim.cloud.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单服务
 *
 * @author jib
 * @date 2019/7/4 10:47
 */
@RestController
@RequestMapping("/orderLog")
public class OrderLogController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public List<OrderVo> orderListByUserId() {
        return orderService.orderListByUserId(1L);
    }
}
