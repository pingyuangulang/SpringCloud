package com.jim.cloud.integration.impl;

import com.jim.cloud.dto.TicketDto;
import com.jim.cloud.integration.TicketTransfer;
import com.jim.cloud.integration.UrlConstant;
import com.jim.cloud.util.RestTemplateUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TicketTransferImpl implements TicketTransfer {

    @Autowired
    private RestTemplateUtils restTemplateUtils;

    /**
     * 远程访问票务系统
     * 通过票务id获取票务信息
     *
     * @param ticketId
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "ticketFallbackMethod")
    public TicketDto ticketInfo(Long ticketId) {
        Map<String, Long> map = new HashMap<>(8);
        map.put("id", ticketId);
        TicketDto dto = restTemplateUtils.getForObject(UrlConstant.TICKET_APP_HOST + UrlConstant.TICKET_GET, TicketDto.class, map);
        return dto;
    }

    /**
     * 服务降级
     *
     * @return
     */
    public TicketDto ticketFallbackMethod() {
        log.warn("票务系统请求出错,服务降级");
        return null;
    }
}
