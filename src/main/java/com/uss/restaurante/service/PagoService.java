package com.uss.restaurante.service;

import java.util.List;

import com.uss.restaurante.entity.Pago;

public interface PagoService {
	public List<Pago> findAll();
	public Pago findById(int id);
	public Pago findByDocumentoInquilino(String documentoInquilino);
	public List<Pago> findByDocumentoInquilinoContaining(String documentoInquilino);
    public Pago create(Pago obj);
    public Pago update(Pago obj);
    public int delete(int id);
}
