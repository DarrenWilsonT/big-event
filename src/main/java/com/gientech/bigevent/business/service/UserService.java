package com.gientech.bigevent.business.service;

import com.gientech.bigevent.business.pojo.User;

/**
 * @author aimintang
 * @date 2024/2/21
 * @description
 */
public interface UserService {
    //根据用户名查询用户
    User findByUsername(String username);

    //用户注册
    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
