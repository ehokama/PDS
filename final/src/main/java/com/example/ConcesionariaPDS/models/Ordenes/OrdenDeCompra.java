package com.example.ConcesionariaPDS.models.Ordenes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.ConcesionariaPDS.models.Areas.Area;
import com.example.ConcesionariaPDS.models.Areas.Consecionaria;
import com.example.ConcesionariaPDS.models.Areas.Ventas;
import com.example.ConcesionariaPDS.models.Areas.Observer.IAreaObserver;
import com.example.ConcesionariaPDS.models.Areas.Observer.ISubject;
import com.example.ConcesionariaPDS.models.MetodosDePago.MetodoDePago;
import com.example.ConcesionariaPDS.models.Ordenes.State.Pendiente;
import com.example.ConcesionariaPDS.models.Ordenes.State.StateOrden;
import com.example.ConcesionariaPDS.models.Usuarios.Usuario;
import com.example.ConcesionariaPDS.models.Vehiculos.Entidades.Vehiculo;

import jakarta.persistence.*;



// no implemente el memento en el historial de estadopedido ya que no se pedia la capacidad de deshacer/revertir/restaurar estados en la consigna, sino que unicamente es una lista de copias inmutables

@Entity
public class OrdenDeCompra implements ISubject{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroDeOrden;
    
    @ManyToOne
    private Usuario comprador;
    @ManyToOne
    private Usuario vendedor;

    private LocalDateTime fechaCreacion;

    @ManyToOne  
    private Vehiculo vehiculo;

    @ManyToOne
    private MetodoDePago metodoDePago;

    @Embedded
    private DatosDeFacturacion datosFacturacion;
    
    @Transient
    private Area areaActual;

    @Transient
    private List<IAreaObserver>observadores;

    @Transient
    private EstadoAreaPedido estadoAreaActual;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstadoAreaPedido> historialDeEstados;
    
    @Transient
    private StateOrden stateOrden;


    private String nombreConsecionaria;
    private String cuitConsecionaria;
    private double costoTotal;

    public void agregarEstadoAlHistorial() {
        historialDeEstados.add(this.estadoAreaActual.clonar());
    }

    public OrdenDeCompra(){
        
    }

    public OrdenDeCompra(int numeroDeOrden, Vehiculo vehiculo, Usuario vendedor, Usuario comprador, DatosDeFacturacion datosFacturacion, MetodoDePago metodoDePago) {
        this.numeroDeOrden = numeroDeOrden;

        this.vendedor= vendedor;
        this.comprador = comprador;
        this.datosFacturacion = datosFacturacion;
        this.metodoDePago = metodoDePago;
        
        this.nombreConsecionaria = Consecionaria.getNombre() ;
        this.cuitConsecionaria = Consecionaria.getCuit() ;

        this.vehiculo = vehiculo;
        this.costoTotal = vehiculo.calcularPrecioFinal() + metodoDePago.calcularRecargo(vehiculo.calcularPrecioFinal());

        this.fechaCreacion = LocalDateTime.now();

        this.observadores = new ArrayList<>();
        this.historialDeEstados = new ArrayList<>();
        this.estadoAreaActual  = new EstadoAreaPedido(areaActual);
        this.areaActual = Ventas.getInstancia();
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
		estadoAreaActual = new EstadoAreaPedido(siguienteHandler);
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


}