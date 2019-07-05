package com.jim.cloud.service;

import com.jim.cloud.vo.OrderVo;

import java.util.List;

/**
 * 订单业务
 *
 * @author jib
 * @date 2019/7/3 16:59
 */
public interface OrderService {

    /**
     * 根据会员id查询会员订单集合
     *
     * @param userId
     * @return
     */
    List<OrderVo> orderListByUserId(Long userId);
}
