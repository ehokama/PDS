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

    public int getCuitConsecionaria() {
        return cuitConsecionaria;
    }

    public void setCuitConsecionaria(int cuitConsecionaria) {
        this.cuitConsecionaria = cuitConsecionaria;
    }

    public HandlerEtapa getPrimerHandler() {
        return primerHandler;
    }

    public void setPrimerHandler(HandlerEtapa primerHandler) {
        this.primerHandler = primerHandler;
    }

    //getters y setters
    

}
