package com.example.demo.validators;
import javax.validation.*;
@Constraint(validatedBy = ContactNumberValidator.class)
public @interface ContactNumberConstraint {
    String message() default "Invalid phone number";
}
