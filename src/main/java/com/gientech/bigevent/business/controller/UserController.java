package com.gientech.bigevent.business.controller;

import com.gientech.bigevent.business.pojo.Result;
import com.gientech.bigevent.business.pojo.User;
import com.gientech.bigevent.business.service.UserService;
import com.gientech.bigevent.framework.utils.JwtUtil;
import com.gientech.bigevent.framework.utils.Md5Util;
import com.gientech.bigevent.framework.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User u = userService.findByUsername(username);
        if (u != null) {
            return Result.error("用户名已存在");
        } else {
            userService.register(username, password);
            return Result.success();
        }
    }

    @PostMapping(value = "/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User loginUser = userService.findByUsername(username);
        if (loginUser != null) {
            if (loginUser.getPassword().equals(Md5Util.getMD5String(password))) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", loginUser.getId());
                claims.put("username", loginUser.getUsername());
                String token = JwtUtil.genToken(claims);

                return Result.success(token);
            } else {
                return Result.error("密码错误");
            }
        } else {
            return Result.error("用户名不存在");
        }
    }

    @GetMapping("/userInfo")
    public Result userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping(value = "/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping(value = "/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping(value = "/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String repeatPwd = params.get("repeat_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(repeatPwd)) {
            return Result.error("参数不能为空");
        } else {
            Map<String, Object> map = ThreadLocalUtil.get();
            String username = (String) map.get("username");
            User loginUser = userService.findByUsername(username);
            if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
                return Result.error("原密码错误");
            } else if (!newPwd.equals(repeatPwd)) {
                return Result.error("两次输入的密码不一致");
            } else {
                userService.updatePwd(newPwd);
                return Result.success();
            }
        }
    }
}
