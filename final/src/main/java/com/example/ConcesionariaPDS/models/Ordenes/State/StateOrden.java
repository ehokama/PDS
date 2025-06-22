package com.example.ConcesionariaPDS.models.Ordenes.State;


import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;

// estado global del ciclo de vida de la orden en términos de negocio: por ejemplo, Pendiente, Aprobada, Cancelada. 
//Representa el patron State para controlar el comportamiento de la orden.

public abstract class StateOrden {
    protected OrdenDeCompra orden;

    public StateOrden(OrdenDeCompra orden) {
        this.orden = orden;
    }

    public abstract void cancelar();
    public abstract void aprobar();
    public abstract String getNombre();
}