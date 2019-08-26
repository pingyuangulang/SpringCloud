package com.jim.cloud.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * JSON工具类
 *
 * @author jim
 * @date 2019/6/15 13:09
 */
@Component
public class FastJsonUtils {

    /**
     * 对象转换为JSON串
     * @param o
     * @return
     */
    public String toJsonString(Object o) {
        return Objects.isNull(o)? StringUtils.EMPTY : JSON.toJSONString(o);
    }

    /**
     * JSON串转换为对象
     * @param json
     * @param clazz
     * @return
     */
    public <T> T json2Object(String json, Class<T> clazz) {
        boolean flag = StringUtils.isBlank(json) || Objects.isNull(clazz);
        return flag ? null : JSON.parseObject(json, clazz);
    }
}
