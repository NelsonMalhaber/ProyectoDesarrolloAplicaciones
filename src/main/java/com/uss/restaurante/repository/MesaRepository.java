package com.uss.restaurante.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uss.restaurante.entity.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    Optional<Mesa> findByNumero(String numero);
	List<Mesa> findByNumeroContaining(String numero);
}
