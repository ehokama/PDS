package backend.Pedidos;

import java.util.ArrayList;
import java.util.List;

import backend.Areas.DatosDeFacturacion;
import backend.MetodoDePago.MetodoDePago;
import backend.Usuarios.Usuario;
import backend.Usuarios.Vendedor;
import backend.Vehiculos.TiposVehiculos.Vehiculo;

public class SistemaPedidos {
    private List<OrdenDeCompra> pedidos = new ArrayList<>();

    
    public void realizarPedido(Usuario usuario, Vehiculo vehiculo, DatosDeFacturacion facturacion,MetodoDePago metodoDePago, Vendedor vendedor){
        OrdenDeCompra orden = new OrdenDeCompra(0, usuario, vehiculo, facturacion, metodoDePago, vendedor); // HAY QUE HACER EL NUMERO INCREMENTAL 565687
        pedidos.add(orden);
    }

    public void eliminarPedido(OrdenDeCompra orden){
        pedidos.remove(orden);
    }

    public List<OrdenDeCompra> getPedidos(){
        return this.pedidos;
    }

}