package com.jim.cloud.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 方法参数不能为空
 * AOP实现参见com.jim.cloud.aspectj.MethodParamNotNullOrEmptyHandler
 * @author jim
 * @date 2019/6/15 13:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodParamNotNullOrEmpty {

}
