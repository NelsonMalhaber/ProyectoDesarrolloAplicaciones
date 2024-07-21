package com.uss.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uss.restaurante.entity.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
    public Trabajador findByDocumento(String documento);
    public List<Trabajador> findByNombreContaining(String nombre);
}
