package com.jim.cloud.enums;

/**
 * 层错误类型枚举
 *
 * @author jib
 * @date 2019/7/22 11:31
 */
public enum ErrorType {

    /** 业务逻辑层 */
    SERVICE((byte) 0),

    /** 请求外部接口层 */
    INTEGRATION((byte) 1),

    /** 控制层 */
    CONTROLLER((byte) 2);

    private Byte type;

    ErrorType(Byte type) {
        this.type = type;
    }

    public Byte getType() {
        return type;
    }
}
