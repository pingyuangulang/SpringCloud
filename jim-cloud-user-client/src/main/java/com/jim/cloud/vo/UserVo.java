package com.jim.cloud.vo;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 接口返参
 *
 * @author jim
 * @date 2019/6/15 23:06
 */
@Data
public class UserVo {

    private Long id;

    @NotNull
    @Size(min = 1, max = 10, message = "姓名长度必须在[1,10]之间")
    private String name;

    @NotNull
    @Size(min = 1, max = 10, message = "账号长度必须在[1,10]之间")
    private String userName;

    @NotNull
    @Size(min = 1, max = 10, message = "密码长度必须在[1,10]之间")
    private String pwd;

    @NotNull
    @Size(min = 11, max = 11, message = "手机号必须为11位")
    private String phoneNum;

    private Long balance;

    private Byte level;

    private Byte status;
}
