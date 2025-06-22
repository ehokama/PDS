package com.example.ConcesionariaPDS.models.Facades;

import java.time.LocalDateTime;
import java.util.List;

import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;
import com.example.ConcesionariaPDS.models.Reportes.Bridge.Exportador;
import com.example.ConcesionariaPDS.models.Reportes.Bridge.ExportadorCSV;
import com.example.ConcesionariaPDS.models.Reportes.Bridge.ExportadorTXT;
import com.example.ConcesionariaPDS.models.Reportes.Bridge.ReporteDeOrdenes;
import com.example.ConcesionariaPDS.models.Reportes.Bridge.ReportePorEstado;
import com.example.ConcesionariaPDS.models.Reportes.Bridge.ReportePorFecha;



public class FacadeReportes {
    public void generarReportePorFecha(List<OrdenDeCompra> ordenes, LocalDateTime desde, LocalDateTime hasta, String formato) { // "  formato: csv o txt"
        Exportador exportador = seleccionarExportador(formato);
        ReporteDeOrdenes reporte = new ReportePorFecha(exportador, desde, hasta);
        reporte.generarReporte(ordenes);
    }

    public void generarReportePorEstado(List<OrdenDeCompra> ordenes, String estado, String formato) { // " estado: PENDIENTE CANCELADA O APROBADA | formato: CSV o TXT "
        Exportador exportador = seleccionarExportador(formato);
        ReporteDeOrdenes reporte = new ReportePorEstado(exportador, estado);
        reporte.generarReporte(ordenes);
    }

    private Exportador seleccionarExportador(String formato) {
        return switch (formato.toUpperCase()) {
            case "TXT" -> new ExportadorTXT();
            case "CSV" -> new ExportadorCSV();
            default -> throw new IllegalArgumentException("Formato no soportado: " + formato);
        };
    }
}
