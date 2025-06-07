package Vehiculos.Builder;

import java.util.List;

import Vehiculos.Caracteristicas.Color;
import Vehiculos.Caracteristicas.ConfiguracionAdicional;

public interface IVehiculoBuilder {
    void setMarca(String marca);
    void setModelo(String modelo);
    void setAño(int año);
    void setColor(Color color);
    void setPatente(String patente);
    void setNumeroChasis(int numeroChasis);
    void setNumeroMotor(int numeroMotor);
    void setPrecio(double precio);
    void setAdicionales(List<ConfiguracionAdicional> adicionales);
}


