package com.example.ConsecionariaPDS.models.Areas;

import com.example.ConsecionariaPDS.models.Areas.Handler.IAreaHandler;
import com.example.ConsecionariaPDS.models.Areas.Observer.IAreaObserver;
import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;


// Ventas → Cobranzas → Impuestos → Embarque → Logística → Entrega → Seguimiento
// Las areas tambien implementan el Singleton aparte del Observer y del Chain of Responsability

public class Logistica extends Area implements IAreaHandler, IAreaObserver {
    private static Logistica instancia = null;
    private IAreaHandler siguienteHandler ;

    private Logistica() {
        setNombre("Logistica");
        this.siguienteHandler =  Entrega.getInstancia();
    }

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Logistica recibió una actualización del pedido #" + ordenDeCompra.getNumeroDeOrden());
        if (ordenDeCompra.getAreaActual() instanceof Ventas) { // si el area correspondiente de la orden coincide con esta la toma sino no
            this.handle(ordenDeCompra);
        }else{
            System.out.println("Logistica no puede procesar la orden.");
        }
    }

    @Override
    public void setNextHandler(IAreaHandler handler) {
        this.siguienteHandler = handler;
    }


    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
    
        System.out.println("Logistica está procesando el pedido #" + ordenDeCompra.getNumeroDeOrden());   
        ordenDeCompra.agregarEstadoAlHistorial();

        try {
            Thread.sleep(111); // 1 milisegundo de espera
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // logica de procesamiento...

        if (siguienteHandler != null) {        // Si hay un sig handler se pasa el pedido
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            ordenDeCompra.reiniciarEstadoOrden((Area) siguienteHandler);
            System.out.println("Logistica pasa el pedido a la siguiente área");
            siguienteHandler.handle(ordenDeCompra); 
        } else {
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            System.out.println("Logistica finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroDeOrden());
            ordenDeCompra.getVehiculo().setVendido();
            ordenDeCompra.aprobar();}
    }
    
    public static Logistica getInstancia() {
        if (instancia == null) {
            instancia = new Logistica();
        }
        return instancia;
    }

}