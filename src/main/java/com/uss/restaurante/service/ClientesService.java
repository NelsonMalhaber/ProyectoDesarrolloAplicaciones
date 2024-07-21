package com.uss.restaurante.service;

import java.util.List;
import com.uss.restaurante.entity.Clientes;

public interface ClientesService {
    public List<Clientes> findAll();
    public Clientes findById(int id);
    public Clientes findByDocumento(String documento);
    public List<Clientes> findByNombreContaining(String nombre);
    public Clientes create(Clientes obj);
    public Clientes update(Clientes obj);
    public int delete(int id);
}
