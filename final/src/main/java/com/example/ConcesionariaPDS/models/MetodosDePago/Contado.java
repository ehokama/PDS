package com.example.ConcesionariaPDS.models.MetodosDePago;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Contado")
public class Contado extends MetodoDePago {

    public Contado() {
    }


    
}
