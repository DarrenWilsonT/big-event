package com.gientech.bigevent.business.mapper;

import com.gientech.bigevent.business.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author aimintang
 * @date 2024/2/21
 * @description
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into user(username, password, create_time, update_time)" +
            " values(#{username}, #{md5PwdStr}, now(), now())")
    void register(String username, String md5PwdStr);

    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = now() where id = #{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("update user set password = #{newPwd}, update_time = now() where id = #{id}")
    void updatePwd(String newPwd, Integer id);
}
