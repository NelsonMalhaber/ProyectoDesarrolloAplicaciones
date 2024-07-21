package com.uss.restaurante.service;

import java.util.List;

import com.uss.restaurante.entity.Mesa;

public interface MesaService {
	List<Mesa> findAll();
    Mesa findById(int id);
    Mesa findByNumero(String numero);
    List<Mesa> findByNumeroContaining(String numero); // Cambiado a String para búsqueda flexible
    Mesa create(Mesa mesa);
    Mesa update(Mesa mesa);
    int delete(int id);
}
