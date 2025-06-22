package com.example.ConcesionariaPDS.models.Ordenes.State;

import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;


public class Aprobada extends StateOrden {

    public Aprobada(OrdenDeCompra orden) {
        super(orden);
    }

    @Override
    public void cancelar() {
        System.out.println("No se puede cancelar una orden ya aprobada.");
    }

    @Override
    public void aprobar() {
        System.out.println("La orden ya está aprobada.");
    }

    @Override
    public String getNombre() {
        return "APROBADA";
    }
}
