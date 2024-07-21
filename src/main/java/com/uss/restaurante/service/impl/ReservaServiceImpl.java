package com.uss.restaurante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uss.restaurante.entity.Reserva;
import com.uss.restaurante.exception.GeneralServiceException;
import com.uss.restaurante.exception.ValidateServiceException;
import com.uss.restaurante.repository.ReservaRepository;
import com.uss.restaurante.service.ReservaService;
import com.uss.restaurante.validator.ReservaValidator;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository repository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Reserva> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Reserva findById(int id) {
        try {
            Reserva reservaDb = repository.findById(id)
                    .orElseThrow(() -> new ValidateServiceException("No hay un registro con ese ID"));
            return reservaDb;
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reserva> findByClienteId(int clienteId) {
        try {
            return repository.findByClienteId(clienteId);
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reserva> findByMesaId(int mesaId) {
        try {
            return repository.findByMesaId(mesaId);
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional
    public Reserva create(Reserva obj) {
        try {
            ReservaValidator.save(obj);
            // Additional validation logic if necessary
            return repository.save(obj);
        } catch (ValidateServiceException e) {
            throw new ValidateServiceException(e.getMessage());
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional
    public Reserva update(Reserva obj) {
        try {
            ReservaValidator.save(obj);
            Reserva reservaDb = findById(obj.getId());
            // Update fields
            reservaDb.setClienteId(obj.getClienteId());
            reservaDb.setMesaId(obj.getMesaId());
            reservaDb.setFecha(obj.getFecha());
            reservaDb.setHora(obj.getHora());
            reservaDb.setEstado(obj.getEstado());
            reservaDb.setTrabajadorId(obj.getTrabajadorId());
            return repository.save(reservaDb);
        } catch (ValidateServiceException e) {
            throw new ValidateServiceException(e.getMessage());
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Reserva reservaDb = findById(id);
            repository.delete(reservaDb);
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }
}
