package backend.Pedidos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import backend.Estados.EstadoPosiblesPedido;
import backend.MetodoDePago.MetodoDePago;

public class ReporteDePedidos {
    private List<OrdenDeCompra> pedidos;

    public ReporteDePedidos(List<OrdenDeCompra> pedidos) {
        this.pedidos = pedidos;
    }

    public void exportarReporteAArchivo(String nombreArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (OrdenDeCompra orden : pedidos) {
                writer.write("Pedido #" + orden.getNumeroPedido() + "," +
                             orden.getFechaCreacion() + "," +
                             orden.getEstado() + "," +
                             orden.getCostoTotal() + "," +
                             orden.getMetodoDePago().getNombre());
                writer.newLine();
            }
        }
    }

    public void generarReporteCompleto() {
        System.out.println("=== Reporte Completo de Pedidos ===");
        for (OrdenDeCompra orden : pedidos) {
            System.out.println("Pedido #" + orden.getNumeroPedido() 
                + " - Estado: " + orden.getEstado() 
                + " - Fecha: " + orden.getFechaCreacion() 
                + " - Total: $" + orden.getCostoTotal());
            System.out.println("Concesionaria: " + orden.getNombreConsecionaria());
            System.out.println("CUIT: " + orden.getCuitConsecionaria());
        }
    }

    public void generarReportePorEstado(EstadoPosiblesPedido estadoBuscado) {
        System.out.println("=== Reporte de Pedidos en Estado: " + estadoBuscado + " ===");

        pedidos.stream()
            .filter(p -> p.getEstado().getEstado().equals(estadoBuscado))
            .forEach(orden -> {
                System.out.println("Pedido #" + orden.getNumeroPedido() 
                    + " - Fecha: " + orden.getFechaCreacion() 
                    + " - Total: $" + orden.getCostoTotal());
                System.out.println("Concesionaria: " + orden.getNombreConsecionaria());
                System.out.println("CUIT: " + orden.getCuitConsecionaria());
            });
    }


    
    public void generarReportePorFecha(Date desde, Date hasta) {
        System.out.println("=== Reporte de Pedidos desde " + desde + " hasta " + hasta + " ===");

        pedidos.stream()
            .filter(orden -> !orden.getFechaCreacion().before(desde) && !orden.getFechaCreacion().after(hasta))
            .sorted((o1, o2) -> o1.getFechaCreacion().compareTo(o2.getFechaCreacion()))
            .forEach(orden -> {
                System.out.println("Pedido #" + orden.getNumeroPedido()
                    + " - Fecha: " + orden.getFechaCreacion()
                    + " - Estado: " + orden.getEstado()
                    + " - Total: $" + orden.getCostoTotal());
                System.out.println("Concesionaria: " + orden.getNombreConsecionaria());
                System.out.println("CUIT: " + orden.getCuitConsecionaria());
            });
    }

    public int calcularTotalGeneral() {
        int total = pedidos.stream()
            .mapToInt(OrdenDeCompra::getCostoTotal)
            .sum();
        System.out.println("Total General: $" + total);
        return total;
    }

    public int calcularTotalPorMetodoDePago(MetodoDePago metodoDePago) {
        int total = pedidos.stream()
            .filter(p -> p.getMetodoDePago().equals(metodoDePago))
            .mapToInt(OrdenDeCompra::getCostoTotal)
            .sum();
        System.out.println("Total para método de pago " + metodoDePago.getNombre() + ": $" + total);
        return total;
    }

    public void imprimirDetallePedido(OrdenDeCompra orden) {
        System.out.println("=== Detalle Pedido #" + orden.getNumeroPedido() + " ===");
        System.out.println("Fecha Creación: " + orden.getFechaCreacion());
        System.out.println("Estado: " + orden.getEstado());
        System.out.println("Método de Pago: " + orden.getMetodoDePago().getNombre());
        System.out.println("Costo Total: $" + orden.getCostoTotal());
        System.out.println("Usuario: " + (orden.getUsuario() != null ? orden.getUsuario().getNombre() : "N/A"));
        System.out.println("Vehículo: " + (orden.getVehiculo() != null ? orden.getVehiculo().getModelo() : "N/A"));
        System.out.println("Concesionaria: " + orden.getNombreConsecionaria());
        System.out.println("CUIT: " + orden.getCuitConsecionaria());
        // Si tienes más atributos que quieras mostrar, los agregamos acá
    }
}
