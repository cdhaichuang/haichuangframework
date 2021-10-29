package pro.haichuang.framework.base.validate.phone;

import pro.haichuang.framework.base.constant.PatternConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 手机号验证
 *
 * @author JiYinchuan
 */
public class PhoneValidator implements ConstraintValidator<Phone, CharSequence> {

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value != null) {
            String stringValue = value.toString();
            return Pattern.matches(PatternConstant.PHONE, stringValue);
        } else {
            return true;
        }
    }
}
