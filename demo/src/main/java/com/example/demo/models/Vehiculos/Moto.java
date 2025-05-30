package com.example.demo.models.Vehiculos;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Moto extends Vehiculo {

    public Moto(String marca, String modelo, int año, Color color, String patente, int numeroChasis,
                int numeroMotor, int asientos, double precio, boolean disponible,
                List<ConfiguracionAdicional> adicionales, EstadoVehiculo estado, int kilometraje, String imagenUrl) {
        super.setMarca(marca);
        super.setModelo(modelo);
        super.setAño(año);
        super.setColor(color);
        super.setPatente(patente);
        super.setNumeroChasis(numeroChasis);
        super.setNumeroMotor(numeroMotor);
        super.setAsientos(asientos);
        super.setPrecio(precio);
        super.setDisponible(disponible);
        super.setAdicionales(adicionales);
        super.setEstadoVehiculo(estado);
        super.setKilometraje(kilometraje);
        super.setImagenUrl(imagenUrl);
    }
    public Moto() {
        super();
    }
    public double calcularImpuestos() {
        // suma de impuesto nacional + provincial general + provincial adicional
        return ( getPrecio() * ( 0 + 0.05 + 0.01 ) );
    }
}
