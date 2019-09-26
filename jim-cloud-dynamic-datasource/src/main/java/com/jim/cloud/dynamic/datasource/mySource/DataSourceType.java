package com.jim.cloud.dynamic.datasource.mySource;

import java.util.Objects;

/**
 * 数据源类型
 *
 * @auth jim
 * @date 2019/9/26 22:16
 */
public class DataSourceType {

    public enum DataBaseType {
        MASTER,
        SLAVE
    }

    private static final ThreadLocal<DataBaseType> CURRENT_THREAD_TYPE = new ThreadLocal<>();

    public static void setDataBaseType(DataBaseType dataBaseType) {
        if (Objects.isNull(dataBaseType)) {
            throw new NullPointerException();
        }
        CURRENT_THREAD_TYPE.set(dataBaseType);
    }

    public static DataBaseType getDataBaseType() {
        DataBaseType dataBaseType = CURRENT_THREAD_TYPE.get();
        dataBaseType = Objects.isNull(dataBaseType) ? DataBaseType.MASTER : dataBaseType;
        return dataBaseType;
    }

    public static void clearDataBaseType() {
        CURRENT_THREAD_TYPE.remove();
    }
}
