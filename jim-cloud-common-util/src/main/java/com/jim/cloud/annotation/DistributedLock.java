package com.jim.cloud.annotation;

import java.lang.annotation.*;

/**
 * 分布式锁
 * AOP实现参见com.jim.cloud.aspectj.DistributedLockHandler
 *
 * @author jib
 * @date 2019/7/18 11:12
 */
@Documented
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    /** 锁的自动释放时间,单位:秒 */
    int timeout();
}
