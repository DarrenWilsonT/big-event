package com.gientech.bigevent.controller;

import com.gientech.bigevent.pojo.Result;
import com.gientech.bigevent.pojo.User;
import com.gientech.bigevent.service.UserService;
import com.gientech.bigevent.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aimintang
 * @date 2024/2/21
 * @description
 */
@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$")String username, @Pattern(regexp = "^\\S{5,16}$")String password){
        User u = userService.findByUsername(username);
        if(u != null){
            return Result.error("用户名已存在");
        }else{
            userService.register(username, password);
            return Result.success();
        }
    }

    @PostMapping(value = "/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$")String username, @Pattern(regexp = "^\\S{5,16}$")String password){
        User loginUser = userService.findByUsername(username);
        if(loginUser != null){
            if(loginUser.getPassword().equals(Md5Util.getMD5String(password))){
                return Result.success("jwt token");
            }else{
                return Result.error("密码错误");
            }
        }else{
            return Result.error("用户名不存在");
        }
    }
}
