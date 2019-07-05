package com.jim.cloud.integration.impl;

import com.jim.cloud.dto.UserDto;
import com.jim.cloud.integration.UrlConstant;
import com.jim.cloud.integration.UserTransfer;
import com.jim.cloud.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * order中远程调用user实现
 *
 * @author jib
 * @date 2019/7/1 10:54
 */
@Component
public class UserTransferImpl implements UserTransfer {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public UserDto userInfo(Long userId) {
        Map<String, Long> map = new HashMap<>(8);
        map.put("id", userId);
        UserDto userDto = restTemplateUtil.getForObject(UrlConstant.USER_APP_HOST + UrlConstant.USER_GET, UserDto.class, map);
        return userDto;
    }
}
