package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Pedidos.OrdenDeCompra;

public class Seguimiento implements HandlerEtapa, IAreaObserver {
    private static Seguimiento instancia = null;
    private HandlerEtapa siguienteHandler; // En este caso, probablemente siempre sera null

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Seguimiento recibió una actualización del pedido #" + ordenDeCompra.getNumeroPedido());
        if (ordenDeCompra.getAreaActual() instanceof Seguimiento) {
            this.handle(ordenDeCompra);
        }
    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        // No hay siguiente etapa porque es la última
        this.siguienteHandler = null;
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Seguimiento está procesando el pedido #" + ordenDeCompra.getNumeroPedido());

        // logica

        if (siguienteHandler != null) {
            // En este caso no deberia entrar nunca
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler);
            siguienteHandler.handle(ordenDeCompra);
        } else {
            System.out.println("Seguimiento finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroPedido());
        }
    }
    // Método estático para obtener la instancia
    public static Seguimiento getInstancia() {
        if (instancia == null) {
            instancia = new Seguimiento();
        }
        return instancia;
    }
}
