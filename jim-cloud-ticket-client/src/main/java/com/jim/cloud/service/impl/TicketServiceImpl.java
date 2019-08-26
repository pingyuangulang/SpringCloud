package com.jim.cloud.service.impl;

import com.jim.cloud.enums.CacheOpsType;
import com.jim.cloud.mapper.TicketMapper;
import com.jim.cloud.po.Ticket;
import com.jim.cloud.service.TicketService;
import com.jim.cloud.util.PojoUtils;
import com.jim.cloud.util.RedisUtils;
import com.jim.cloud.vo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 票务
 *
 * @author jib
 * @date 2019/6/21 17:51
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TicketVo deleteByPrimaryKey(Long id) {
        TicketVo ticketVo = selectByPrimaryKey(id);
        int del = ticketMapper.deleteByPrimaryKey(id);
        Ticket ticket = new Ticket();
        PojoUtils.copyProperties(ticketVo, ticket);
        opsCache(del > 0, ticket, CacheOpsType.DELETE);
        return ticketVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TicketVo insertSelective(Ticket record) {
        int insert = ticketMapper.insertSelective(record);
        opsCache(insert > 0, record, CacheOpsType.INSERT);
        TicketVo ticketVo = new TicketVo();
        PojoUtils.copyProperties(record, ticketVo);
        return ticketVo;
    }

    @Override
    public TicketVo selectByPrimaryKey(Long id) {
        String key = generateKey(id);
        Ticket ticket = redisUtils.getStr(key, Ticket.class);
        if (Objects.isNull(ticket)) {
            ticket = ticketMapper.selectByPrimaryKey(id);
            redisUtils.setStr(key, ticket);
        }
        TicketVo vo = new TicketVo();
        PojoUtils.copyProperties(ticket, vo);
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TicketVo updateByPrimaryKeySelective(Ticket record) {
        String key = generateKey(record.getId());
        int update = ticketMapper.updateByPrimaryKeySelective(record);
        opsCache(update > 0, record, CacheOpsType.DELETE);
        TicketVo vo = new TicketVo();
        PojoUtils.copyProperties(record, vo);
        return vo;
    }

    /**
     * 生成缓存key
     * @param id
     * @return
     */
    private String generateKey(Long id) {
        StringBuilder sb = new StringBuilder("ticket-");
        sb.append(id);
        return sb.toString();
    }

    /**
     * 写入或删除缓存
     * @param compare
     * @param record
     * @param type
     * @return
     */
    private TicketVo opsCache(boolean compare, Ticket record, CacheOpsType type) {
        TicketVo ticketVo = null;
        compare = compare && Objects.nonNull(type) && Objects.nonNull(record);
        if (compare) {
            if (CacheOpsType.INSERT.equals(type)) {
                redisUtils.setStr(generateKey(record.getId()), record);
            } else {
                redisUtils.delStr(generateKey(record.getId()));
            }
            ticketVo = new TicketVo();
            PojoUtils.copyProperties(record, ticketVo);
        }
        return ticketVo;
    }
}
