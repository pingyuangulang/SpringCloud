package com.jim.cloud.error;

import com.jim.cloud.enums.ErrorType;
import com.jim.cloud.error.exception.ControllerException;
import com.jim.cloud.error.exception.IntegrationException;
import com.jim.cloud.error.exception.ServiceException;
import com.jim.cloud.util.PropertyUtils;
import java.io.InputStream;
import java.util.Properties;

/**
 * 错误工厂
 *
 * @author jib
 * @date 2019/7/22 11:26
 */
public class ErrorFactory {

    private static final Properties prop;

    static {
        InputStream in = ServiceException.class.getClassLoader().getResourceAsStream("error.properties");
        prop = PropertyUtils.readProperty(in);
    }

    public static Exception createError(ErrorType type, String errorCode) {
        String defaultValue = "服务端错误码定义错误";
        String msg = prop.getProperty(errorCode, defaultValue);
        Exception e;
        switch (type) {
            case SERVICE: {
                e = new ServiceException(errorCode, msg);
                break;
            }
            case CONTROLLER: {
                e = new ControllerException(errorCode, msg);
                break;
            }
            case INTEGRATION: {
                e = new IntegrationException(errorCode, msg);
                break;
            }
            default:
                e = new RuntimeException();
        }
        return e;
    }
}
