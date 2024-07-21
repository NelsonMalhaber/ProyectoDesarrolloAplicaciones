package com.uss.restaurante.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;
    
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;
    
    @Column(name = "trabajador_id", nullable = false)
    private int trabajadorId;

    // Enum para tipo_usuario
    public enum TipoUsuario {
        administrador,
        colaborador
    }
    
    // Constructor vacío (necesario para JPA)
    public Usuario() {
    }

    // Constructor con todos los campos
    public Usuario(String usuario, String password, TipoUsuario tipoUsuario, int trabajadorId) {
        this.usuario = usuario;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.trabajadorId = trabajadorId;
    }
    
    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }
}
