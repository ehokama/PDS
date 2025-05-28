package backend.Pedidos;

import java.util.List;

import backend.Vehiculos.TiposVehiculos.Vehiculo;

public class SistemaPedidos {
    private List<OrdenDeCompra> pedidos;

    
    public void realizarPedido(Usuario usuario, Vehiculo vehiculo, MetodoDePaGO formaDePago, Vendedor vendedor){

    }

    public void eliminarPedido(Usuario usuario, Vehiculo vehiculo, MetodoDePaGO formaDePago, Vendedor vendedor){

    }

    public List<OrdenDeCompra> getPedidos(){
        return this.pedidos;
    }

}