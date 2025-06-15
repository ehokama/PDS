package com.example.ConsecionariaPDS.models.Usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario{
    public Cliente() {}

    public Cliente(String dni, String nombre, String apellido, String password, String telefono, String correo) {
        super(dni, nombre, apellido, password, telefono, correo);
    }

    
}