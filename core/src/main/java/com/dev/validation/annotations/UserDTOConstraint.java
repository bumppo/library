package com.dev.validation.annotations;

import com.dev.validation.UserDTOValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Check if userName already exist in DB.
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UserDTOValidator.class)
public @interface UserDTOConstraint {

    String message() default "com.dev.validation.annotations.UserDTOConstraint.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
