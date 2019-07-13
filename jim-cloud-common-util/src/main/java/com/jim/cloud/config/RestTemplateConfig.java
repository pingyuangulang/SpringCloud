package com.jim.cloud.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置
 *
 * @author jib
 * @date 2019/7/4 12:02
 */
@Configuration
@EnableAutoConfiguration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.setRequestFactory(requestFactory());
        return restTemplate;
    }

    /**
     * 连接工厂
     *
     * @return
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient());
        // 连接不够用时，等待连接超时时间，毫秒
        factory.setConnectionRequestTimeout(3000);
        // 连接超时时间，毫秒
        factory.setConnectTimeout(3000);
        // 读取数据超时时间，毫秒
        factory.setReadTimeout(3000);
        return factory;
    }

    /**
     * HTTP/HTTPS client
     *
     * @return
     */
    @Bean
    public HttpClient httpClient() {
        // 连接池设置，默认的连接池已注册HTTP和HTTPS请求
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        // 同路由并发数
        manager.setDefaultMaxPerRoute(100);
        // 最大连接数
        manager.setMaxTotal(500);
        // 建造者模式
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setConnectionManager(manager);
        // 失败重试3次
        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
        HttpClient httpClient = builder.build();
        return httpClient;
    }
}
