package com.example.ConcesionariaPDS.models.Areas.Observer;

import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;

public interface IAreaObserver {
    void update(OrdenDeCompra ordenDeCompra);
}