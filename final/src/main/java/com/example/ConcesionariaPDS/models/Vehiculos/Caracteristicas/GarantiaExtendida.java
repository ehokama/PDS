package com.example.ConcesionariaPDS.models.Vehiculos.Caracteristicas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GarantiaExtendida")

public class GarantiaExtendida extends ConfiguracionAdicional{

    private int duracionMeses;

    public GarantiaExtendida(String nombre, String descripcion, double precio) {
        super(nombre, descripcion, precio);
    }

    public GarantiaExtendida() {
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }
    
    


}
