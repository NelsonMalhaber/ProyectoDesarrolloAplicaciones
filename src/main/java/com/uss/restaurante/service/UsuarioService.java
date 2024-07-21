package com.uss.restaurante.service;



import java.util.List;

import com.uss.restaurante.entity.Usuario;



public interface UsuarioService {
    public List<Usuario> findAll();
    
    public Usuario findById(int id);
    
    public Usuario create(Usuario usuario);
    
    public Usuario update(Usuario usuario);
    
    public void delete(int id);
}
