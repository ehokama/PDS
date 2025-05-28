package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Estados.EstadoPosiblesPedido;
import backend.Pedidos.OrdenDeCompra;

public class Entrega extends Area implements HandlerEtapa, IAreaObserver {
    private static Entrega instancia = null;
    private HandlerEtapa siguienteHandler;

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Entrega recibió una actualización del pedido #" + ordenDeCompra.getNumeroPedido());
        if (ordenDeCompra.getAreaActual() instanceof Entrega) {
            this.handle(ordenDeCompra);
        }
    }

    public Entrega() {
        setNombre("Entrega");

    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        this.siguienteHandler = Seguimiento.getInstancia();
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        System.out.println("Entrega está procesando el pedido #" + ordenDeCompra.getNumeroPedido());

        if (siguienteHandler != null) {
            ordenDeCompra.setEstadoDelPedido(EstadoPosiblesPedido.FINALIZADO);
            System.out.println("Entrega pasa el pedido a la siguiente área");
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler);
            siguienteHandler.handle(ordenDeCompra);
        } else {
            System.out.println("Entrega finalizó el flujo de procesamiento para el pedido #" + ordenDeCompra.getNumeroPedido());
        }
    }

    // Método estático para obtener la instancia
    public static Entrega getInstancia() {
        if (instancia == null) {
            instancia = new Entrega();
        }
        return instancia;
    }
}
