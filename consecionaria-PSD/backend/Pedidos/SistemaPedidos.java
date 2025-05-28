package backend.Pedidos;

import java.util.List;

import backend.MetodoDePago.MetodoDePago;
import backend.Usuarios.Usuario;
import backend.Usuarios.Vendedor;
import backend.Vehiculos.TiposVehiculos.Vehiculo;

public class SistemaPedidos {
    private List<OrdenDeCompra> pedidos;

    
    public void realizarPedido(Usuario usuario, Vehiculo vehiculo, MetodoDePago formaDePago, Vendedor vendedor){
        
    }

    public void eliminarPedido(Usuario usuario, Vehiculo vehiculo, MetodoDePago formaDePago, Vendedor vendedor){

    }

    public List<OrdenDeCompra> getPedidos(){
        return this.pedidos;
    }

}