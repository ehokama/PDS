package com.example.ConsecionariaPDS.models.MetodosDePago;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;

@Entity	
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "metodo_pago")
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "metodo_pago"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Contado.class, name = "Contado"),
    @JsonSubTypes.Type(value = Tarjeta.class, name = "Tarjeta"),
    @JsonSubTypes.Type(value = Transferencia.class, name = "Transferencia"),
})


public abstract class MetodoDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private double recargoPorcentaje;

    

    public MetodoDePago() {
    }

    public MetodoDePago(String descripcion, String nombre, double recargoPorcentaje) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.recargoPorcentaje = recargoPorcentaje;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getRecargoPorcentaje(){
        return this.recargoPorcentaje;
    }

    public double calcularRecargo(double montoBase) {
        return montoBase * (recargoPorcentaje / 100.0);
    }

}