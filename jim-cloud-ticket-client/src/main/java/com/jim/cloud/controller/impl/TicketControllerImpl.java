package com.jim.cloud.controller.impl;

import com.jim.cloud.controller.TicketController;
import com.jim.cloud.po.Ticket;
import com.jim.cloud.response.vo.BaseSingleResponse;
import com.jim.cloud.service.TicketService;
import com.jim.cloud.util.FastJsonUtils;
import com.jim.cloud.util.PojoUtils;
import com.jim.cloud.vo.TicketVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 票务接口实现
 *
 * @author jim
 * @date 2019/6/23 23:20
 */
@RestController
@RequestMapping("/ticket")
@Slf4j
public class TicketControllerImpl implements TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FastJsonUtils fastJsonUtils;

    @Override
    @DeleteMapping("/delete/{id}")
    public BaseSingleResponse<TicketVo> deleteByPrimaryKey(@PathVariable(value = "id") Long id) {
        log.info("删除影票：入参id={}", id);
        BaseSingleResponse<TicketVo> response = new BaseSingleResponse<>();
        TicketVo ticketVo = ticketService.deleteByPrimaryKey(id);
        response.setData(ticketVo);
        log.info("删除影票：返参={}", fastJsonUtils.toJsonString(ticketVo));
        return response;
    }

    @Override
    @PutMapping("/insert")
    public BaseSingleResponse<TicketVo> insertSelective(@RequestBody @Valid TicketVo record) {
        log.info("添加影票：入参={}", fastJsonUtils.toJsonString(record));
        BaseSingleResponse<TicketVo> response = new BaseSingleResponse<>();
        Ticket ticket = new Ticket();
        PojoUtils.copyProperties(record, ticket);
        TicketVo ticketVo = ticketService.insertSelective(ticket);
        response.setData(ticketVo);
        log.info("添加影票：返参={}", fastJsonUtils.toJsonString(ticketVo));
        return response;
    }

    @Override
    @GetMapping("/select/{id}")
    public BaseSingleResponse<TicketVo> selectByPrimaryKey(@PathVariable("id") Long id) {
        log.info("查询影票：入参={}", id);
        BaseSingleResponse<TicketVo> response = new BaseSingleResponse<>();
        TicketVo ticketVo = ticketService.selectByPrimaryKey(id);
        response.setData(ticketVo);
        log.info("查询影票：返参={}", fastJsonUtils.toJsonString(ticketVo));
        return response;
    }

    @Override
    @PostMapping("/update")
    public BaseSingleResponse<TicketVo> updateByPrimaryKeySelective(TicketVo record) {
        log.info("修改影票：入参={}", fastJsonUtils.toJsonString(record));
        BaseSingleResponse<TicketVo> response = new BaseSingleResponse<>();
        Ticket ticket = new Ticket();
        PojoUtils.copyProperties(record, ticket);
        TicketVo ticketVo = ticketService.updateByPrimaryKeySelective(ticket);
        response.setData(ticketVo);
        log.info("修改影票：返参={}", fastJsonUtils.toJsonString(ticketVo));
        return response;
    }
}
