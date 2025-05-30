package com.example.demo.models.MetodoDePago;

import jakarta.persistence.Entity;

@Entity
public class Contado extends MetodoDePago{
    
    public Contado(){
        super("Pago en efectivo","Efectivo-Contado");
    }

}
