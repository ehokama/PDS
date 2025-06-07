package com.example.ConsecionariaPDS.models.Vehiculos.Caracteristicas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Accesorio")
public class Accesorio extends ConfiguracionAdicional{
    public Accesorio() {
    }
    public Accesorio(String nombre, String descripcion, double precio) {
        super(nombre, descripcion, precio);
    }
    
}
