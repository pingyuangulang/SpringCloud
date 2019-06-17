package com.jim.cloud.service.impl;

import com.jim.cloud.enums.CacheOpsType;
import com.jim.cloud.mapper.UserMapper;
import com.jim.cloud.po.User;
import com.jim.cloud.service.UserService;
import com.jim.cloud.util.PojoUtils;
import com.jim.cloud.util.RedisUtil;
import com.jim.cloud.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

/**
 * 用户服务实现
 *
 * @author jim
 * @date 2019/6/15 22:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo deleteByPrimaryKey(Long id) {
        UserVo userVo = selectByPrimaryKey(id);
        int del = userMapper.deleteByPrimaryKey(id);
        User user = new User();
        PojoUtils.copyProperties(userVo, user);
        opsCache(del > 0, user, CacheOpsType.DELETE);
        return userVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo insert(User record) {
        int ins = userMapper.insert(record);
        return opsCache(ins > 0, record, CacheOpsType.INSERT);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo insertSelective(User record) {
        int ins = userMapper.insertSelective(record);
        return opsCache(ins > 0, record, CacheOpsType.INSERT);
    }

    @Override
    public UserVo selectByPrimaryKey(Long id) {
        User user = redisUtil.getStr(generateKey(id), User.class);
        if (Objects.isNull(user)) {
            user = userMapper.selectByPrimaryKey(id);
        }
        UserVo userVo = new UserVo();
        PojoUtils.copyProperties(user, userVo);
        userVo.setId(id);
        return userVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo updateByPrimaryKeySelective(User record) {
        int update = userMapper.updateByPrimaryKeySelective(record);
        return opsCache(update > 0, record, CacheOpsType.DELETE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo updateByPrimaryKey(User record) {
        int update = userMapper.updateByPrimaryKey(record);
        return opsCache(update > 0, record, CacheOpsType.DELETE);
    }

    /**
     * 生成缓存key
     * @param id
     * @return
     */
    private String generateKey(Long id) {
        StringBuilder sb = new StringBuilder("user-");
        sb.append(id);
        return sb.toString();
    }

    /**
     * 写入或删除缓存
     * @param compare
     * @param record
     * @param type
     * @return
     */
    private UserVo opsCache(boolean compare, User record, CacheOpsType type) {
        UserVo userVo = null;
        compare = compare && Objects.nonNull(type) && Objects.nonNull(record);
        if (compare) {
            if (CacheOpsType.INSERT.equals(type)) {
                redisUtil.setStr(generateKey(record.getId()), record);
            } else {
                redisUtil.delStr(generateKey(record.getId()));
            }
            userVo = new UserVo();
            PojoUtils.copyProperties(record, userVo);
        }
        return userVo;
    }

}
