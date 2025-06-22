package com.example.ConcesionariaPDS.models.MetodosDePago;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Tarjeta")
public class Tarjeta extends MetodoDePago {

    public Tarjeta() {
    }


    
}
