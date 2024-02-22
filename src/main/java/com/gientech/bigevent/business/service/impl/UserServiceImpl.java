package com.gientech.bigevent.business.service.impl;

import com.gientech.bigevent.business.mapper.UserMapper;
import com.gientech.bigevent.business.pojo.User;
import com.gientech.bigevent.business.service.UserService;
import com.gientech.bigevent.framework.utils.Md5Util;
import com.gientech.bigevent.framework.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    /**
     * @param avatarUrl
     */
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    /**
     * @param newPwd
     */
    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), id);
    }
}
