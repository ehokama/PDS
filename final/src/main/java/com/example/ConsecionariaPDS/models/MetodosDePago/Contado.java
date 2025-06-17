package com.example.ConsecionariaPDS.models.MetodosDePago;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Contado")
public class Contado extends MetodoDePago {

    public Contado() {
    }


    
}
