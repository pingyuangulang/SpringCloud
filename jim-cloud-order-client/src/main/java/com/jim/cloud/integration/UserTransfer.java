package com.jim.cloud.integration;

import com.jim.cloud.dto.UserDto;

/**
 * order中远程调用user
 *
 * @author jib
 * @date 2019/7/1 10:53
 */
public interface UserTransfer {

    UserDto userInfo(Long userId);
}
