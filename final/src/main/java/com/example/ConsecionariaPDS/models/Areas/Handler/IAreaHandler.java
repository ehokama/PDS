package com.example.ConsecionariaPDS.models.Areas.Handler;

import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;

public interface IAreaHandler {
    void setNextHandler(IAreaHandler handler);
    void handle(OrdenDeCompra ordenDeCompra);
}