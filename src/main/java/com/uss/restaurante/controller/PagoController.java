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

import com.uss.restaurante.entity.Pago;
import com.uss.restaurante.service.PagoService;
import java.util.List;

@RestController
@RequestMapping("/api/pagos") //www.localhost:8081/api/pagos
public class PagoController {
	@Autowired
	private PagoService service;
	
	@GetMapping()
	public ResponseEntity<List<Pago>> getAll(){
		List<Pago> pagos= service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(pagos);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Pago> getById(@PathVariable("id") int id) {
		Pago pago = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(pago);
	}
	
	@PostMapping
	public ResponseEntity<Pago> create(@RequestBody Pago pago) {
		Pago pagoDb=service.create(pago);
		return ResponseEntity.status(HttpStatus.CREATED).body(pagoDb);
	}
	
	@PutMapping
	public ResponseEntity<Pago> update(@RequestBody Pago pago) {
		Pago pagoDb=service.update(pago);
		return ResponseEntity.status(HttpStatus.OK).body(pagoDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}