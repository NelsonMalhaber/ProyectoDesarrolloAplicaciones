package com.uss.restaurante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.uss.restaurante.entity.Mesa;

import com.uss.restaurante.exception.GeneralServiceException;
import com.uss.restaurante.exception.ValidateServiceException;
import com.uss.restaurante.repository.MesaRepository;
import com.uss.restaurante.service.MesaService;
import com.uss.restaurante.validator.MesaValidator;

@Service
public class MesaServiceImpl implements MesaService {

    @Autowired
    private MesaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Mesa> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Mesa findById(int id) {
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
    public List<Mesa> findByNumeroContaining(String numero) {
        try {
            return repository.findByNumeroContaining(numero);
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional
    public Mesa create(Mesa mesa) {
        try {
            MesaValidator.save(mesa);
            return repository.save(mesa);
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
    @Transactional
    public Mesa update(Mesa mesa) {
        try {
            MesaValidator.save(mesa);
            Mesa mesaDb = findById(mesa.getId());
            mesaDb.setNumero(mesa.getNumero());
            mesaDb.setCapacidad(mesa.getCapacidad());
            mesaDb.setDescripcion(mesa.getDescripcion());
            return repository.save(mesaDb);
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error en el servidor", e);
        }
    }

    @Override
	@Transactional
	public int delete(int id) {
		try {
			Mesa mesaDb= findById(id);
			if(mesaDb==null) {
				return 0;
			}else {
				repository.delete(mesaDb);
				return 1;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Mesa findByNumero(String numero) {
		// TODO Auto-generated method stub
		return null;
	}
}
