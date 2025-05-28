package backend;


import backend.Usuarios.Cliente;
import backend.Vehiculos.Caracteristicas.Accesorio;
import backend.Vehiculos.Caracteristicas.Color;
import backend.Vehiculos.Caracteristicas.ConfiguracionAdicional;
import backend.Vehiculos.Caracteristicas.EquipamentoExtra;
import backend.Vehiculos.Caracteristicas.EstadoVehiculo;
import backend.Vehiculos.Caracteristicas.GarantiaExtendida;
import backend.Vehiculos.PatronBuilder.AutoBuilder;
import backend.Vehiculos.TiposVehiculos.Auto;
import backend.Vehiculos.TiposVehiculos.Vehiculo;
import backend.Usuarios.Usuario;
import backend.Usuarios.Vendedor;
import backend.Areas.Consecionaria;
import backend.Areas.DatosDeFacturacion;
import backend.Areas.Ventas;
import backend.Estados.*;
import backend.MetodoDePago.MetodoDePago;
import backend.MetodoDePago.Tarjeta;
import backend.MetodoDePago.Transferencia;
import backend.Pedidos.OrdenDeCompra;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;


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

        

    }
}

