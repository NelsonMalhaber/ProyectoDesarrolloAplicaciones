package com.uss.restaurante.validator;

import com.uss.restaurante.entity.Usuario;
import com.uss.restaurante.exception.ValidateServiceException;

public class UsuarioValidator {
    
    public static void save(Usuario usuario) {
        if (usuario.getUsuario() == null || usuario.getUsuario().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre de usuario es requerido");
        }
        
        if (usuario.getUsuario().length() > 50) {
            throw new ValidateServiceException("El nombre de usuario es muy extenso");
        }
        
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            throw new ValidateServiceException("La contraseña es requerida");
        }
        
        if (usuario.getPassword().length() > 50) {
            throw new ValidateServiceException("La contraseña es muy extensa");
        }
        
        // Puedes agregar más validaciones según tus requisitos
    }
    
}
