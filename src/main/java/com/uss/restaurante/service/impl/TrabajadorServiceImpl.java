package com.uss.restaurante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uss.restaurante.entity.Trabajador;
import com.uss.restaurante.repository.TrabajadorRepository;
import com.uss.restaurante.service.TrabajadorService;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    @Autowired
    private TrabajadorRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Trabajador> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Trabajador findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Trabajador findByDocumento(String documento) {
        return repository.findByDocumento(documento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trabajador> findByNombreContaining(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    @Override
    @Transactional
    public Trabajador create(Trabajador obj) {
        return repository.save(obj);
    }

    @Override
    @Transactional
    public Trabajador update(Trabajador obj) {
        return repository.save(obj);
    }

    @Override
    @Transactional
    public int delete(int id) {
        repository.deleteById(id);
        return id;
    }
}
