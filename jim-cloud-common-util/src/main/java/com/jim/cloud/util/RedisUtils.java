package com.jim.cloud.util;

import com.jim.cloud.annotation.MethodParamNotNullOrEmpty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 *
 * @author jim
 * @date 2019/6/15 12:58
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private FastJsonUtils fastJsonUtils;

    /**
     * @param key
     * @param value
     * @param time 缓存过期时间，单位：秒
     */
    @MethodParamNotNullOrEmpty
    public void setStr(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, fastJsonUtils.toJsonString(value), time, TimeUnit.SECONDS);
    }

    /**
     * 默认缓存一天
     * @param key
     * @param value
     */
    @MethodParamNotNullOrEmpty
    public void setStr(String key, Object value) {
        setStr(key, value, Constant.CACHE_EXPIRE_TIME);
    }

    /**
     * 获取字符串数据结构的value
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    @MethodParamNotNullOrEmpty
    public <T> T getStr(String key, Class<T> clazz) {
        T value = null;
        String jsonValue = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(jsonValue)) {
            value = fastJsonUtils.json2Object(jsonValue, clazz);
        }
        return value;
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    @MethodParamNotNullOrEmpty
    public boolean delStr(String key) {
        Boolean flag = redisTemplate.delete(key);
        flag = Objects.isNull(flag) ? false : flag;
        return flag;
    }

    /**
     * setnx 指令
     * key不存在，则添加；存在，则什么都不做
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public boolean setnx(String key, String value, int timeout) {
        boolean success;
        try {
            success = redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
            if (success) {
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("lock failed");
            sb.append(" key:{").append(key).append("} value:{").append(value).append("}");
            log.error(sb.toString(), e);
            success = false;
        }
        return success;
    }
}
