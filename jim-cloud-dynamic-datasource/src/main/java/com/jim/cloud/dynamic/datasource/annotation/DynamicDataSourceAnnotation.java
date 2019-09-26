package com.jim.cloud.dynamic.datasource.annotation;

import com.jim.cloud.dynamic.datasource.mySource.DataSourceType;

import java.lang.annotation.*;

/**
 * 动态数据源注解
 * 切面实现见
 * @see com.jim.cloud.dynamic.datasource.aop.DynamicDataSourceAspect
 * @auth jim
 * @date 2019/9/26 22:59
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSourceAnnotation {
    DataSourceType.DataBaseType dataBaseType() default DataSourceType.DataBaseType.MASTER;
}
