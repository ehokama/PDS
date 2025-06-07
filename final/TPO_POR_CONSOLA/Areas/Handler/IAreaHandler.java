package Areas.Handler;

import Ordenes.OrdenDeCompra;

public interface IAreaHandler {
    void setNextHandler(IAreaHandler handler);
    void handle(OrdenDeCompra ordenDeCompra);
}