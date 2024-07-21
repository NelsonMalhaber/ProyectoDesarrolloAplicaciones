package com.uss.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uss.restaurante.entity.Reserva;
import com.uss.restaurante.service.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/trabajadores") // Ejemplo de URL: www.localhost:8081/api/trabajadores
public class TrabajadorController {

	@Autowired
	private ReservaService service;
	
	@GetMapping()
	public ResponseEntity<List<Reserva>> getAll(){
		List<Reserva> trabajador = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(trabajador);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Reserva> getById(@PathVariable("id") int id) {
		Reserva trabajador = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(trabajador);
	}
	
	@PostMapping
	public ResponseEntity<Reserva> create(@RequestBody Reserva reserva) {
		Reserva trabajador = service.create(reserva);
		return ResponseEntity.status(HttpStatus.CREATED).body(trabajador);
	}
	
	@PutMapping
	public ResponseEntity<Reserva> update(@RequestBody Reserva reserva) {
		Reserva trabajador = service.update(reserva);
		return ResponseEntity.status(HttpStatus.OK).body(trabajador);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
