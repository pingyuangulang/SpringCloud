package com.jim.cloud.aspectj;

import com.jim.cloud.util.ClassUtils;
import com.jim.cloud.util.FastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;



/**
 * 对控制层的接口打印请求进入和返回的日志
 *
 * @author jib
 * @date 2019/7/23 14:04
 */
@Aspect
@Component
@Slf4j
public class AroundLogHandler {

    @Autowired
    private FastJsonUtil fastJsonUtil;

    @Pointcut(value = "execution(public com.jim.cloud..* *(..))")
    public void pointCut() {}

    /**
     * 环绕通知
     *
     * @param joinPoint
     */
    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint joinPoint) {
        if (!isController(joinPoint)) {
            return;
        }
        Object[] args = before(joinPoint);
        try {
            long start = System.currentTimeMillis();
            Object o = arrayIsEmpty(args) ? joinPoint.proceed() : joinPoint.proceed(args);
            long end = System.currentTimeMillis();
            afterReturning(joinPoint, o, end - start);
        } catch (Throwable throwable) {
            afterThrowing(joinPoint, throwable);
        }
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    private Object[] before(JoinPoint joinPoint) {
        String classMethodName = classMethodName(joinPoint);
        Object[] args = joinPoint.getArgs();
        int length = args.length;
        if (length > 0) {
            StringBuilder paramValue = new StringBuilder();
            for (int i = 0; i < length; i++) {
                paramValue.append("第").append(i + 1).append("个参数:");
                paramValue.append(fastJsonUtil.toJsonString(args[i]));
                if (i < length - 1) {
                    paramValue.append(",");
                }
            }
            log.info("方法:{}开始执行,入参:{}", classMethodName, paramValue.toString());
        } else {
            log.info("方法:{}开始执行,该方法没有参数列表", classMethodName);
        }
        return args;
    }

    /**
     * 后置正常通知
     *
     * @param joinPoint
     * @param result 方法执行结果
     * @param timeUse 方法耗时
     */
    private void afterReturning(JoinPoint joinPoint, Object result, long timeUse) {
        Class returnType = ((MethodSignature) joinPoint.getSignature()).getMethod().getReturnType();
        String classMethodName = classMethodName(joinPoint);
        if (void.class.equals(returnType)) {
            log.info("方法:{}执行完毕,耗时:{}ms,该方法没有返回参数", classMethodName, timeUse);
        } else {
            String resultJson = fastJsonUtil.toJsonString(result);
            log.info("方法:{}执行完毕,耗时:{}ms,返参:{}", classMethodName, timeUse, resultJson);
        }
    }

    /**
     * 后置异常通知
     *
     * @param joinPoint
     * @param e
     */
    private void afterThrowing(JoinPoint joinPoint, Throwable e) {
        String classMethodName = classMethodName(joinPoint);
        StringBuilder sb = new StringBuilder();
        sb.append("方法:").append(classMethodName).append("执行异常,异常原因:");
        log.error(sb.toString(), e);
    }

    /**
     * 全类名.方法名(参数类型列表)
     *
     * @param joinPoint
     * @return
     */
    private String classMethodName(JoinPoint joinPoint) {
        // 全类名
        String className = joinPoint.getTarget().getClass().getName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 方法名
        String methodName = method.getName();
        // 参数类型集合
        String paramType = ClassUtils.methodParamType(method);
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(".").append(methodName).append(paramType);
        return sb.toString();
    }

    /**
     * 判断切面是否是控制层
     * true:是控制层;false:不是控制层
     *
     * @param joinPoint
     * @return
     */
    private boolean isController(JoinPoint joinPoint) {
        Class clazz = joinPoint.getTarget().getClass();
        Annotation annotation = clazz.getAnnotation(RestController.class);
        annotation = Objects.isNull(annotation) ? clazz.getAnnotation(Controller.class) : annotation;
        return Objects.nonNull(annotation);
    }

    /**
     * 判断数组是否为空
     * true:为空;false:不为空
     *
     * @param args
     * @return
     */
    private boolean arrayIsEmpty(Object[] args) {
        return Objects.isNull(args) || args.length == 0;
    }
}
