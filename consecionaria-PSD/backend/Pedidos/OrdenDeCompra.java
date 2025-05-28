package backend.Pedidos;

import java.util.List;

import backend.Vehiculos.TiposVehiculos.Vehiculo;

public class OrdenDeCompra {
    private int numeroPedido;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private IAreaObserver areaActual;
    private Facturacion facturacion;
    private Date fechaCreacion;
    private EstadoPedido estado;
    private MetodoDePago metodoDePago;
    private historialDeEstados List<HistorialEstado>;
    private List<IObserver>observadores;
    private Usuario vendedor;
    private int costoTotal;
    private String nombreConsecionaria;
    private int cuitConsecionaria;
    private IHandler primerHandler;

    public void cambiarEstado(){

    }

    public void registrar(){

    }

    public void notificar(){

    }

    public void eliminar(){

    }
    public String gestionDeImpuestos(){
        
    }

}
