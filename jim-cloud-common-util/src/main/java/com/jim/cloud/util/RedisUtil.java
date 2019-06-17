package com.jim.cloud.util;

import com.jim.cloud.annotation.MethodParamNotNullOrEmpty;
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
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private FastJsonUtil fastJsonUtil;

    /**
     * @param key
     * @param value
     * @param time 缓存过期时间，单位：秒
     */
    @MethodParamNotNullOrEmpty
    public void setStr(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, fastJsonUtil.toJsonString(value), time, TimeUnit.SECONDS);
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
            value = fastJsonUtil.json2Object(jsonValue, clazz);
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
}
