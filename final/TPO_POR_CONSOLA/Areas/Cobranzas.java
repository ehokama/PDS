package Areas;

import Areas.Handler.IAreaHandler;
import Areas.Observer.IAreaObserver;
import Ordenes.OrdenDeCompra;


// Ventas → Cobranzas → Impuestos → Embarque → Logística → Entrega → Seguimiento
// Las areas tambien implementan el Singleton aparte del Observer y del Chain of Responsability
public class Cobranzas extends Area implements IAreaHandler, IAreaObserver {
    private static Cobranzas instancia = null;
    private IAreaHandler siguienteHandler;

    private Cobranzas() {
        setNombre("Cobranzas");
        this.siguienteHandler =  Impuestos.getInstancia();
    }
    
    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Cobranzas recibió una actualización del pedido #" + ordenDeCompra.getNumeroDeOrden());
        if (ordenDeCompra.getAreaActual() instanceof Cobranzas) {
            this.handle(ordenDeCompra);
        }else{
            System.out.println("Cobranzas no puede procesar la orden.");
        }
    }

    @Override
    public void setNextHandler(IAreaHandler handler) {
        this.siguienteHandler = handler;
    }


    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
    
        System.out.println("Cobranzas está procesando el pedido #" + ordenDeCompra.getNumeroDeOrden());   
        ordenDeCompra.agregarEstadoAlHistorial();

        // logica de procesamiento...
        try {
            Thread.sleep(33); // 1 milisegundo de espera
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (siguienteHandler != null) {        // Si hay un sig handler se pasa el pedido
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            ordenDeCompra.reiniciarEstadoOrden((Area) siguienteHandler);
            System.out.println("Cobranzas pasa el pedido a la siguiente área");
            siguienteHandler.handle(ordenDeCompra); 
        } else {
            ordenDeCompra.marcarFinalizadoEstadoActualDeOrden();
            ordenDeCompra.agregarEstadoAlHistorial();
            System.out.println("Cobranzas finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroDeOrden());
            ordenDeCompra.getVehiculo().setVendido();
            ordenDeCompra.aprobar();
        }
    }

    public static Cobranzas getInstancia() {
        if (instancia == null) {
            instancia = new Cobranzas();
        }
        return instancia;
    }
    
}