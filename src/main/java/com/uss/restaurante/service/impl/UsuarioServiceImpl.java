package com.uss.restaurante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uss.restaurante.entity.Usuario;
import com.uss.restaurante.repository.UsuarioRepository;
import com.uss.restaurante.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        // Assuming usuario.getId() is not null and exists in the database
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }
}
