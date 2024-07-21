package com.uss.restaurante.service;

import java.util.List;

import com.uss.restaurante.entity.Reserva;

public interface ReservaService {
    public List<Reserva> findAll();
    public Reserva findById(int id);
    public List<Reserva> findByClienteId(int clienteId);
    public List<Reserva> findByMesaId(int mesaId);
    public Reserva create(Reserva obj);
    public Reserva update(Reserva obj);
    public void delete(int id);
}
