package com.gientech.bigevent.framework.interceptors;

import com.gientech.bigevent.framework.utils.JwtUtil;
import com.gientech.bigevent.framework.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        try {
            ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
            String redisToken = valueOperations.get(token);
            if (redisToken == null || !redisToken.equals(token)) {
                throw new RuntimeException();
            }

            Map<String, Object> claims = JwtUtil.parseToken(token);
//            将业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        清空ThreadLocal中的资源
        ThreadLocalUtil.remove();
    }
}
