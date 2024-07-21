package com.uss.restaurante.validator;

import com.uss.restaurante.entity.Trabajador;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TrabajadorValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Trabajador.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Trabajador trabajador = (Trabajador) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty");
        if (trabajador.getNombre().length() < 2 || trabajador.getNombre().length() > 100) {
            errors.rejectValue("nombre", "Size.trabajadorForm.nombre");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty");
        if (trabajador.getApellido().length() < 2 || trabajador.getApellido().length() > 100) {
            errors.rejectValue("apellido", "Size.trabajadorForm.apellido");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "documento", "NotEmpty");
        if (trabajador.getDocumento().length() != 15) {
            errors.rejectValue("documento", "Size.trabajadorForm.documento");
        }
    }
}
