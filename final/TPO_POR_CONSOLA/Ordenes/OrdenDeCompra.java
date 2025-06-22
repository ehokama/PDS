package Ordenes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Areas.Area;
import Areas.Consecionaria;
import Areas.Ventas;
import Areas.Observer.IAreaObserver;
import Areas.Observer.ISubject;
import MetodosDePago.MetodoDePago;
import Usuarios.Usuario;
import Vehiculos.Entidades.Vehiculo;
import Ordenes.State.StateOrden;
import Ordenes.State.Pendiente;

// no implemente el memento en el historial de estadopedido ya que no se pedia la capacidad de deshacer/revertir/restaurar estados en la consigna, sino que unicamente es una lista de copias inmutables


public class OrdenDeCompra implements ISubject{
    private int numeroDeOrden;

    private Usuario comprador;
    private Usuario vendedor;
    private LocalDateTime fechaCreacion;

    private Vehiculo vehiculo;
    private MetodoDePago metodoDePago;

    private DatosDeFacturacion datosFacturacion;

    private Area areaActual;
    private List<IAreaObserver>observadores;
    private EstadoAreaPedido estadoAreaActual;
    private List<EstadoAreaPedido> historialDeEstados;
    private StateOrden stateOrden;

    private String nombreConsecionaria;
    private String cuitConsecionaria;

    private double costoTotal;

    public void agregarEstadoAlHistorial() {
        historialDeEstados.add(this.estadoAreaActual.clonar());
    }

    public OrdenDeCompra(int numeroDeOrden, Vehiculo vehiculo, Usuario vendedor, Usuario comprador, DatosDeFacturacion datosFacturacion, MetodoDePago metodoDePago) {
        this.numeroDeOrden = numeroDeOrden;

        this.vendedor= vendedor;
        this.comprador = comprador;
        this.datosFacturacion = datosFacturacion;

        this.nombreConsecionaria = Consecionaria.getNombre() ;
        this.cuitConsecionaria = Consecionaria.getCuit() ;

        this.vehiculo = vehiculo;
        this.costoTotal = vehiculo.calcularPrecioFinal() + metodoDePago.calcularRecargo(vehiculo.calcularPrecioFinal());

        this.fechaCreacion = LocalDateTime.now();

        this.observadores = new ArrayList<>();
        this.historialDeEstados = new ArrayList<>();
        this.areaActual = Ventas.getInstancia();
        this.estadoAreaActual  = new EstadoAreaPedido(numeroDeOrden, areaActual);
        this.stateOrden = new Pendiente(this);
    }

    @Override
    public void registrar(IAreaObserver observer) {
        observadores.add(observer);
    }

    @Override
    public void eliminar(IAreaObserver observer) {
        observadores.remove(observer);
    }

    @Override
    public void notificar() {
        this.vehiculo.setProcesoDeVenta();
        Ventas.getInstancia().update(this);
    }

    

    public Usuario getComprador() {
        return comprador;
    }

    public String getDniComprador() {
        return comprador.getDni();
    }

    public String getDniVendedor() {
        return vendedor.getDni();
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public DatosDeFacturacion getDatosFacturacion() {
        return datosFacturacion;
    }

    public String getNombreConsecionaria() {
        return nombreConsecionaria;
    }

    public String getCuitConsecionaria() {
        return cuitConsecionaria;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public Area getAreaActual() {
        return areaActual;
    }

    public void setAreaActual(Area areaActual) {
        this.areaActual = areaActual;
    }

    public int getNumeroDeOrden() {
        return numeroDeOrden;
    }
    
    public  EstadoAreaPedido getEstadoAreaActual(){
        return estadoAreaActual;
    } 
    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }


    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void marcarFinalizadoEstadoActualDeOrden(){
        estadoAreaActual.finalizarEstadoPedido();
    }

    public List<EstadoAreaPedido> getHistorialDeEstados() {
        return historialDeEstados;
    }

	public void reiniciarEstadoOrden(Area siguienteHandler) {
		estadoAreaActual = new EstadoAreaPedido(numeroDeOrden, siguienteHandler);
	}

    public StateOrden getStateOrden() {
        return stateOrden;
    }

    public void setStateOrden(StateOrden stateOrden) {
        this.stateOrden = stateOrden;
    }

    public void cancelar(){
        this.stateOrden.cancelar();
    }

    public void aprobar(){
        this.stateOrden.aprobar();
    }

    @Override
    public String toString() {
        return "OrdenDeCompra [numeroDeOrden=" + numeroDeOrden + ", comprador=" + comprador + ", vendedor=" + vendedor
                + ", fechaCreacion=" + fechaCreacion + ", vehiculo=" + vehiculo + ", metodoDePago=" + metodoDePago
                + ", datosFacturacion=" + datosFacturacion + ", areaActual=" + areaActual + ", observadores="
                + observadores + ", estadoAreaActual=" + estadoAreaActual + ", historialDeEstados=" + historialDeEstados
                + ", stateOrden=" + stateOrden + ", nombreConsecionaria=" + nombreConsecionaria + ", cuitConsecionaria="
                + cuitConsecionaria + ", costoTotal=" + costoTotal + "]";
    }

}
