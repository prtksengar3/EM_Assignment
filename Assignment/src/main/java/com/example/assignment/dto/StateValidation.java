package com.example.assignment.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StateValidator.class)
public @interface StateValidation {
    String message() default "This is not valid State";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
