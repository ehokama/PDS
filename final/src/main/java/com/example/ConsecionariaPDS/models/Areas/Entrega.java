package com.example.ConsecionariaPDS.models.Areas;

import com.example.ConsecionariaPDS.models.Areas.Handler.IAreaHandler;
import com.example.ConsecionariaPDS.models.Areas.Observer.IAreaObserver;
import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.*;


// Ventas → Cobranzas → Impuestos → Embarque → Logística → Entrega → Seguimiento
// Las areas tambien implementan el Singleton aparte del Observer y del Chain of Responsability

@Entity
@DiscriminatorValue("Entrega")
public class Entrega extends Area implements IAreaHandler, IAreaObserver {
    private static Entrega instancia = null;
    @Transient
    private IAreaHandler siguienteHandler;

    private Entrega() {
        setNombre("Cobranzas");
        this.siguienteHandler =  Seguimiento.getInstancia();
    }
    
    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Entrega recibió una actualización del pedido #" + ordenDeCompra.getNumeroDeOrden());
        if (ordenDeCompra.getAreaActual() instanceof Entrega) {
            this.handle(ordenDeCompra);
        }else{
            System.out.println("Entrega no puede procesar la orden.");
        }
    }

    @Override
    public void setNextHandler(IAreaHandler handler) {
        this.siguienteHandler = handler;
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
    
        System.out.println("Entrega está procesando el pedido #" + ordenDeCompra.getNumeroDeOrden());   
        ordenDeCompra.agregarEstadoAlHistorial();

        try {
            Thread.sleep(27); // 1 milisegundo de espera
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // logica de procesamiento...

        if (siguienteHandler != null) {        // Si hay un sig handler se pasa el pedido
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            ordenDeCompra.reiniciarEstadoOrden((Area) siguienteHandler);
            System.out.println("Entrega pasa el pedido a la siguiente área");
            siguienteHandler.handle(ordenDeCompra); 
        } else {
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            System.out.println("Entrega finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroDeOrden());
            ordenDeCompra.getVehiculo().setVendido();
            ordenDeCompra.aprobar();
        }
    }

    public static Entrega getInstancia() {
        if (instancia == null) {
            instancia = new Entrega();
        }
        return instancia;
    }
    
}