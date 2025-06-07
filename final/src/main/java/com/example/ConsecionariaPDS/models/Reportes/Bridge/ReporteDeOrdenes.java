package com.example.ConsecionariaPDS.models.Reportes.Bridge;

import java.util.List;

import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;

public abstract class ReporteDeOrdenes {
    protected Exportador exportador;

    public ReporteDeOrdenes(Exportador exportador) {
        this.exportador = exportador;
    }

    public abstract void generarReporte(List<OrdenDeCompra> pedidos);
}

