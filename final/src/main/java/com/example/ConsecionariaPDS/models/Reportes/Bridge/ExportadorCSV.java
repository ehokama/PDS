package com.example.ConsecionariaPDS.models.Reportes.Bridge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;


public class ExportadorCSV implements Exportador {
    @Override
    public void exportar(List<OrdenDeCompra> ordenes) {
        try (PrintWriter writer = new PrintWriter("reporte.csv")) {
            writer.println("ID,Fecha,Estado"); // encabezado
            for (OrdenDeCompra o : ordenes) {
                writer.println(o.getNumeroDeOrden() + "," + o.getFechaCreacion() + "," + o.getEstadoAreaActual());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
