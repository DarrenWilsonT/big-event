package com.gientech.bigevent.framework.validation;

import com.gientech.bigevent.framework.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author aimintang
 * @date 2024/2/23
 * @description
 */
public class StateValidation implements ConstraintValidator<State, String> {
    /**
     * @param s                          将来要校验的数据
     * @param constraintValidatorContext context in which the constraint is evaluated
     * @return 如果返回false，则校验不通过，如果返回true，则校验通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        } else {
            return "已发布".equals(s) || "草稿".equals(s);
        }
    }
}
