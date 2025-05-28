package backend.Pedidos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import backend.Areas.Cobranzas;
import backend.Areas.Consecionaria;
import backend.Areas.DatosDeFacturacion;
import backend.Areas.Embarque;
import backend.Areas.Entrega;
import backend.Areas.Impuestos;
import backend.Areas.Logistica;
import backend.Areas.Seguimiento;
import backend.Areas.Ventas;
import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Areas.PatronIObserver.ISubject;
import backend.Estados.EstadoPedido;
import backend.Estados.EstadoPosiblesPedido;
import backend.MetodoDePago.MetodoDePago;
import backend.Usuarios.Usuario;
import backend.Vehiculos.Caracteristicas.EstadoVehiculo;
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
    private String cuitConsecionaria;
    private HandlerEtapa handler;

    //constructor (tiene en cuenta los valores que se cargan automaticamente: costototal)
    public OrdenDeCompra(int numeroPedido, Usuario usuario, Vehiculo vehiculo, DatosDeFacturacion facturacion, 
                     MetodoDePago metodoDePago, Usuario vendedor) {
    this.numeroPedido = numeroPedido;
    this.usuario = usuario;
    this.vehiculo = vehiculo;
    this.areaActual = Ventas.getInstancia();
    this.facturacion = facturacion;
    this.metodoDePago = metodoDePago;
    this.vendedor = vendedor;
    this.nombreConsecionaria = Consecionaria.getNombre();
    this.cuitConsecionaria = Consecionaria.getCuit();
    this.handler = Ventas.getInstancia();

    this.fechaCreacion = new Date(System.currentTimeMillis());
    this.historialDeEstados = new ArrayList<>();
    this.observadores = Arrays.asList(Ventas.getInstancia(), Cobranzas.getInstancia(), Impuestos.getInstancia(),Embarque.getInstancia(),Logistica.getInstancia(), Entrega.getInstancia(), Seguimiento.getInstancia());
    
    // Crear estado inicial
    EstadoPedido estadoInicial = new EstadoPedido();
    estadoInicial.setEstadoPosible(backend.Estados.EstadoPosiblesPedido.PENDIENTE);
    estadoInicial.setFechaInicio(this.fechaCreacion);
    estadoInicial.setAreaResponsable(areaActual);

    this.estado = estadoInicial;
    this.historialDeEstados.add(estadoInicial);

    // Calcular costo inicial
    this.costoTotal = (int) this.calcularCostoTotal();
    }


    public void cambiarEstado(EstadoPosiblesPedido nuevoEstado) {
        // Finalizar el estado anterior si existe
        if (this.estado != null) {
            this.estado.setFechaFinalizacion(new Date(System.currentTimeMillis()));
        }

        // Crear nuevo estado
        EstadoPedido nuevoEstadoPedido = new EstadoPedido();
        nuevoEstadoPedido.setEstadoPosible(nuevoEstado);
        nuevoEstadoPedido.setFechaInicio(new Date(System.currentTimeMillis()));
        nuevoEstadoPedido.setAreaResponsable(this.areaActual); // el área que lo tiene asignado

        // Actualizar estado actual y el historial
        this.estado = nuevoEstadoPedido;
        this.historialDeEstados.add(nuevoEstadoPedido);

        // Notificar a los observadores
        notificar();
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
        observadores.add(observer);
    }

    @Override
    public void eliminar(IAreaObserver observer) {
        observadores.remove(observer);

    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public IAreaObserver getAreaActual() {
        return areaActual;
    }

    public void setAreaActual(IAreaObserver areaActual) {
        this.areaActual = areaActual;
    }

    public DatosDeFacturacion getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(DatosDeFacturacion facturacion) {
        this.facturacion = facturacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }


    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(MetodoDePago metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public List<EstadoPedido> getHistorialDeEstados() {
        return historialDeEstados;
    }

    public void setHistorialDeEstados(List<EstadoPedido> historialDeEstados) {
        this.historialDeEstados = historialDeEstados;
    }

    public List<IAreaObserver> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<IAreaObserver> observadores) {
        this.observadores = observadores;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getNombreConsecionaria() {
        return nombreConsecionaria;
    }

    public void setNombreConsecionaria(String nombreConsecionaria) {
        this.nombreConsecionaria = nombreConsecionaria;
    }

    public String getCuitConsecionaria() {
        return cuitConsecionaria;
    }

    public void setCuitConsecionaria(String cuitConsecionaria) {
        this.cuitConsecionaria = cuitConsecionaria;
    }

    public HandlerEtapa getHandler() {
        return handler;
    }

    public void setPrimerHandler(HandlerEtapa primerHandler) {
        this.handler = primerHandler;
    }

    public void setEstadoDelPedido(EstadoPosiblesPedido estado){
        this.estado.setEstadoPosible(estado);
    }    

}
