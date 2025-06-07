package Vehiculos.Entidades;

import java.util.List;

import Vehiculos.Caracteristicas.Color;
import Vehiculos.Caracteristicas.ConfiguracionAdicional;
import Vehiculos.State.Disponible;

public class Camioneta extends Vehiculo {
    
    public Camioneta(String marca, String modelo, int año, Color color, String patente, int numeroChasis,
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
    
    @Override
    public String toString() {
    return "Camioneta{" +
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
        return ( getPrecio() * ( 0.10 + 0.05 + 0.02 ) );
    }
}
