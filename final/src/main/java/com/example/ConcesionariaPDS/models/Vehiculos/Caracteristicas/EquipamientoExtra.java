package com.example.ConcesionariaPDS.models.Vehiculos.Caracteristicas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EquipamientoExtra")
public class EquipamientoExtra extends ConfiguracionAdicional{

    public EquipamientoExtra() {
    }

    public EquipamientoExtra(String nombre, String descripcion) {
        super(nombre, descripcion, 0);
    }
    
}
