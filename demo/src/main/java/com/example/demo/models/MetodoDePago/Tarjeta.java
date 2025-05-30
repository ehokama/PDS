package com.example.demo.models.MetodoDePago;

import jakarta.persistence.Entity;

@Entity
public class Tarjeta extends MetodoDePago {

    public Tarjeta() {
        super("Pago con tarjeta (UNICAMENTE CREDITO)", "Tarjeta de Credito");
    }

}