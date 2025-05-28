package backend.Areas;

import backend.Pedidos.OrdenDeCompra;

public interface HandlerEtapa {
    void setNextHandler(HandlerEtapa handler);
    void handle(OrdenDeCompra ordenDeCompra);
}
