package com.jim.cloud.controller;

import com.jim.cloud.vo.BaseSingleResponse;
import com.jim.cloud.vo.UserVo;

/**
 * 用户微服务对外暴露的访问接口
 *
 * @author jim
 * @date 2019/6/15 22:58
 */
public interface UserController {

    BaseSingleResponse<UserVo> deleteByPrimaryKey(Long id);

    BaseSingleResponse<UserVo> insertSelective(UserVo record);

    BaseSingleResponse<UserVo> selectByPrimaryKey(Long id);

    BaseSingleResponse<UserVo> updateByPrimaryKeySelective(UserVo record);
}
