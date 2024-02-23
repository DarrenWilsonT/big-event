package com.gientech.bigevent.framework.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "gientech.security")
@Data
public class SecurityConfig {
    public static Jwt jwt;
    private Path path;

    public void setJwt(Jwt jwt){
        SecurityConfig.jwt = jwt;
    }

    @Data
    public static class Path {
        private List<String> permit;
    }

    public static class Jwt {
        public static Long effectiveTime;
        public static String secret;

        public void setEffectiveTime(Long effectiveTime){
            Jwt.effectiveTime = effectiveTime;
        }

        public static Long getEffectiveTime(){
            return effectiveTime;
        }

        public void setSecret(String secret){
            Jwt.secret = secret;
        }

        public static String getSecret(){
            return secret;
        }
    }
}
