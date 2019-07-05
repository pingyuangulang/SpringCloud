package com.jim.cloud.mapper.extend;

import com.jim.cloud.po.OrderLog;

import java.util.List;

/**
 * 订单表扩展接口
 *
 * @author jim
 * @date 2019/6/15 16:58
 */
public interface OrderLogExtendMapper {

    /**
     * SELECT * FROM order_log WHERE user_id = #{userId} AND status = 0;
     *
     * @param userId
     * @return
     */
    List<OrderLog> findByUserId(Long userId);
}
