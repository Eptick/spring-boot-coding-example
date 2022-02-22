package hr.redzicleon.library.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hr.redzicleon.library.annotations.ISBN;

public class ISBNValidator
    implements ConstraintValidator<ISBN, Object> {

    private static org.apache.commons.validator.routines.ISBNValidator
        validator = 
            new org.apache.commons.validator.routines.ISBNValidator();

    public void initialize(ISBN isbn) {
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        if (!validator.isValid(value.toString())) return false;
        return true;
    }
}