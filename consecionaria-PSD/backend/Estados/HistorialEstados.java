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
}
