package com.example.assignment.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StateValidator implements ConstraintValidator<StateValidation,String> {

    List<String> states = Arrays.asList("Uttar Pradesh", "Rajasthan","Punjab");
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        return states.contains(value);
    }
}
