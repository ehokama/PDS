package com.example.ConsecionariaPDS.models.MetodosDePago;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Transferencia")
public class Transferencia extends MetodoDePago {

    public Transferencia() {
    }

    
}
