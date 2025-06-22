package com.example.ConcesionariaPDS.models.Areas;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Ventas → Cobranzas → Impuestos → Embarque → Logística → Entrega → Seguimiento
// Las areas tambien implementan el Singleton aparte del Observer y del Chain of Responsability

@Entity
@DiscriminatorColumn(name = "nombre_area")
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "nombre_area"
)

@JsonSubTypes({
    @JsonSubTypes.Type(value = Ventas.class, name = "Ventas"),
    @JsonSubTypes.Type(value = Cobranzas.class, name = "Cobranzas"),
    @JsonSubTypes.Type(value = Impuestos.class, name = "Impuestos"),
    @JsonSubTypes.Type(value = Embarque.class, name = "Embarque"),
    @JsonSubTypes.Type(value = Logistica.class, name = "Logistica"),
    @JsonSubTypes.Type(value = Entrega.class, name = "Entrega"),
    @JsonSubTypes.Type(value = Seguimiento.class, name = "Seguimiento"),
})

public abstract class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

