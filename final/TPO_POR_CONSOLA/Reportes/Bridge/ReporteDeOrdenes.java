package Reportes.Bridge;

import java.util.List;

import Ordenes.OrdenDeCompra;

public abstract class ReporteDeOrdenes {
    protected Exportador exportador;

    public ReporteDeOrdenes(Exportador exportador) {
        this.exportador = exportador;
    }

    public abstract void generarReporte(List<OrdenDeCompra> pedidos);
}

