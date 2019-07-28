package com.jim.cloud.aspectj;

import com.jim.cloud.annotation.DistributedLock;
import com.jim.cloud.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.UUID;

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

    @Pointcut("execution(public * *(..)) && @annotation(com.jim.cloud.annotation.DistributedLock)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result;
        // 包名
        String packageName = joinPoint.getTarget().getClass().getPackage().getName();
        // 类名（简名）
        String className = joinPoint.getTarget().getClass().getSimpleName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 方法名
        String methodName = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        // 参数类型
        StringBuilder sb = new StringBuilder();
        int length = parameterTypes.length;
        for (int i = 0; i < length; i++) {
            Class parameterType = parameterTypes[i];
            sb.append(parameterType.getSimpleName());
            if (i < length - 1) {
                sb.append(", ");
            }
        }
        String parameterTypeNames = sb.toString();
        // 获取注解
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        // 获取超时时间（多久未释放锁，则主动释放）
        int timeout = distributedLock.timeout();
        if (timeout <= 0) {
            StringBuilder sbException = new StringBuilder();
            sb.append("类").append(packageName).append(".").append(className);
            sb.append("方法").append(methodName).append("分布式锁超时时间设置非法");
            throw new RuntimeException(sbException.toString());
        }
        String key = generateKey(packageName, className, methodName, parameterTypeNames);
        String value = generateValue();
        // 加锁
        lock(key, value, timeout);
        Object[] args = joinPoint.getArgs();
        try {
            // 执行方法
            result = joinPoint.proceed(args);
        } finally {
            // 无论方法是否会抛出异常，都要释放锁
            unLock(key, value);
        }
        return result;
    }

    /**
     * 生成key
     * lock:{packageName}:{className}:{methodName}({parameterTypeName}...)
     *
     * @param packageName 包名
     * @param className 类简名
     * @param methodName 方法名
     * @param parameterTypeNames 参数类型简名,各参数类型名之间使用逗号空格(", ")隔开
     * @return
     */
    private String generateKey(String packageName, String className, String methodName, String parameterTypeNames) {
        StringBuilder sb = new StringBuilder("lock:");
        sb.append(packageName).append(":");
        sb.append(className).append(":");
        sb.append(methodName).append("(").append(parameterTypeNames).append(")");
        return sb.toString();
    }

    /**
     * 生成value
     *
     * @return
     */
    private String generateValue() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 加锁
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    private void lock(String key, String value, int timeout) {
        boolean flag = redisUtil.setnx(key, value, timeout);
        String loginfo = "key:{},value:{},timeout:{}s lock " + (flag ? "success" : "failed");
        log.info(loginfo, key, value, timeout);
        if (!flag) {
            StringBuilder sb = new StringBuilder();
            sb.append("key:{").append(key).append("},value:{").append(value).append("} 加锁失败");
            throw new RuntimeException(sb.toString());
        }
    }

    /**
     * 释放锁
     *
     * @param key
     * @param value
     */
    private void unLock(String key, String value) {
        String redisValue = redisUtil.getStr(key, String.class);
        if (StringUtils.equals(value, redisValue)) {
            redisUtil.delStr(key);
            log.info("key:{},value:{} unLock success", key, value);
        } else {
            log.info("lock does not match,key:{},value:{},redisValue:{} unLock failed", key, value, redisValue);
        }
    }
}
