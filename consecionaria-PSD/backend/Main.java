package backend;

import backend.Vehiculos.CatalogoVehiculos;
import backend.Vehiculos.Caracteristicas.Color;
import backend.Vehiculos.Caracteristicas.Estado;
import backend.Vehiculos.PatronBuilder.AutoBuilder;
import backend.Vehiculos.TiposVehiculos.Vehiculo;

public class Main {
    public static void main(String[] args) {
        // Crear el catálogo
        CatalogoVehiculos catalogo = new CatalogoVehiculos();

        // Crear un auto con AutoBuilder
        Vehiculo primerAuto = new AutoBuilder()
            .setMarca("Toyota")
            .setModelo("Corolla")
            .setAño(2022)
            .setColor(Color.NEGRO)
            .setPatente(123456)
            .setNumeroChasis(98765)
            .setNumeroMotor(56789)
            .setAsientos(5)
            .setPrecio(30000)
            .setDisponible(true)
            .setEstado(Estado.DISPONIBLE)
            .build(); 

        // Agregarlo al catálogo
        System.out.println(primerAuto.getPatente());
        System.out.println(primerAuto.getColor());

        catalogo.agregarVehiculo(primerAuto);

        // Mostrar vehículos en catálogo
        catalogo.mostrarVehiculos();
        
    }
}
