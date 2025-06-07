package Vehiculos.State;

import Vehiculos.Entidades.Vehiculo;

public class EnProcesoDeVenta extends VehiculoEstado{

    public EnProcesoDeVenta(Vehiculo vehiculo) {
        super(vehiculo);
    }

    @Override
    public void setDisponible() {
        vehiculo.setVehiculoEstado(new Disponible(vehiculo));
        System.out.println("Cambiando estado de vehiculo con patente "+ vehiculo.getPatente() +" de EnProcesoDeVenta a Disponible (cancelando proceso de venta).");
    }

    @Override
    public void setProcesoDeVenta() {
        System.out.println("Vehiculo con patente "+ vehiculo.getPatente() +" ya se encuentra EnProcesoDeVenta.");
    }

    @Override
    public void setVendido() {
        vehiculo.setVehiculoEstado(new Vendido(vehiculo));
        System.out.println("Cambiando estado de vehiculo con patente "+ vehiculo.getPatente() +" de EnProcesoDeVenta a Vendido.");
    }
}
