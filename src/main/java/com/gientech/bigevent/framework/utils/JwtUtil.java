package com.gientech.bigevent.framework.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gientech.bigevent.framework.config.SecurityConfig;

import java.util.Date;
import java.util.Map;

/**
 * @author P0194339
 */
public class JwtUtil {

    //接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.Jwt.getEffectiveTime()))
                .sign(Algorithm.HMAC256(SecurityConfig.Jwt.getSecret()));
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
