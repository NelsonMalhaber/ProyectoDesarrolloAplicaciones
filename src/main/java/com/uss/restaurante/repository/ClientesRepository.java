package com.uss.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uss.restaurante.entity.Clientes;
import java.util.List;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    public Clientes findByDocumento(String documento);
    public List<Clientes> findByNombreContaining(String nombre);
}
