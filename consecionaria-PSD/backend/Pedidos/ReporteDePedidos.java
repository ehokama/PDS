package backend.Pedidos;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import backend.Estados.EstadoPedido;
import backend.MetodoDePago.MetodoDePago;

public class ReporteDePedidos {
    private List<OrdenDeCompra> pedidos;

    public ReporteDePedidos(List<OrdenDeCompra> pedidos) {
        this.pedidos = pedidos;
    }

    public void generarReporteCompleto() {
        System.out.println("=== Reporte Completo de Pedidos ===");
        for (OrdenDeCompra orden : pedidos) {
            System.out.println("Pedido #" + orden.getNumeroPedido() 
                + " - Estado: " + orden.getEstado() 
                + " - Fecha: " + orden.getFechaCreacion() 
                + " - Total: $" + orden.getCostoTotal());
        }
    }

    public void generarReportePorEstado() {
        System.out.println("=== Reporte de Pedidos por Estado ===");
        Map<EstadoPedido, List<OrdenDeCompra>> pedidosPorEstado = pedidos.stream()
            .collect(Collectors.groupingBy(OrdenDeCompra::getEstado));

        for (EstadoPedido estado : pedidosPorEstado.keySet()) {
            System.out.println("Estado: " + estado);
            for (OrdenDeCompra orden : pedidosPorEstado.get(estado)) {
                System.out.println("  Pedido #" + orden.getNumeroPedido() 
                    + " - Fecha: " + orden.getFechaCreacion() 
                    + " - Total: $" + orden.getCostoTotal());
            }
        }
    }

    public void generarReportePorFecha() {
        System.out.println("=== Reporte de Pedidos por Fecha ===");
        pedidos.stream()
            .sorted((o1, o2) -> o1.getFechaCreacion().compareTo(o2.getFechaCreacion()))
            .forEach(orden -> System.out.println(
                "Pedido #" + orden.getNumeroPedido()
                + " - Fecha: " + orden.getFechaCreacion()
                + " - Estado: " + orden.getEstado()
                + " - Total: $" + orden.getCostoTotal()));
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
        // Si tienes más atributos que quieras mostrar, los agregamos acá
    }
}
