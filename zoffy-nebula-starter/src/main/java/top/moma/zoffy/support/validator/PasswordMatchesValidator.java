package top.moma.zoffy.support.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import top.moma.zoffy.annotation.PasswordMatches;
import top.moma.zoffy.rbac.user.controller.request.UserRequest;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRequest> {

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(
      UserRequest userRequest, ConstraintValidatorContext constraintValidatorContext) {
    return userRequest.getPassword().equals(userRequest.getMatchingPassword());
  }
}
