package com.uss.restaurante.validator;

import com.uss.restaurante.entity.Clientes;
import com.uss.restaurante.exception.ValidateServiceException;

public class ClientesValidator {
    public static void save(Clientes cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }
        if (cliente.getNombre().length() > 100) {
            throw new ValidateServiceException("El nombre es muy extenso");
        }
        if (cliente.getDocumento() == null || cliente.getDocumento().trim().isEmpty()) {
            throw new ValidateServiceException("El documento es requerido");
        }
        if (cliente.getDocumento().length() > 15) {
            throw new ValidateServiceException("El documento es muy extenso");
        }
        if (cliente.getTelefono() != null && cliente.getTelefono().length() > 15) {
            throw new ValidateServiceException("El teléfono es muy extenso");
        }
        if (cliente.getDireccion() != null && cliente.getDireccion().length() > 100) {
            throw new ValidateServiceException("La dirección es muy extensa");
        }
        if (cliente.getEmail() != null && cliente.getEmail().length() > 50) {
            throw new ValidateServiceException("El correo electrónico es muy extenso");
        }
    }
}
