package com.gientech.bigevent.mapper;

import com.gientech.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
