package com.jim.cloud.dynamic.datasource.mapper;

import com.jim.cloud.dynamic.datasource.annotation.DynamicDataSourceAnnotation;
import com.jim.cloud.dynamic.datasource.model.User;
import com.jim.cloud.dynamic.datasource.mySource.DataSourceType;

/**
 *
 *
 * @auth jim
 * @date 2019/9/26 22:40
 */
public interface UserMapper {

    @DynamicDataSourceAnnotation
    User findByIdFromMaster(Long id);

    @DynamicDataSourceAnnotation(dataBaseType = DataSourceType.DataBaseType.SLAVE)
    User findByIdFromslave(Long id);
}
