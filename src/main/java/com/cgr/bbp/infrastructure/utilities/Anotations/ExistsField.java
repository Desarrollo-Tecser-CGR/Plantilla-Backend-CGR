package com.cgr.bbp.infrastructure.utilities.Anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistsFieldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsField {
    String message() default "ya existe";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    // Parámetro para la clase de la entidad
    Class<?> entityClass();
 
    // Parámetro para el nombre del campo enla base de datos
    String fieldName();
}
