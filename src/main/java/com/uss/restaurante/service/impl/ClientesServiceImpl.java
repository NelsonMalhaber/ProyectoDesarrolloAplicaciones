package com.uss.restaurante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uss.restaurante.entity.Clientes;
import com.uss.restaurante.exception.GeneralServiceException;
import com.uss.restaurante.exception.ValidateServiceException;
import com.uss.restaurante.repository.ClientesRepository;
import com.uss.restaurante.service.ClientesService;
import com.uss.restaurante.validator.ClientesValidator;

@Service
public class ClientesServiceImpl implements ClientesService {
    @Autowired
    private ClientesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Clientes> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Clientes findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new ValidateServiceException("No hay un registro con ese ID"));
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Clientes findByDocumento(String documento) {
        try {
            return repository.findByDocumento(documento);
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Clientes> findByNombreContaining(String nombre) {
        try {
            return repository.findByNombreContaining(nombre);
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional
	public Clientes create(Clientes obj) {
        try {
            ClientesValidator.save(obj);
            Clientes clienteExistente = findByDocumento(obj.getDocumento());
            if (clienteExistente != null) {
                throw new ValidateServiceException("Ya hay un registro con ese documento");
            }
            obj.setActivo(true);
            return repository.save(obj);
        } catch (ValidateServiceException e) {
            throw new ValidateServiceException(e.getMessage());
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
	}


    @Override
    @Transactional
	public Clientes update(Clientes obj) {
        try {
            ClientesValidator.save(obj);
            Clientes clienteDb = findById(obj.getId());
            Clientes clienteExistente = findByDocumento(obj.getDocumento());
            if (clienteExistente != null && obj.getId() != clienteExistente.getId()) {
                throw new ValidateServiceException("Ya hay un registro con ese documento");
            }
            clienteDb.setNombre(obj.getNombre());
            clienteDb.setDocumento(obj.getDocumento());
            clienteDb.setTelefono(obj.getTelefono());
            clienteDb.setDireccion(obj.getDireccion());
            clienteDb.setEmail(obj.getEmail());
            return repository.save(clienteDb);
        } catch (ValidateServiceException e) {
            throw new ValidateServiceException(e.getMessage());
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
	}
    @Override
    @Transactional
    public int delete(int id) {
        try {
            Clientes clienteDb = findById(id);
            if (clienteDb == null) {
                return 0;
            } else {
                repository.delete(clienteDb);
                return 1;
            }
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

}

