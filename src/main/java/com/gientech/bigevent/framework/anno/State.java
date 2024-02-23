package com.gientech.bigevent.framework.anno;

import com.gientech.bigevent.framework.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author aimintang
 * @date 2024/2/23
 * @description
 */
@Documented//元注解
@Target({ElementType.FIELD})//元注解，标识可以用在哪里
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    //    校验失败后的提示信息
    String message() default "state参数的值只能是已发布或者草稿";

    //    指定分组
    Class<?>[] groups() default {};

    //    负载，获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
