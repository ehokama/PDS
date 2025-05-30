package com.example.demo.models.Vehiculos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EQUIPAMENTO_EXTRA")
public class EquipamientoExtra extends ConfiguracionAdicional {

    public EquipamientoExtra() {}

    public EquipamientoExtra(String nombre, String descripcion, double precio) {
        super(nombre, descripcion, precio);
    }
}
