package com.uss.restaurante.service;

import java.util.List;

import com.uss.restaurante.entity.Trabajador;

public interface TrabajadorService {
    public List<Trabajador> findAll();
    public Trabajador findById(int id);
    public Trabajador findByDocumento(String documento);
    public List<Trabajador> findByNombreContaining(String nombre);
    public Trabajador create(Trabajador obj);
    public Trabajador update(Trabajador obj);
    public int delete(int id);
}
