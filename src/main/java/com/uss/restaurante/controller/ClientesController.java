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

import com.uss.restaurante.entity.Clientes;
import com.uss.restaurante.service.ClientesService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes") // www.localhost:8081/api/clientes
public class ClientesController {
    @Autowired
    private ClientesService service;

    @GetMapping()
    public ResponseEntity<List<Clientes>> getAll() {
        List<Clientes> clientes = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Clientes> getById(@PathVariable("id") int id) {
        Clientes cliente = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PostMapping
    public ResponseEntity<Clientes> create(@RequestBody Clientes cliente) {
        Clientes clienteDb = service.create(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDb);
    }

    @PutMapping
    public ResponseEntity<Clientes> update(@RequestBody Clientes cliente) {
        Clientes clienteDb = service.update(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteDb);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}