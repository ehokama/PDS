package com.example.ConcesionariaPDS.models.Areas;

import jakarta.persistence.DiscriminatorValue;

import com.example.ConcesionariaPDS.models.Areas.Handler.IAreaHandler;
import com.example.ConcesionariaPDS.models.Areas.Observer.IAreaObserver;
import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;

import jakarta.persistence.*;

// Ventas → Cobranzas → Impuestos → Embarque → Logística → Entrega → Seguimiento
// Las areas tambien implementan el Singleton aparte del Observer y del Chain of Responsability

@Entity
@DiscriminatorValue("Ventas")
public class Ventas extends Area implements IAreaHandler, IAreaObserver {
    private static Ventas instancia = null;
    @Transient
    private IAreaHandler siguienteHandler;

    private Ventas() {
        setNombre("Ventas");
        this.siguienteHandler =  Cobranzas.getInstancia();
    }

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Ventas recibió una actualización del pedido #" + ordenDeCompra.getNumeroDeOrden());
        if (ordenDeCompra.getAreaActual() instanceof Ventas) { // si el area correspondiente de la orden coincide con esta la toma sino no
            this.handle(ordenDeCompra);
        }else{
            System.out.println("Ventas no puede procesar la orden.");
        }
    }

    @Override
    public void setNextHandler(IAreaHandler handler) {
        this.siguienteHandler = handler;
    }


    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
    
        System.out.println("Ventas está procesando el pedido #" + ordenDeCompra.getNumeroDeOrden());   
        ordenDeCompra.agregarEstadoAlHistorial();

        // logica de procesamiento...

        if (siguienteHandler != null) {        // Si hay un sig handler se pasa el pedido
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            ordenDeCompra.reiniciarEstadoOrden((Area) siguienteHandler);
            System.out.println("Ventas pasa el pedido a la siguiente área");
            siguienteHandler.handle(ordenDeCompra); 
        } else {
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            System.out.println("Ventas finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroDeOrden());
            ordenDeCompra.getVehiculo().setVendido();
            ordenDeCompra.aprobar();
        }
    }

    
    public static Ventas getInstancia() {
        if (instancia == null) {
            instancia = new Ventas();
        }
        return instancia;
    }

    
}