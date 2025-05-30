package com.example.demo.models.Usuarios;
import jakarta.persistence.Entity;

@Entity
public class Vendedor extends Usuario{

    public Vendedor() {
        super();
    }

    public Vendedor(String dni, String nombre, String password, String correo, String apellido, String telefono) {
        super(dni, nombre, password, correo, apellido, telefono);
    }
    
}
