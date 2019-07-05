package com.jim.cloud.integration.impl;

import com.jim.cloud.dto.TicketDto;
import com.jim.cloud.integration.TicketTransfer;
import com.jim.cloud.integration.UrlConstant;
import com.jim.cloud.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * order远程调用ticket
 *
 * @author jib
 * @date 2019/7/4 10:33
 */
@Component
public class TicketTransferImpl implements TicketTransfer {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public TicketDto ticketInfo(Long ticketId) {
        Map<String, Long> map = new HashMap<>(8);
        map.put("id", ticketId);
        TicketDto dto = restTemplateUtil.getForObject(UrlConstant.TICKET_APP_HOST + UrlConstant.TICKET_GET, TicketDto.class, map);
        return dto;
    }
}
