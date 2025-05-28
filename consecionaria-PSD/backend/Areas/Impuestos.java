package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Pedidos.OrdenDeCompra;

public class Impuestos implements HandlerEtapa, IAreaObserver {

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
        this.siguienteHandler = new Embarque();
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Impuestos está procesando el pedido #" + ordenDeCompra.getNumeroPedido());

        // logica para calculos

        if (siguienteHandler != null) {
            System.out.println("Impuestos pasa el pedido a la siguiente área");
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler); 
            siguienteHandler.handle(ordenDeCompra); 
        } else {
            System.out.println("Impuestos finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroPedido());
        }
    }
}
