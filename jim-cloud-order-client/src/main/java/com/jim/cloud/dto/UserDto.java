package com.jim.cloud.dto;

import lombok.Data;

/**
 * 远程调用user数据接收
 *
 * @author jib
 * @date 2019/7/1 10:55
 */
@Data
public class UserDto {

    private Long id;

    private String name;

    private String userName;

    private String pwd;

    private String phoneNum;

    private Long balance;

    private Byte level;

    private Byte status;
}
