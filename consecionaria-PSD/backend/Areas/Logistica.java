package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Pedidos.OrdenDeCompra;

public class Logistica implements HandlerEtapa, IAreaObserver {
    private HandlerEtapa siguienteHandler;

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Logística recibió una actualización del pedido #" + ordenDeCompra.getNumeroPedido());
        if (ordenDeCompra.getAreaActual() instanceof Logistica) {
            this.handle(ordenDeCompra);
        }
    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        this.siguienteHandler = new Entrega();
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Logística está procesando el pedido #" + ordenDeCompra.getNumeroPedido());

        if (siguienteHandler != null) {
            System.out.println("Logística pasa el pedido a la siguiente área");
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler);
            siguienteHandler.handle(ordenDeCompra);
        } else {
            System.out.println("Logística finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroPedido());
        }
    }
}
