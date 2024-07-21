package com.uss.restaurante.validator;

import com.uss.restaurante.entity.Reserva;
import com.uss.restaurante.exception.ValidateServiceException;

public class ReservaValidator {
    public static void save(Reserva reserva) {
        if (reserva.getClienteId() <= 0) {
            throw new ValidateServiceException("El ID del cliente es requerido y debe ser mayor que 0");
        }
        if (reserva.getMesaId() <= 0) {
            throw new ValidateServiceException("El ID de la mesa es requerido y debe ser mayor que 0");
        }
        if (reserva.getFecha() == null) {
            throw new ValidateServiceException("La fecha de la reserva es requerida");
        }
        if (reserva.getHora() == null || reserva.getHora().trim().isEmpty()) {
            throw new ValidateServiceException("La hora de la reserva es requerida");
        }
        if (reserva.getHora().length() > 8) {
            throw new ValidateServiceException("La hora de la reserva es muy extensa");
        }
        if (reserva.getTrabajadorId() <= 0) {
            throw new ValidateServiceException("El ID del trabajador es requerido y debe ser mayor que 0");
        }
        if (reserva.getEstado() == null) {
            throw new ValidateServiceException("El estado de la reserva es requerido");
        }
    }
}
