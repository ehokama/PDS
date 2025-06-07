package com.example.ConsecionariaPDS.models.Vehiculos.State;

import com.example.ConsecionariaPDS.models.Vehiculos.Entidades.Vehiculo;



public class Disponible extends VehiculoEstado{

    public Disponible(Vehiculo vehiculo) {
        super(vehiculo);
    }

    @Override
    public void setDisponible() {
        System.out.println("Vehiculo con patente "+ vehiculo.getPatente() +" ya se encuentra disponible.");
    }

    @Override
    public void setProcesoDeVenta() {
        vehiculo.setVehiculoEstado(new EnProcesoDeVenta(vehiculo));
        vehiculo.setTipoEstado("EnProcesoDeVenta");
        System.out.println("Vehiculo con patente "+ vehiculo.getPatente() +" pasando de estado Disponible a EnProcesoDeVenta.");
    }

    @Override
    public void setVendido() {
        System.out.println("Para marcar el vehiculo con patente "+ vehiculo.getPatente() +" como vendido primero debe ponerse en ProcesoDeVenta.");
    }
    
}