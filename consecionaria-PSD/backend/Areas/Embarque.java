package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Pedidos.OrdenDeCompra;

public class Embarque implements HandlerEtapa, IAreaObserver {
    private static Embarque instancia = null;
    private HandlerEtapa siguienteHandler;

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Embarque recibió una actualización del pedido #" + ordenDeCompra.getNumeroPedido());
        if (ordenDeCompra.getAreaActual() instanceof Embarque) {
            this.handle(ordenDeCompra);
        }
    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        this.siguienteHandler = Logistica.getInstancia();
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Embarque está procesando el pedido #" + ordenDeCompra.getNumeroPedido());

        // logica de embarque

        if (siguienteHandler != null) {
            System.out.println("Embarque pasa el pedido a la siguiente área");
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler);
            siguienteHandler.handle(ordenDeCompra);
        } else {
            System.out.println("Embarque finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroPedido());
        }
    }

    // Método estático para obtener la instancia
    public static Embarque getInstancia() {
        if (instancia == null) {
            instancia = new Embarque();
        }
        return instancia;
    }
}
