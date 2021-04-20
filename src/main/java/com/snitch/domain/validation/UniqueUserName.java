package com.snitch.domain.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { UniqueUserNameValid.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UniqueUserName {

    String message() default "{validation.UniqueUserName.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}