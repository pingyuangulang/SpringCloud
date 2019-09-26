package com.jim.cloud.dynamic.datasource.aop;

import com.jim.cloud.dynamic.datasource.annotation.DynamicDataSourceAnnotation;
import com.jim.cloud.dynamic.datasource.mySource.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 动态数据源切面
 * 注解见
 * @see com.jim.cloud.dynamic.datasource.annotation.DynamicDataSourceAnnotation
 * @auth jim
 * @date 2019/9/26 22:58
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut(value = "execution(public * com.jim.cloud.dynamic.datasource.mapper..*.*(..)) && @annotation(com.jim.cloud.dynamic.datasource.annotation.DynamicDataSourceAnnotation)")
    public void pointCut(){

    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        DynamicDataSourceAnnotation annotation = getAnnotation(joinPoint);
        DataSourceType.DataBaseType dataBaseType = annotation.dataBaseType();
        DataSourceType.setDataBaseType(dataBaseType);
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        DataSourceType.clearDataBaseType();
    }

    /**
     * 获取注解信息
     *
     * @param joinPoint
     * @return
     */
    private DynamicDataSourceAnnotation getAnnotation(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DynamicDataSourceAnnotation annotation = methodSignature.getMethod().getAnnotation(DynamicDataSourceAnnotation.class);
        return annotation;
    }
}
