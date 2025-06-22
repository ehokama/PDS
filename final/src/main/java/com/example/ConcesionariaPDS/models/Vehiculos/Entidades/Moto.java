package com.example.ConcesionariaPDS.models.Vehiculos.Entidades;

import java.util.List;

import com.example.ConcesionariaPDS.models.Vehiculos.Caracteristicas.Color;
import com.example.ConcesionariaPDS.models.Vehiculos.Caracteristicas.ConfiguracionAdicional;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Moto")
public class Moto extends Vehiculo{
    public Moto(String marca, String modelo, int año, Color color, String patente, int numeroChasis,
                int numeroMotor, double precio,
                List<ConfiguracionAdicional> adicionales) {
        super.setMarca(marca);
        super.setModelo(modelo);
        super.setAño(año);
        super.setColor(color);
        super.setPatente(patente);
        super.setNumeroChasis(numeroChasis);
        super.setNumeroMotor(numeroMotor);
        super.setPrecio(precio);
        super.setAdicionales(adicionales);
        super.setDisponible();
    }

    public Moto() {
        super();
    }
    
    @Override
    public String toString() {
    return "Moto{" +
            "marca='" + getMarca() + '\'' +
            ", modelo='" + getModelo() + '\'' +
            ", año=" + getAño() +
            ", color=" + getColor() +
            ", patente='" + getPatente() + '\'' +
            ", numeroChasis=" + getNumeroChasis() +
            ", numeroMotor=" + getNumeroMotor() +
            ", precio=" + getPrecio() +
            ", adicionales=" + getAdicionales() +
            ", estado=" + getVehiculoEstado() +
            '}';
    }

    @Override
    public double calcularImpuestos() {
        // suma de impuesto nacional + provincial general + provincial adicional
        return ( getPrecio() * ( 0 + 0.05 + 0.01 ) );
    }

    
}
