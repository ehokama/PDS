package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Estados.EstadoPosiblesPedido;
import backend.Pedidos.OrdenDeCompra;

public class Impuestos extends Area implements HandlerEtapa, IAreaObserver {
    private static Impuestos instancia = null;
    private HandlerEtapa siguienteHandler;

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Impuestos recibió una actualización del pedido #" + ordenDeCompra.getNumeroPedido());
        if (ordenDeCompra.getAreaActual() instanceof Impuestos) {
            this.handle(ordenDeCompra);
        }
    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        this.siguienteHandler = Embarque.getInstancia();
    }

    public Impuestos() {
        setNombre("Impuestos");

    }
    
    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Impuestos está procesando el pedido #" + ordenDeCompra.getNumeroPedido());

        // logica para calculos

        if (siguienteHandler != null) {
            ordenDeCompra.setEstadoDelPedido(EstadoPosiblesPedido.FINALIZADO);
            System.out.println("Impuestos pasa el pedido a la siguiente área");
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler); 
            siguienteHandler.handle(ordenDeCompra); 
        } else {
            System.out.println("Impuestos finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroPedido());
        }
    }

    // Método estático para obtener la instancia
    public static Impuestos getInstancia() {
        if (instancia == null) {
            instancia = new Impuestos();
        }
        return instancia;
    }
}
