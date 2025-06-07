package com.example.ConsecionariaPDS.models.Areas;

import com.example.ConsecionariaPDS.models.Areas.Handler.IAreaHandler;
import com.example.ConsecionariaPDS.models.Areas.Observer.IAreaObserver;
import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;


// Ventas → Cobranzas → Impuestos → Embarque → Logística → Entrega → Seguimiento
// Las areas tambien implementan el Singleton aparte del Observer y del Chain of Responsability

public class Seguimiento extends Area implements IAreaHandler, IAreaObserver {
    private static Seguimiento instancia = null;
    private IAreaHandler siguienteHandler ;

    private Seguimiento() {
        setNombre("Seguimiento");
        this.siguienteHandler =  null;
    }

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Seguimiento recibió una actualización del pedido #" + ordenDeCompra.getNumeroDeOrden());
        if (ordenDeCompra.getAreaActual() instanceof Ventas) { // si el area correspondiente de la orden coincide con esta la toma sino no
            this.handle(ordenDeCompra);
        }else{
            System.out.println("Seguimiento no puede procesar la orden.");
        }
    }

    @Override
    public void setNextHandler(IAreaHandler handler) {
        this.siguienteHandler = handler;
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
    
        System.out.println("Seguimiento está procesando el pedido #" + ordenDeCompra.getNumeroDeOrden());   
        ordenDeCompra.agregarEstadoAlHistorial();
        try {
            Thread.sleep(85); // 1 milisegundo de espera
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // logica de procesamiento...

        if (siguienteHandler != null) {        // Si hay un sig handler se pasa el pedido
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            ordenDeCompra.reiniciarEstadoOrden((Area) siguienteHandler);
            System.out.println("Seguimiento pasa el pedido a la siguiente área");
            siguienteHandler.handle(ordenDeCompra); 
        } else {
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            System.out.println("Seguimiento finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroDeOrden());
            ordenDeCompra.getVehiculo().setVendido();
            ordenDeCompra.aprobar();
        }
    }

    
    public static Seguimiento getInstancia() {
        if (instancia == null) {
            instancia = new Seguimiento();
        }
        return instancia;
    }


}