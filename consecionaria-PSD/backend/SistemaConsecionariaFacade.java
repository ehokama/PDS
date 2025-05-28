package backend;

import java.sql.Date;

import backend.Areas.DatosDeFacturacion;
import backend.Estados.EstadoPosiblesPedido;
import backend.MetodoDePago.MetodoDePago;
import backend.Pedidos.ReporteDePedidos;
import backend.Pedidos.SistemaPedidos;
import backend.Usuarios.SistemaUsuarios;
import backend.Usuarios.Usuario;
import backend.Usuarios.Vendedor;
import backend.Vehiculos.CatalogoVehiculos;
import backend.Vehiculos.TiposVehiculos.Vehiculo;

public class SistemaConsecionariaFacade {

    SistemaPedidos sistemaPedidos = new SistemaPedidos();
    CatalogoVehiculos catalogoVehiculos = new CatalogoVehiculos();
    ReporteDePedidos sistemaReportes = new ReporteDePedidos(sistemaPedidos.getPedidos());
    SistemaUsuarios sistemaUsuarios = new SistemaUsuarios();

    public void realizarPedido(Usuario usuario, Vehiculo vehiculo, DatosDeFacturacion facturacion,MetodoDePago metodoDePago, Vendedor vendedor){
        sistemaPedidos.realizarPedido(usuario, vehiculo, facturacion, metodoDePago, vendedor);
    }

    public void generarReporteCompleto() {
        sistemaReportes.generarReporteCompleto();
    }

    public void generarReportePorEstado(EstadoPosiblesPedido estadoBuscado) {
        sistemaReportes.generarReportePorEstado(estadoBuscado);
    }

    public void generarReportePorFecha(Date desde, Date hasta) {
        sistemaReportes.generarReportePorFecha( desde,  hasta);
    }

    public void agregarUsuario(Usuario usuario){
        sistemaUsuarios.agregarUsuario(usuario);
    }

    public void eliminarUsuario(Usuario usuario){
        sistemaUsuarios.eliminarUsuario(usuario);
    }

    public void agregarVehiculo(Vehiculo vehiculo){
        catalogoVehiculos.agregarVehiculo(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo){
        catalogoVehiculos.eliminarVehiculo(vehiculo);
    }
}
