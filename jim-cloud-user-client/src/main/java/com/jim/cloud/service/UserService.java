package com.jim.cloud.service;

import com.jim.cloud.po.User;
import com.jim.cloud.vo.UserVo;

/**
 * 用户服务接口
 *
 * @author jim
 * @date 2019/6/15 22:27
 */
public interface UserService {

    UserVo deleteByPrimaryKey(Long id);

    UserVo insert(User record);

    UserVo insertSelective(User record);

    UserVo selectByPrimaryKey(Long id);

    UserVo updateByPrimaryKeySelective(User record);

    UserVo updateByPrimaryKey(User record);
}
