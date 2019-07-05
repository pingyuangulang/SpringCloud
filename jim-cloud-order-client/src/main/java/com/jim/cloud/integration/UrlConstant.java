package com.jim.cloud.integration;

/**
 * 服务调用地址
 *
 * @author jib
 * @date 2019/7/1 12:03
 */
public class UrlConstant {

    /** user host */
    public static final String USER_APP_HOST = "http://user-client";

    /** select user by id   GET */
    public static final String USER_GET = "/user/select/{id}";

    /** ticket host */
    public static final String TICKET_APP_HOST = "http://ticket-client";

    /** select ticket by id  GET */
    public static final String TICKET_GET = "/ticket/select/{id}";
}
