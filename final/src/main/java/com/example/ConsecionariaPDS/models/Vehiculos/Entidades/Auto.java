package com.example.ConsecionariaPDS.models.Vehiculos.Entidades;

import java.util.List;

import com.example.ConsecionariaPDS.models.Vehiculos.Caracteristicas.Color;
import com.example.ConsecionariaPDS.models.Vehiculos.Caracteristicas.ConfiguracionAdicional;
import com.example.ConsecionariaPDS.models.Vehiculos.State.Disponible;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Auto")
public class Auto extends Vehiculo{
        public Auto(String marca, String modelo, int año, Color color, String patente, int numeroChasis,
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
        super.setVehiculoEstado(new Disponible(this));
    }
    
    public Auto() {
        super();
    }

    @Override
    public double calcularImpuestos() {
        // suma de impuesto nacional + provincial general + provincial adicional
        return ( getPrecio() * ( 0.21 + 0.05 + 0.01 ) );
    }

    @Override
    public String toString() {
    return "Auto{" +
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

}
