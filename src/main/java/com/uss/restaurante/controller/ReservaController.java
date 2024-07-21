package com.uss.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uss.restaurante.entity.Reserva;
import com.uss.restaurante.service.ReservaService;
import java.util.List;

@RestController
@RequestMapping("/api/reservas") //www.localhost:8081/api/reservas
public class ReservaController {
	@Autowired
	private ReservaService service;
	
	@GetMapping()
	public ResponseEntity<List<Reserva>> getAll(){
		List<Reserva> reservas = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(reservas);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Reserva> getById(@PathVariable("id") int id) {
		Reserva reserva = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(reserva);
	}
	
	@PostMapping
	public ResponseEntity<Reserva> create(@RequestBody Reserva reserva) {
		Reserva reservaDb = service.create(reserva);
		return ResponseEntity.status(HttpStatus.CREATED).body(reservaDb);
	}
	
	@PutMapping
	public ResponseEntity<Reserva> update(@RequestBody Reserva reserva) {
		Reserva reservaDb = service.update(reserva);
		return ResponseEntity.status(HttpStatus.OK).body(reservaDb);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
