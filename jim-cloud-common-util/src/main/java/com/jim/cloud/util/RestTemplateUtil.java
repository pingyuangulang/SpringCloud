package com.jim.cloud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

/**
 * RestTemplate 工具
 *
 * @author jib
 * @date 2019/7/1 11:05
 */
@Component
public class RestTemplateUtil {


    @Autowired
    private RestTemplate restTemplate;

    public <T> T getForObject(String url, Class<T> type, Map<String, ?> map) {
        return restTemplate.getForObject(url, type, map);
    }

    public <T> T postForObject(String url, Object request, Class<T> type, Map<String, ?> map) {
        return restTemplate.postForObject(url, request, type, map);
    }

}
