package com.example.demo.models.Usuarios;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Usuario{

    public Cliente() {
        super();
    }

    public Cliente(String dni, String nombre, String password, String correo, String apellido, String telefono) {
        super(dni, nombre, password, correo, apellido, telefono);
    }
}

