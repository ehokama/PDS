package backend.Areas.PatronIObserver;

import backend.Pedidos.OrdenDeCompra;

public interface IAreaObserver {
    void update(OrdenDeCompra ordenDeCompra);
}
