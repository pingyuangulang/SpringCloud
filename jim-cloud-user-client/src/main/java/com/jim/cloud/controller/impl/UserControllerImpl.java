package com.jim.cloud.controller.impl;

import com.jim.cloud.controller.UserController;
import com.jim.cloud.po.User;
import com.jim.cloud.service.UserService;
import com.jim.cloud.util.FastJsonUtil;
import com.jim.cloud.util.PojoUtils;
import com.jim.cloud.vo.BaseSingleResponse;
import com.jim.cloud.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 用户微服务对外暴露的访问接口实现
 *
 * @author jim
 * @date 2019/6/15 22:56
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FastJsonUtil fastJsonUtil;

    @Override
    @DeleteMapping("/delete/{id}")
    public BaseSingleResponse<UserVo> deleteByPrimaryKey(@PathVariable("id") Long id) {
        log.info("删除用户：入参id={}", id);
        BaseSingleResponse<UserVo> response = new BaseSingleResponse<>();
        UserVo userVo = userService.deleteByPrimaryKey(id);
        response.setData(userVo);
        log.info("删除用户：返参={}", fastJsonUtil.toJsonString(response));
        return response;
    }

    @Override
    @PutMapping("/insert")
    public BaseSingleResponse<UserVo> insertSelective(@RequestBody @Valid UserVo record) {
        log.info("添加用户：入参={}", fastJsonUtil.toJsonString(record));
        BaseSingleResponse<UserVo> response = new BaseSingleResponse<>();
        User user = new User();
        PojoUtils.copyProperties(record, user);
        UserVo userVo = userService.insertSelective(user);
        response.setData(userVo);
        log.info("添加用户：返参={}", fastJsonUtil.toJsonString(response));
        return response;
    }

    @Override
    @GetMapping("/select/{id}")
    public BaseSingleResponse<UserVo> selectByPrimaryKey(@PathVariable("id") Long id) {
        log.info("查询用户：入参id={}", id);
        BaseSingleResponse<UserVo> response = new BaseSingleResponse<>();
        UserVo userVo = userService.selectByPrimaryKey(id);
        response.setData(userVo);
        log.info("查询用户：返参={}", fastJsonUtil.toJsonString(response));
        return response;
    }

    @Override
    @PostMapping("/update")
    public BaseSingleResponse<UserVo> updateByPrimaryKeySelective(@RequestBody @Valid UserVo record) {
        log.info("修改用户：入参={}", fastJsonUtil.toJsonString(record));
        BaseSingleResponse<UserVo> response = new BaseSingleResponse<>();
        User user = new User();
        PojoUtils.copyProperties(record, user);
        UserVo userVo = userService.updateByPrimaryKeySelective(user);
        response.setData(userVo);
        log.info("修改用户：返参={}", fastJsonUtil.toJsonString(response));
        return response;
    }
}
