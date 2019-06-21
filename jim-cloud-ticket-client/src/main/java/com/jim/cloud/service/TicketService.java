package com.jim.cloud.service;

import com.jim.cloud.po.Ticket;
import com.jim.cloud.vo.TicketVo;

/**
 * 票务逻辑
 *
 * @author jib
 * @date 2019/6/21 17:47
 */
public interface TicketService {

    TicketVo deleteByPrimaryKey(Long id);

    TicketVo insertSelective(Ticket record);

    TicketVo selectByPrimaryKey(Long id);

    TicketVo updateByPrimaryKeySelective(Ticket record);

}
