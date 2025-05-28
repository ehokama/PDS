package backend.Areas.PatronHandler;

import backend.Pedidos.OrdenDeCompra;

public interface HandlerEtapa {
    void setNextHandler(HandlerEtapa handler);
    void handle(OrdenDeCompra ordenDeCompra);
}
