package com.example.ConsecionariaPDS.models.Vehiculos.Builder;

import java.util.List;

import com.example.ConsecionariaPDS.models.Vehiculos.Caracteristicas.Color;
import com.example.ConsecionariaPDS.models.Vehiculos.Caracteristicas.ConfiguracionAdicional;
import com.example.ConsecionariaPDS.models.Vehiculos.Entidades.Auto;

public class AutoBuilder implements IVehiculoBuilder{
    private String marca;
    private String modelo;
    private int año;
    private Color color;
    private String patente;
    private int numeroChasis;
    private int numeroMotor;
    private double precio;
    private List<ConfiguracionAdicional> adicionales;


    @Override
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public void setAño(int año) {
        this.año = año;

    }

    @Override
    public void setColor(Color color) {
        this.color = color;

    }

    @Override
    public void setPatente(String patente) {
        this.patente = patente;

    }

    @Override
    public void setNumeroChasis(int numeroChasis) {
        this.numeroChasis = numeroChasis;

    }

    @Override
    public void setNumeroMotor(int numeroMotor) {
        this.numeroMotor = numeroMotor;

    }

    @Override
    public void setPrecio(double precio) {
        this.precio = precio;

    }

    @Override
    public void setAdicionales(List<ConfiguracionAdicional> adicionales) {
        this.adicionales = adicionales;
    }

    public Auto getAuto(){
        return new Auto(marca, modelo, año, color, patente, numeroChasis, numeroMotor, precio, adicionales);
    }



    
}
