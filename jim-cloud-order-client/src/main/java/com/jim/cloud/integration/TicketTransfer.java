package com.jim.cloud.integration;

import com.jim.cloud.dto.TicketDto;

/**
 * 票务
 *
 * @author jib
 * @date 2019/7/3 17:26
 */
public interface TicketTransfer {

    TicketDto ticketInfo(Long ticketId);
}
