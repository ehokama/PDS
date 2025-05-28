package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Pedidos.OrdenDeCompra;

public class Ventas implements HandlerEtapa, IAreaObserver {
    
    private HandlerEtapa siguienteHandler;


    public Ventas() {
        this.siguienteHandler = new Cobranzas(); // por defecto la proxima area es cobranzas
    }

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Ventas recibió una actualización del pedido #" + ordenDeCompra.getNumeroPedido());
        if (ordenDeCompra.getAreaActual() instanceof Ventas) {
            this.handle(ordenDeCompra);
        }
    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        this.siguienteHandler = handler;
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Ventas está procesando el pedido #" + ordenDeCompra.getNumeroPedido());
        // Aca iria la logica, validaciones, etc
        // Dsp, se pasa al siguiente handler en la cadena
        if (siguienteHandler != null) {
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler); // actualizo el area casteando
            siguienteHandler.handle(ordenDeCompra); // se continua la cadena
        }
    }
}
