package com.example.demo.models.MetodoDePago;

import jakarta.persistence.Entity;

@Entity
public class Transferencia extends MetodoDePago {

    public Transferencia() {
        super("Transferencia bancaria", "Transferencia");
    }


}
