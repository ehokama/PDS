package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Pedidos.OrdenDeCompra;

public class Cobranzas implements HandlerEtapa, IAreaObserver {

    private HandlerEtapa siguienteHandler;

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Cobranzas recibió una actualización del pedido #" + ordenDeCompra.getNumeroPedido());
        if (ordenDeCompra.getAreaActual() instanceof Cobranzas) {
            this.handle(ordenDeCompra);
        }
    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        this.siguienteHandler = new Impuestos(); // establecemos el nextHandler por defecto
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Cobranzas está procesando el pedido #" + ordenDeCompra.getNumeroPedido());
        // logica de procesamiento...
        // Si hay un sig handler se pasa el pedido
        if (siguienteHandler != null) {
            System.out.println("Cobranzas pasa el pedido a la siguiente área");
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler); 
            siguienteHandler.handle(ordenDeCompra); 
        } else {
            System.out.println("Cobranzas finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroPedido());
        }
    }
}
