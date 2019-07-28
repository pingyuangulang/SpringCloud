package com.jim.cloud.util;


import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author jib
 * @date 2019/7/23 14:40
 */
public class ClassUtils {

    /**
     * 获取方法参数类型列表
     *
     * @param method
     * @return
     */
    public static String methodParamType(Method method) {
        Class[] paramClazz = method.getParameterTypes();
        StringBuilder paramType = new StringBuilder("(");
        int length = paramClazz.length;
        for (int i = 0; i < length; i++) {
            paramType.append(paramClazz[i].getSimpleName());
            if (i < length - 1) {
                paramType.append(", ");
            }
        }
        paramType.append(")");
        return paramType.toString();
    }
}
