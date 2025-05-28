package backend.Areas;

import backend.Pedidos.OrdenDeCompra;

public interface IAreaObserver {
    void update(OrdenDeCompra ordenDeCompra);
}
