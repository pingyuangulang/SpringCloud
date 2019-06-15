package com.jim.cloud.aspectj;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.CollectionUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 注解@MethodParamNotNullOrEmpty的AOP实现
 *
 * @author jim
 * @date 2019/6/15 13:57
 */
@Aspect
public class MethodParamNotNullOrEmptyHandler {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.jim.cloud.annotation.MethodParamNotNullOrEmpty)")
    public void pointCut() {}

    /**
     * 方法执行前通知
     * @param joinPoint
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        // 标记：count>0时表示方法入参有为空，抛出RuntimeException。
        int count = 0;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        Method method = signature.getMethod();
        String methodName = method.getName();
        StringBuilder sb = new StringBuilder("类").append(className).append("---中方法").append(methodName);
        Parameter[] parameters = method.getParameters();
        Object[] params = joinPoint.getArgs();
        int length = params.length;
        for (int i = 0; i < length; i++) {
            Object param = params[i];
            if (isNon(param)) {
                count++;
                sb.append("---参数").append(parameters[i].getName()).append("值为null或empty");
            }
        }
        if (count > 0) {
            throw new RuntimeException(sb.toString());
        }
    }

    /**
     * 判断参数是否为空
     * @param param
     * @return true:空;false:非空
     */
    private boolean isNon(Object param) {
        boolean flag = false;
        if (Objects.isNull(param)) {
            flag = true;
        } else if (param instanceof String) {
            flag = StringUtils.isBlank((String) param);
        } else if (param instanceof Collection) {
            flag = CollectionUtils.isEmpty((Collection)param);
        } else if (param instanceof Map) {
            flag = CollectionUtils.isEmpty((Map) param);
        }
        return flag;
    }
}
