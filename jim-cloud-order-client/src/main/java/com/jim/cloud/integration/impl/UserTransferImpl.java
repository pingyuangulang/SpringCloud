package com.jim.cloud.integration.impl;

import com.jim.cloud.dto.UserDto;
import com.jim.cloud.integration.UrlConstant;
import com.jim.cloud.integration.UserTransfer;
import com.jim.cloud.util.RestTemplateUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserTransferImpl implements UserTransfer {

    @Autowired
    private RestTemplateUtils restTemplateUtils;

    /**
     * 远程访问会员系统
     * 根据会员id查询会员信息
     *
     * @param userId
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "userFallbackMethod")
    public UserDto userInfo(Long userId) {
        Map<String, Long> map = new HashMap<>(8);
        map.put("id", userId);
        UserDto userDto = restTemplateUtils.getForObject(UrlConstant.USER_APP_HOST + UrlConstant.USER_GET, UserDto.class, map);
        return userDto;
    }

    /**
     * 服务降级
     *
     * @return
     */
    public UserDto userFallbackMethod() {
        log.warn("会员系统出错,服务降级");
        return null;
    }
}
