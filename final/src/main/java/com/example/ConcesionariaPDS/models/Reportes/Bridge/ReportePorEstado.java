package com.example.ConcesionariaPDS.models.Reportes.Bridge;

import java.util.List;

import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;

public class ReportePorEstado extends ReporteDeOrdenes {
    private String estadoBuscado;

    public ReportePorEstado(Exportador exportador, String estadoBuscado) {
        super(exportador);
        this.estadoBuscado = estadoBuscado;
    }

    @Override
    public void generarReporte(List<OrdenDeCompra> pedidos) {
        List<OrdenDeCompra> filtradas = pedidos.stream()
            .filter(o -> o.getStateOrden().getNombre().equalsIgnoreCase(estadoBuscado))
            .toList();

        exportador.exportar(filtradas);
    }
}