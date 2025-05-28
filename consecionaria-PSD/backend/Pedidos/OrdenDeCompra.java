package backend.Pedidos;

import java.sql.Date;
import java.util.List;

import backend.Areas.DatosDeFacturacion;
import backend.Areas.Ventas;
import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Areas.PatronIObserver.ISubject;
import backend.Estados.EstadoPedido;
import backend.MetodoDePago.MetodoDePago;
import backend.Usuarios.Usuario;
import backend.Vehiculos.TiposVehiculos.Vehiculo;

public class OrdenDeCompra implements ISubject{
    private int numeroPedido;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private IAreaObserver areaActual;
    private DatosDeFacturacion facturacion;
    private Date fechaCreacion;
    private EstadoPedido estado;
    private MetodoDePago metodoDePago;
    private List<EstadoPedido> historialDeEstados;
    private List<IAreaObserver>observadores;
    private Usuario vendedor;
    private int costoTotal;
    private String nombreConsecionaria;
    private int cuitConsecionaria;
    private HandlerEtapa primerHandler;

    public void cambiarEstado(){

    }

    public void notificar(){
        for (IAreaObserver observer : observadores) {
            observer.update(this);
        }
    }


    public double calcularCostoTotal(){
        double precioBase = vehiculo.getPrecio();
        double impuestos = vehiculo.calcularImpuestos();
        double adicionales = vehiculo.calcularAdicionales(); // por garantías, accesorios, etc.
        return (precioBase + impuestos + adicionales);
}
    

    @Override
    public void registrar(IAreaObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrar'");
    }

    @Override
    public void eliminar(IAreaObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
