package Reportes.Bridge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import Ordenes.OrdenDeCompra;

public class ExportadorTXT implements Exportador {
    @Override
    public void exportar(List<OrdenDeCompra> ordenes) {
        try (PrintWriter writer = new PrintWriter("reporte.txt")) {
            for (OrdenDeCompra o : ordenes) {

                writer.println("ID: " + o.getNumeroDeOrden() + " | Fecha: " + o.getFechaCreacion() + " | UltimoEstadoArea: " + o.getEstadoAreaActual()+ " | UltimoEstadoArea: " + o.getStateOrden());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
