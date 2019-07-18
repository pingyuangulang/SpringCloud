package com.jim.cloud.aspectj;

import com.jim.cloud.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 注解@DistributedLock的AOP实现
 *
 * @author jib
 * @date 2019/7/18 11:21
 */
@Aspect
@Component
@Order(value = 1)
@Slf4j
public class DistributedLockHandler {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("execution(public * *..*.*(..)) && @annotation(com.jim.cloud.annotation.DistributedLock)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around() {

    }

    /**
     * 加锁
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    private boolean lock(String key, String value, int timeout) {
        boolean flag = redisUtil.setnx(key, value, timeout);
        String loginfo = "key:{},value:{},timeout:{}s lock " + (flag ? "success" : "failed");
        log.info(loginfo, key, value, timeout);
        return flag;
    }

    private boolean unLock(String key, String value) {

    }
}
