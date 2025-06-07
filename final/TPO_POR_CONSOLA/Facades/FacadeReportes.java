package Facades;

import java.time.LocalDateTime;
import java.util.List;

import Reportes.Bridge.Exportador;
import Ordenes.OrdenDeCompra;
import Reportes.Bridge.ExportadorCSV;
import Reportes.Bridge.ExportadorTXT;
import Reportes.Bridge.ReporteDeOrdenes;
import Reportes.Bridge.ReportePorEstado;
import Reportes.Bridge.ReportePorFecha;

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
