package backend;


import backend.Usuarios.Cliente;
import backend.Vehiculos.Caracteristicas.Accesorio;
import backend.Vehiculos.Caracteristicas.Color;
import backend.Vehiculos.Caracteristicas.ConfiguracionAdicional;
import backend.Vehiculos.Caracteristicas.EquipamentoExtra;
import backend.Vehiculos.Caracteristicas.EstadoVehiculo;
import backend.Vehiculos.Caracteristicas.GarantiaExtendida;
import backend.Vehiculos.PatronBuilder.AutoBuilder;
import backend.Vehiculos.TiposVehiculos.Vehiculo;
import backend.Usuarios.Usuario;
import backend.Usuarios.Vendedor;
import backend.Areas.DatosDeFacturacion;
import backend.Estados.EstadoPosiblesPedido;
import backend.MetodoDePago.Tarjeta;
import backend.Pedidos.OrdenDeCompra;

import java.util.Arrays;
import java.util.List;

// prueba para ver que toda la logica funciona correctamente antes de conectarlo con el front 

public class Main {
    public static void main(String[] args) {

        ConfiguracionAdicional polarizado = new EquipamentoExtra("Polarizado","oscuro 70%",250000);
        ConfiguracionAdicional rastreador = new Accesorio("Rastreador","gps",72000);
        ConfiguracionAdicional garantia1 = new GarantiaExtendida("garantia Premium","oscuro 70%",345000, 12);
        
        List<ConfiguracionAdicional> configuraciones1 = Arrays.asList(polarizado, rastreador, garantia1);


        Vehiculo auto1 = new AutoBuilder()
        .setAsientos(5)
        .setColor(Color.ROJO)
        .setModelo("Corolla")
        .setMarca("Toyota")
        .setAño(2020)
        .setAdicionales(configuraciones1)
        .setEstado(EstadoVehiculo.DISPONIBLE)
        .setKilometraje(38246)
        .build();

        Usuario juancito = new Cliente("Juan", "faltaEncriptar", "juancito007@gmail.com", "Benavidez");
        Usuario franco = new Vendedor("Franco", "faltaEncriptar", "fval@gmail.com", "Valentini");
        
        DatosDeFacturacion datos1 = new DatosDeFacturacion("Juan","20-34563245-5","Palma Mallorca 435"); 


        OrdenDeCompra orden1 = new OrdenDeCompra(0, juancito, auto1, datos1, new Tarjeta(), franco);


        System.out.println(orden1.getAreaActual().getNombre());
        System.out.println(orden1.getEstado().getEstado());
        System.out.println(orden1.getEstado().getFechaFinalizacion());
        System.out.println(orden1.getEstado().getFechaInicio());

        orden1.cambiarEstado(EstadoPosiblesPedido.FINALIZADO);

        System.out.println(orden1.getEstado().getEstado());



    }
}

