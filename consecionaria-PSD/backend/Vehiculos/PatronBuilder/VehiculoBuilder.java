package backend.Vehiculos.PatronBuilder;

import backend.Vehiculos.Caracteristicas.Color;
import backend.Vehiculos.Caracteristicas.Estado;
import backend.Vehiculos.TiposVehiculos.Vehiculo;

public interface VehiculoBuilder {
    VehiculoBuilder setMarca(String marca);
    VehiculoBuilder setModelo(String modelo);
    VehiculoBuilder setAño(int año);
    VehiculoBuilder setColor(Color color);
    VehiculoBuilder setPatente(int patente);
    VehiculoBuilder setNumeroChasis(int numeroChasis);
    VehiculoBuilder setNumeroMotor(int numeroMotor);
    VehiculoBuilder setAsientos(int asientos);
    VehiculoBuilder setPrecio(double precio);
    VehiculoBuilder setDisponible(boolean disponible);
    VehiculoBuilder setEstado(Estado estado);
    Vehiculo build();
}
