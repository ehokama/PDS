package Ordenes.State;

import Ordenes.OrdenDeCompra;

public class Cancelada extends StateOrden {
   public Cancelada(OrdenDeCompra orden) {
        super(orden);
    }

    @Override
    public void cancelar() {
        System.out.println("La orden ya está cancelada.");
    }

    @Override
    public void aprobar() {
        System.out.println("No se puede aprobar una orden cancelada.");
    }

    @Override
    public String getNombre() {
        return "CANCELADA";
    }
}