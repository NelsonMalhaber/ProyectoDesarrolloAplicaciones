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

import com.uss.restaurante.entity.Usuario;
import com.uss.restaurante.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios") // www.localhost:8081/api/usuarios
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @GetMapping()
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
    
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        if (usuario.getTipoUsuario() == null || usuario.getPassword() == null || usuario.getTipoUsuario() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Usuario usuarioDb = service.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);
    }
    
    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
        Usuario usuarioDb = service.update(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDb);
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
