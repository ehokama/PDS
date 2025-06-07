package com.example.ConsecionariaPDS.models.Areas.Observer;

import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;

public interface IAreaObserver {
    void update(OrdenDeCompra ordenDeCompra);
}