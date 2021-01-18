package com.payment.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface isValidEmail {
    String message() default "invalid email";
    Class<?> [] groups () default {};
    Class<? extends Payload>[] payload() default {};
}
