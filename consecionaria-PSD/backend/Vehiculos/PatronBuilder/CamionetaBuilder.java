package backend.Vehiculos.PatronBuilder;

import java.util.List;

import backend.Vehiculos.Caracteristicas.Color;
import backend.Vehiculos.Caracteristicas.ConfiguracionAdicional;
import backend.Vehiculos.Caracteristicas.Estado;
import backend.Vehiculos.TiposVehiculos.Camioneta;
import backend.Vehiculos.TiposVehiculos.Vehiculo;

public class CamionetaBuilder  implements VehiculoBuilder{
    private String marca;
    private String modelo;
    private int año;
    private Color color;
    private int patente;
    private int numeroChasis;
    private int numeroMotor;
    private int asientos;
    private double precio;
    private boolean disponible;
    private List<ConfiguracionAdicional> adicionales;
    private Estado estado;

  @Override
    public VehiculoBuilder  setMarca(String marca) {
        this.marca = marca;
        return this;
    }

    @Override
    public VehiculoBuilder  setModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    @Override
    public VehiculoBuilder  setAño(int año) {
        this.año = año;
        return this;
    }

    @Override
    public VehiculoBuilder  setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public VehiculoBuilder  setPatente(int patente) {
        this.patente = patente;
        return this;
    }

    @Override
    public VehiculoBuilder  setNumeroChasis(int numeroChasis) {
        this.numeroChasis = numeroChasis;
        return this;
    }

    @Override
    public VehiculoBuilder  setNumeroMotor(int numeroMotor) {
        this.numeroMotor = numeroMotor;
        return this;
    }

    @Override
    public VehiculoBuilder  setAsientos(int asientos) {
        this.asientos = asientos;
        return this;
    }

    @Override
    public VehiculoBuilder  setPrecio(double precio) {
        this.precio = precio;
        return this;
    }

    @Override
    public VehiculoBuilder  setDisponible(boolean disponible) {
        this.disponible = disponible;
        return this;
    }

    @Override
    public VehiculoBuilder  setEstado(Estado estado) {
        this.estado = estado;
        return this;
    }


    @Override
    public Vehiculo build() {
        return new Camioneta(marca, modelo, año, color, patente, numeroChasis, numeroMotor, asientos, precio, disponible, adicionales, estado);
    }
}

