package com.example.ConcesionariaPDS.models.Facades;

import java.util.ArrayList;
import java.util.List;

import com.example.ConcesionariaPDS.models.MetodosDePago.MetodoDePago;
import com.example.ConcesionariaPDS.models.Ordenes.DatosDeFacturacion;
import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;
import com.example.ConcesionariaPDS.models.Usuarios.Usuario;
import com.example.ConcesionariaPDS.models.Vehiculos.Entidades.Vehiculo;


public class FacadeOrdenes {
    List<OrdenDeCompra> ordenes = new ArrayList<>();


    public void crearOrdenDeCompra(int numeroDeOrden, Vehiculo vehiculo, Usuario vendedor, Usuario comprador, DatosDeFacturacion datosFacturacion, MetodoDePago metodoDePago){
        OrdenDeCompra orden1 = new OrdenDeCompra(numeroDeOrden, vehiculo, vendedor, comprador, datosFacturacion, metodoDePago);
        agregarOrdenDeCompra(orden1); 
        orden1.notificar();
    }

    public void agregarOrdenDeCompra(OrdenDeCompra orden){
        ordenes.add(orden);
    }

    public void eliminarOrdenDeCompra(OrdenDeCompra orden){
        ordenes.remove(orden);
    }

    public void mostrarOrdenes(){
        for (OrdenDeCompra orden : ordenes) {
            System.out.println(orden.toString());
        }
    }

    public void mostrarOrdenesDeUsuario(String dni){
        for (OrdenDeCompra orden : ordenes) {
            if(orden.getDniComprador()==dni){
                System.out.println(orden.toString()); 
            }
        }
    }

    public List<OrdenDeCompra> getOrdenes() {
        return ordenes;
    }

    
}
