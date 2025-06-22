package com.example.ConcesionariaPDS.models.Vehiculos.State;

import com.example.ConcesionariaPDS.models.Vehiculos.Entidades.Vehiculo;



public class Vendido extends VehiculoEstado{

    public Vendido(Vehiculo vehiculo) {
        super(vehiculo);
    }

    @Override
    public void setDisponible() {
        System.out.println("Vehiculo con patente "+ vehiculo.getPatente() +" ya se encuentra Vendido (imposible cambiar de estado).");

    }

    @Override
    public void setProcesoDeVenta() {
        System.out.println("Vehiculo con patente "+ vehiculo.getPatente() +" ya se encuentra Vendido (imposible cambiar de estado).");

    }

    @Override
    public void setVendido() {
        System.out.println("Vehiculo con patente "+ vehiculo.getPatente() +" ya se encuentra Vendido (imposible cambiar de estado).");
    }
    
}