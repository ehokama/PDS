package com.example.demo.models.Usuarios;
import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario{

    public Administrador() {
        super();
    }

    public Administrador(String dni, String nombre, String password, String correo, String apellido, String telefono) {
        super(dni, nombre, password, correo, apellido, telefono);
    }
    
}
