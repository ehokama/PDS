package com.example.demo.models.Areas.PatronHandler;

import com.example.demo.models.Pedidos.OrdenDeCompra;

public interface HandlerEtapa {
    void setNextHandler(HandlerEtapa handler);
    void handle(OrdenDeCompra ordenDeCompra);
}
