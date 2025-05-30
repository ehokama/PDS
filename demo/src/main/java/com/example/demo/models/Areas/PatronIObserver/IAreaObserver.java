package com.example.demo.models.Areas.PatronIObserver;


import com.example.demo.models.Pedidos.OrdenDeCompra;

public interface IAreaObserver {
    void update(OrdenDeCompra ordenDeCompra);
    String getNombre();
}
