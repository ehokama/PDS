package com.example.ConsecionariaPDS.models.MetodosDePago;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Tarjeta")
public class Tarjeta extends MetodoDePago {

    public Tarjeta() {
        super("Pago con tarjeta de credito en 1 pago","Tarjeta de Credito", 10);
    }
    

    
}
