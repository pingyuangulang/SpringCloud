package com.jim.cloud.controller;


import com.jim.cloud.response.vo.BaseSingleResponse;
import com.jim.cloud.vo.TicketVo;

/**
 * 票务接口
 *
 * @author jim
 * @date 2019/6/23 23:17
 */
public interface TicketController {

    BaseSingleResponse<TicketVo> deleteByPrimaryKey(Long id);

    BaseSingleResponse<TicketVo> insertSelective(TicketVo record);

    BaseSingleResponse<TicketVo> selectByPrimaryKey(Long id);

    BaseSingleResponse<TicketVo> updateByPrimaryKeySelective(TicketVo record);
}
