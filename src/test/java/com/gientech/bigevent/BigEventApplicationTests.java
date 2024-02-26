package com.gientech.bigevent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class BigEventApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testTokenGenerator() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("username", "张三");

        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4))
                .sign(Algorithm.HMAC256("secret".getBytes()));

        System.out.println(token);
    }

    @Test
    public void testTokenParser() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoi5byg5LiJIn0sImV4cCI6MTcwODYxNjQwMH0.9sQM3jb-UWi_xKRSC7SFXYWIHZXloAd6CJ35WcfzQXY";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("secret".getBytes())).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);//验证token，生成一个解析后的JWT对象

//        decodedJWT.getClaim("user").asMap();
        System.out.println(decodedJWT.getClaim("user").asMap());
    }

    @Test
    public void redisSetTest() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("key", "value", 3, TimeUnit.SECONDS);
//        operations.getAndDelete("key");
        operations.getOperations().delete("key");

        System.out.println(operations.get("key"));
    }

    @Test
    public void redisGetTest() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        System.out.println(operations.get("key"));
    }

}
