package com.example.demo.models.MetodoDePago;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_metodo", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Tarjeta.class, name = "Tarjeta"),
    @JsonSubTypes.Type(value = Transferencia.class, name = "Transferencia"),
    @JsonSubTypes.Type(value = Contado.class, name = "Contado"),
})

public class MetodoDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    public MetodoDePago(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public MetodoDePago() {}

    public double calcularTotalConRecargos(){
        double totalConRecargos = 0;
        return totalConRecargos;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    
}