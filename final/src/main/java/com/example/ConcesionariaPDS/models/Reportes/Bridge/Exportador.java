package com.example.ConcesionariaPDS.models.Reportes.Bridge;

import java.util.List;

import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;

public interface Exportador {
    void exportar(List<OrdenDeCompra> ordenes);
}