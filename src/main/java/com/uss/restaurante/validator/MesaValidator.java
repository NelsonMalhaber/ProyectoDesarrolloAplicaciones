package com.uss.restaurante.validator;

import com.uss.restaurante.entity.Mesa;
import com.uss.restaurante.exception.ValidateServiceException;

public class MesaValidator {
    public static void save(Mesa mesa) {
        String numeroStr = String.valueOf(mesa.getNumero());

        if (numeroStr == null || numeroStr.trim().isEmpty()) {
            throw new ValidateServiceException("El numero de mesa es requerido");
        }

        if (numeroStr.length() > 11) {
            throw new ValidateServiceException("El numero extenso");
        }
    }
}