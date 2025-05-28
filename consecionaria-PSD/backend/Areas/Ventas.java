package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Estados.EstadoPosiblesPedido;
import backend.Pedidos.OrdenDeCompra;

public class Ventas extends Area implements HandlerEtapa, IAreaObserver {
    private static Ventas instancia = null;
    private HandlerEtapa siguienteHandler;


    public Ventas() {
        setNombre("Ventas");
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
            ordenDeCompra.setEstadoDelPedido(EstadoPosiblesPedido.FINALIZADO);
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler); // actualizo el area casteando
            siguienteHandler.handle(ordenDeCompra); // se continua la cadena
        }
    }

    // Método estático para obtener la instancia
    public static Ventas getInstancia() {
        if (instancia == null) {
            instancia = new Ventas();
        }
        return instancia;
    }
}
