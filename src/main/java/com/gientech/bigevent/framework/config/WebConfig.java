package com.gientech.bigevent.framework.config;

import com.gientech.bigevent.framework.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录接口和注册接口不拦截
        List<String> pathPermit = SecurityConfig.pathPermit;
        registry.addInterceptor(loginInterceptor).excludePathPatterns(pathPermit.toArray(new String[0]));
    }
}
