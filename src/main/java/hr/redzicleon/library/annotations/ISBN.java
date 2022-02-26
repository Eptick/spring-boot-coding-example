package hr.redzicleon.library.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import hr.redzicleon.library.validators.ISBNValidator;
 
/**
 * Annotates a property so that it needs to contain a valid ISBN
 * Uses javax for validation in the @ISBNValidator
 * 
 * Example:
 * @ISBN
 * private String isbn;
 * 
 */
@Constraint(validatedBy = ISBNValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ISBN {
 
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
    String message() default "isbn_invalid";
}