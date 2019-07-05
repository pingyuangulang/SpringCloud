package com.jim.cloud.service.impl;

import com.jim.cloud.dto.TicketDto;
import com.jim.cloud.dto.UserDto;
import com.jim.cloud.integration.TicketTransfer;
import com.jim.cloud.integration.UserTransfer;
import com.jim.cloud.mapper.extend.OrderLogExtendMapper;
import com.jim.cloud.po.OrderLog;
import com.jim.cloud.service.OrderService;
import com.jim.cloud.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单业务实现
 *
 * @author jib
 * @date 2019/7/3 17:03
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderLogExtendMapper orderLogExtendMapper;

    @Autowired
    private UserTransfer userTransfer;

    @Autowired
    private TicketTransfer ticketTransfer;

    /**
     * 根据会员id查询会员订单集合
     *
     * @param userId
     * @return
     */
    @Override
    public List<OrderVo> orderListByUserId(Long userId) {
        UserDto userDto = userTransfer.userInfo(userId);
        List<OrderLog> orderLogList = orderLogExtendMapper.findByUserId(userId);
        List<OrderVo> voList = orderLogList.stream().map(orderLog -> {
            OrderVo vo = new OrderVo();
            vo.setAmount(orderLog.getAmount());
            vo.setTotalPrice(orderLog.getTotalPrice());
            vo.setUserName(userDto.getName());
            TicketDto ticketDto = ticketTransfer.ticketInfo(orderLog.getTicketId());
            vo.setTicketName(ticketDto.getName());
            return vo;
        })
                .collect(Collectors.toList());
        return voList;
    }
}
