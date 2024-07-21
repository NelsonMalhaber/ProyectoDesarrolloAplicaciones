package com.uss.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uss.restaurante.entity.Reserva;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    public List<Reserva> findByClienteId(int clienteId);
    public List<Reserva> findByMesaId(int mesaId);
}
