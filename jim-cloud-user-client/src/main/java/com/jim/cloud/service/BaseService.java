package com.jim.cloud.service;

import com.jim.cloud.enums.ErrorType;
import com.jim.cloud.error.ErrorFactory;
import com.jim.cloud.error.exception.ServiceException;

/**
 *
 *
 * @author jib
 * @date 2019/7/22 17:39
 */
public class BaseService {

    /**
     * 创建业务逻辑异常
     *
     * @param errorCode
     * @return
     */
    protected ServiceException createError(String errorCode) {
        Exception exception = ErrorFactory.createError(ErrorType.SERVICE, errorCode);
        if (exception instanceof ServiceException) {
            return (ServiceException) exception;
        } else {
            throw new RuntimeException("业务逻辑异常类创建失败");
        }
    }
}
