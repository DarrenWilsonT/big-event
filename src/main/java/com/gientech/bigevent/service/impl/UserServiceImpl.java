package com.gientech.bigevent.service.impl;

import com.gientech.bigevent.mapper.UserMapper;
import com.gientech.bigevent.pojo.User;
import com.gientech.bigevent.service.UserService;
import com.gientech.bigevent.utils.Md5Util;
import org.springframework.stereotype.Service;

/**
 * @author aimintang
 * @date 2024/2/21
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        //密码加密
        String md5PwdStr = Md5Util.getMD5String(password);
        //添加用户
        userMapper.register(username, md5PwdStr);
    }
}
