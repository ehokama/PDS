package com.example.ConsecionariaPDS.models.MetodosDePago;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Tarjeta")
public class Tarjeta extends MetodoDePago {

    public Tarjeta() {
    }


    
}
