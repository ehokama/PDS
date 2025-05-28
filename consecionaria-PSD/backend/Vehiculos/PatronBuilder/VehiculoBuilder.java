package backend.Vehiculos.PatronBuilder;

import java.util.List;

import backend.Vehiculos.Caracteristicas.Color;
import backend.Vehiculos.Caracteristicas.ConfiguracionAdicional;
import backend.Vehiculos.Caracteristicas.EstadoVehiculo;
import backend.Vehiculos.TiposVehiculos.Vehiculo;

public interface VehiculoBuilder {
    VehiculoBuilder setMarca(String marca);
    VehiculoBuilder setModelo(String modelo);
    VehiculoBuilder setAño(int año);
    VehiculoBuilder setColor(Color color);
    VehiculoBuilder setPatente(String patente);
    VehiculoBuilder setNumeroChasis(int numeroChasis);
    VehiculoBuilder setNumeroMotor(int numeroMotor);
    VehiculoBuilder setAsientos(int asientos);
    VehiculoBuilder setPrecio(double precio);
    VehiculoBuilder setDisponible(boolean disponible);
    VehiculoBuilder setEstado(EstadoVehiculo estado);
    VehiculoBuilder setAdicionales(List<ConfiguracionAdicional> configuracionAdicional);
    VehiculoBuilder setKilometraje(int km);
    Vehiculo build();
}
