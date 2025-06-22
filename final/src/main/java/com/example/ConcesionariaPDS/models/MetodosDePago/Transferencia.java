package com.example.ConcesionariaPDS.models.MetodosDePago;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Transferencia")
public class Transferencia extends MetodoDePago {

    public Transferencia() {
    }

    
}
