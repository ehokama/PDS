package Reportes.Bridge;

import java.time.LocalDateTime;
import java.util.List;

import Ordenes.OrdenDeCompra;

public class ReportePorFecha extends ReporteDeOrdenes {
    private LocalDateTime desde;
    private LocalDateTime hasta;

    public ReportePorFecha(Exportador exportador, LocalDateTime desde, LocalDateTime hasta) {
        super(exportador);
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    public void generarReporte(List<OrdenDeCompra> pedidos) {
        List<OrdenDeCompra> filtradas = pedidos.stream()
            .filter(o -> o.getFechaCreacion().isAfter(desde) && o.getFechaCreacion().isBefore(hasta))
            .toList();
        exportador.exportar(filtradas);
    }
}