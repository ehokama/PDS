package com.example.ConsecionariaPDS.models.Vehiculos.State;


import com.example.ConsecionariaPDS.models.Vehiculos.Entidades.Vehiculo;



public abstract class VehiculoEstado {
    Vehiculo vehiculo;

    public VehiculoEstado(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public abstract void setDisponible();
    public abstract void setProcesoDeVenta();
    public abstract void setVendido();
}