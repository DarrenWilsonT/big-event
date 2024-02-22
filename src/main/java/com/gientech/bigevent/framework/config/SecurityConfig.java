package com.gientech.bigevent.framework.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
@Configuration
//@ConfigurationProperties(prefix = "gientech.security")
@Data
public class SecurityConfig {
    @Value("${gientech.security.jwt.effective-time}")
    public static Long jwtEffectiveTime;

    @Value("${gientech.security.jwt.secret}")
    public static String jwtSecret;
    
    @Value("${gientech.security.path.permit}")
    public static List<String> pathPermit;
}
