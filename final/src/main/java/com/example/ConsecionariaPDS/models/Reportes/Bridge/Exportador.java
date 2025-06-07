package com.example.ConsecionariaPDS.models.Reportes.Bridge;

import java.util.List;

import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;

public interface Exportador {
    void exportar(List<OrdenDeCompra> ordenes);
}