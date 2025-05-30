package com.example.demo.models.Vehiculos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ACCESORIO")
public class Accesorio extends ConfiguracionAdicional {

    public Accesorio() {}

    public Accesorio(String nombre, String descripcion, double precio) {
        super(nombre, descripcion, precio);
    }
}
