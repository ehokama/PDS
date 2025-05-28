package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Pedidos.OrdenDeCompra;

public class Entrega implements HandlerEtapa, IAreaObserver {
    private HandlerEtapa siguienteHandler;

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Entrega recibió una actualización del pedido #" + ordenDeCompra.getNumeroPedido());
        if (ordenDeCompra.getAreaActual() instanceof Entrega) {
            this.handle(ordenDeCompra);
        }
    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        this.siguienteHandler = new Seguimiento();
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Entrega está procesando el pedido #" + ordenDeCompra.getNumeroPedido());

        if (siguienteHandler != null) {
            System.out.println("Entrega pasa el pedido a la siguiente área");
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler);
            siguienteHandler.handle(ordenDeCompra);
        } else {
            System.out.println("Entrega finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroPedido());
        }
    }
}
