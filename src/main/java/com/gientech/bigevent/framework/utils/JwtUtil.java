package com.gientech.bigevent.framework.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gientech.bigevent.framework.config.SecurityConfig;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author P0194339
 */
public class JwtUtil {

    //接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims, StringRedisTemplate stringRedisTemplate) {
        String token = JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.Jwt.getEffectiveTime()))
                .sign(Algorithm.HMAC256(SecurityConfig.Jwt.getSecret()));

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(token, token, SecurityConfig.Jwt.getEffectiveTime(), TimeUnit.MILLISECONDS);
        return token;
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(SecurityConfig.Jwt.getSecret()))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}
