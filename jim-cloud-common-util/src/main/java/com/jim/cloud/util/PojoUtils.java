package com.jim.cloud.util;

import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * POJO之间属性复制
 *
 * @author jim
 * @date 2019/6/15 23:37
 */
public class PojoUtils {

    /**
     * 对目标和源进行非空判断后再复制属性，避免BeanUtils.copyProperties()中抛出异常。
     * 若有一项为空则什么都不做
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        if (Objects.nonNull(source) && Objects.nonNull(target)) {
            BeanUtils.copyProperties(source, target);
        }
    }
}
