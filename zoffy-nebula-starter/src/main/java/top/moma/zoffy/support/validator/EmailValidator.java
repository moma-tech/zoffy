package top.moma.zoffy.support.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import top.moma.m64.core.constants.PatternExpressions;
import top.moma.m64.core.helper.regular.RegularHelper;
import top.moma.zoffy.annotation.ValidEmail;

/**
 * EmailValidator
 *
 * <p>Email格式检查
 *
 * @version 1.0
 * @author Created by ivan at 18:12.
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

  @Override
  public void initialize(ValidEmail constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return validateEmail(s);
  }

  private boolean validateEmail(String email) {
    return RegularHelper.isMatch(PatternExpressions.EMAIL, email);
  }
}
