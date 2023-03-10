package com.nevermore.lunar.server.validation.validators;

import com.nevermore.lunar.server.validation.contraint.EntityExistConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author nevermore
 */
@Constraint(validatedBy = EntityExistConstraint.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface EntityExists {

    String message() default "{lunar.validation.constraints.EntityExists.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
