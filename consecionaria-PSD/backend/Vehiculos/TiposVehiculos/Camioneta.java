package backend.Vehiculos.TiposVehiculos;

import java.util.List;

import backend.Vehiculos.Caracteristicas.Color;
import backend.Vehiculos.Caracteristicas.ConfiguracionAdicional;
import backend.Vehiculos.Caracteristicas.Estado;

public class Camioneta extends Vehiculo {

    public Camioneta(String marca, String modelo, int año, Color color, int patente, int numeroChasis,
                int numeroMotor, int asientos, double precio, boolean disponible,
                List<ConfiguracionAdicional> adicionales, Estado estado) {
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
        super.setEstado(estado);
    }
    @Override
    public double calcularImpuestos() {
        // suma de impuesto nacional + provincial general + provincial adicional
        return ( getPrecio() * ( 0.10 + 0.05 + 0.02 ) );
    }

}
