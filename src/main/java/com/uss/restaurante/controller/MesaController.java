package com.uss.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uss.restaurante.entity.Mesa;
import com.uss.restaurante.service.MesaService;

@RestController
@RequestMapping("/api/mesas") // www.localhost:8081/api/mesas
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<List<Mesa>> getAll() {
        List<Mesa> mesas = mesaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(mesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getById(@PathVariable("id") int id) {
        Mesa mesa = mesaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mesa);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Mesa>> findByNumeroContaining(@RequestParam String numero) {
        List<Mesa> mesas = mesaService.findByNumeroContaining(numero);
        return ResponseEntity.status(HttpStatus.OK).body(mesas);
    }

    @PostMapping
    public ResponseEntity<Mesa> create(@RequestBody Mesa mesa) {
        Mesa mesaDb = mesaService.create(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> update(@PathVariable("id") int id, @RequestBody Mesa mesa) {
        mesa.setId(id);
        Mesa mesaDb = mesaService.update(mesa);
        return ResponseEntity.status(HttpStatus.OK).body(mesaDb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        mesaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
