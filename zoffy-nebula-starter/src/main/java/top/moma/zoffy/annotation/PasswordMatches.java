package top.moma.zoffy.annotation;

import static java.lang.annotation.ElementType.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import top.moma.zoffy.support.validator.PasswordMatchesValidator;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches {

  String message() default "Passwords don't match";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
