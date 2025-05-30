package com.example.demo.models.Pedidos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.models.Areas.Cobranzas;
import com.example.demo.models.Areas.Consecionaria;
import com.example.demo.models.Areas.DatosDeFacturacion;
import com.example.demo.models.Areas.Embarque;
import com.example.demo.models.Areas.Entrega;
import com.example.demo.models.Areas.Impuestos;
import com.example.demo.models.Areas.Logistica;
import com.example.demo.models.Areas.Seguimiento;
import com.example.demo.models.Areas.Ventas;
import com.example.demo.models.Areas.PatronHandler.HandlerEtapa;
import com.example.demo.models.Areas.PatronIObserver.IAreaObserver;
import com.example.demo.models.Areas.PatronIObserver.ISubject;
import com.example.demo.models.Estados.EstadoPedido;
import com.example.demo.models.MetodoDePago.MetodoDePago;
import com.example.demo.models.Usuarios.Usuario;
import com.example.demo.models.Vehiculos.Vehiculo;



public class OrdenDeCompra implements ISubject{
    private int numeroPedido;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private IAreaObserver areaActual;
    private DatosDeFacturacion facturacion;
    private Date fechaCreacion;
    private EstadoPedido estadoPedido;
    private MetodoDePago metodoDePago;
    private List<EstadoPedido> historialDeEstados;
    private List<IAreaObserver>observadores;
    private Usuario vendedor;
    private int costoTotal;
    private String nombreConsecionaria;
    private String cuitConsecionaria;
    private HandlerEtapa primerHandler;
    //constructor (tiene en cuenta los valores que se cargan automaticamente: costototal)
    public OrdenDeCompra(int numeroPedido, Usuario usuario, Vehiculo vehiculo, DatosDeFacturacion facturacion, MetodoDePago metodoDePago, Usuario vendedor, Ventas primerHandler) {
        this.numeroPedido = numeroPedido;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.areaActual = Ventas.getInstancia();
        this.facturacion = facturacion;
        this.metodoDePago = metodoDePago;
        this.vendedor = vendedor;
        this.nombreConsecionaria = Consecionaria.getNombre();
        this.cuitConsecionaria = Consecionaria.getCuit();
        this.fechaCreacion = new Date(System.currentTimeMillis());
        this.historialDeEstados = new ArrayList<>();
        this.observadores = Arrays.asList(Ventas.getInstancia(), Cobranzas.getInstancia(), Impuestos.getInstancia(),Embarque.getInstancia(),Logistica.getInstancia(), Entrega.getInstancia(), Seguimiento.getInstancia());
        this.costoTotal = (int) this.calcularCostoTotal();
        this.estadoPedido = new EstadoPedido(primerHandler);
        this.primerHandler = Ventas.getInstancia();
    }


    public void notificar(){
        for (IAreaObserver observer : observadores) {
            observer.update(this);
        }
        primerHandler.handle(this);
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

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
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

    public void añadirEstadoAlHistorial( EstadoPedido estadoPedido){
        this.historialDeEstados.add(estadoPedido);
    }

    public void mostrarHistorial(){
        for (EstadoPedido estado : this.historialDeEstados) {
            System.out.println(estado.toString());
        }
    }

}
