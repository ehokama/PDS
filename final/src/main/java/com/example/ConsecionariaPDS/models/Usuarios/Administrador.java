package com.example.ConsecionariaPDS.models.Usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Administrador")

public class Administrador extends Usuario{
    public Administrador() {}

    
    public Administrador(String dni, String nombre, String apellido, String password, String telefono, String correo) {
        super(dni, nombre, apellido, password, telefono, correo);
    }
    
}