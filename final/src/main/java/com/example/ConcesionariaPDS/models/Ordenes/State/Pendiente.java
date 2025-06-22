package com.example.ConcesionariaPDS.models.Ordenes.State;

import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;



public class Pendiente extends StateOrden {

    public Pendiente(OrdenDeCompra orden) {
        super(orden);
    }

    @Override
    public void cancelar() {
        orden.setStateOrden(new Cancelada(orden));
        System.out.println("Orden cancelada desde estado PENDIENTE.");
    }

    @Override
    public void aprobar() {
        orden.setStateOrden(new Aprobada(orden));
        System.out.println("Orden aprobada desde estado PENDIENTE.");
    }

    @Override
    public String getNombre() {
        return "PENDIENTE";
    }
}
