package com.example.ConcesionariaPDS.models.Areas.Handler;

import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;

public interface IAreaHandler {
    void setNextHandler(IAreaHandler handler);
    void handle(OrdenDeCompra ordenDeCompra);
}