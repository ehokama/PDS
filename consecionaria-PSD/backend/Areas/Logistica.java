package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Estados.EstadoPosiblesPedido;
import backend.Pedidos.OrdenDeCompra;

public class Logistica extends Area implements HandlerEtapa, IAreaObserver {
    private static Logistica instancia = null;
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
        this.siguienteHandler = Entrega.getInstancia();
    }

    public Logistica() {
    setNombre("Logistica");

    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Logística está procesando el pedido #" + ordenDeCompra.getNumeroPedido());

        if (siguienteHandler != null) {
            ordenDeCompra.setEstadoDelPedido(EstadoPosiblesPedido.FINALIZADO);
            System.out.println("Logística pasa el pedido a la siguiente área");
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler);
            siguienteHandler.handle(ordenDeCompra);
        } else {
            System.out.println("Logística finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroPedido());
        }
    }

    // Método estático para obtener la instancia
    public static Logistica getInstancia() {
        if (instancia == null) {
            instancia = new Logistica();
        }
        return instancia;
    }
}
