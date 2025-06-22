package com.example.ConcesionariaPDS.models.Usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Vendedor")


public class Vendedor extends Usuario{
    public Vendedor() { }

    public Vendedor(String dni, String nombre, String apellido, String password, String telefono, String correo) {
        super(dni, nombre, apellido, password, telefono, correo);
    }
    
}