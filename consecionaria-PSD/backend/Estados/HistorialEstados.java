package backend.Estados;

import java.util.List;

public class HistorialEstados {
    private List<EstadoPedido> historial;
    
    public void añadirEstado(EstadoPedido estadoPedido){
        historial.add(estadoPedido);
    }

    public List<EstadoPedido> getEstadoPedidos(){
        return historial;
    }

    public void mostrarHistorialDeEstados() {
        for (EstadoPedido ep : historial) {
            System.out.println("Estado: " + ep.getEstado() + 
                               ", Inicio: " + ep.getFechaInicio() +
                               ", Fin: " + ep.getFechaFinalizacion() + 
                               ", Área: " + ep.getAreaResponsable().getClass().getSimpleName());
        }
    }

}
