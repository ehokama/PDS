package com.example.ConcesionariaPDS.models.Areas;

import jakarta.persistence.DiscriminatorValue;

import com.example.ConcesionariaPDS.models.Areas.Handler.IAreaHandler;
import com.example.ConcesionariaPDS.models.Areas.Observer.IAreaObserver;
import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;

import jakarta.persistence.*;

// Ventas → Cobranzas → Impuestos → Embarque → Logística → Entrega → Seguimiento
// Las areas tambien implementan el Singleton aparte del Observer y del Chain of Responsability

@Entity
@DiscriminatorValue("Embarque")
public class Embarque extends Area implements IAreaHandler, IAreaObserver {
    private static Embarque instancia = null;
    @Transient
    private IAreaHandler siguienteHandler;

    private Embarque() {
        setNombre("Embarque");
        this.siguienteHandler =  Logistica.getInstancia();
    }

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Embarque recibió una actualización del pedido #" + ordenDeCompra.getNumeroDeOrden());
        if (ordenDeCompra.getAreaActual() instanceof Ventas) { // si el area correspondiente de la orden coincide con esta la toma sino no
            this.handle(ordenDeCompra);
        }else{
            System.out.println("Embarque no puede procesar la orden.");
        }
    }

    @Override
    public void setNextHandler(IAreaHandler handler) {
        this.siguienteHandler = handler;
    }


    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
    
        System.out.println("Embarque está procesando el pedido #" + ordenDeCompra.getNumeroDeOrden());   
        ordenDeCompra.agregarEstadoAlHistorial();
        try {
            Thread.sleep(56); // 1 milisegundo de espera
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // logica de procesamiento...

        if (siguienteHandler != null) {        // Si hay un sig handler se pasa el pedido
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            ordenDeCompra.reiniciarEstadoOrden((Area) siguienteHandler);
            System.out.println("Embarque pasa el pedido a la siguiente área");
            siguienteHandler.handle(ordenDeCompra); 
        } else {
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            System.out.println("Embarque finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroDeOrden());
            ordenDeCompra.getVehiculo().setVendido();
            ordenDeCompra.aprobar();
        }
    }
    
    public static Embarque getInstancia() {
        if (instancia == null) {
            instancia = new Embarque();
        }
        return instancia;
    }

}