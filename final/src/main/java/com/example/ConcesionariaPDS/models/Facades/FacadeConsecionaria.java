package com.example.ConcesionariaPDS.models.Facades;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.example.ConcesionariaPDS.models.MetodosDePago.Contado;
import com.example.ConcesionariaPDS.models.MetodosDePago.MetodoDePago;
import com.example.ConcesionariaPDS.models.MetodosDePago.Tarjeta;
import com.example.ConcesionariaPDS.models.MetodosDePago.Transferencia;
import com.example.ConcesionariaPDS.models.Ordenes.DatosDeFacturacion;
import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;
import com.example.ConcesionariaPDS.models.Usuarios.Cliente;
import com.example.ConcesionariaPDS.models.Usuarios.Usuario;
import com.example.ConcesionariaPDS.models.Usuarios.Vendedor;
import com.example.ConcesionariaPDS.models.Vehiculos.Caracteristicas.Color;
import com.example.ConcesionariaPDS.models.Vehiculos.Caracteristicas.ConfiguracionAdicional;


public class FacadeConsecionaria {
    
    FacadeOrdenes ordenes = new FacadeOrdenes();
    FacadeVehiculos vehiculos = new FacadeVehiculos();
    FacadeUsuarios usuarios = new FacadeUsuarios();
    FacadeReportes reportes = new FacadeReportes();
   
    public FacadeConsecionaria() { //harcodeamos esta parte
        matias = new Vendedor("46231522", "Matias", "corsita123", "mdcacia@gmail.com", "Cacia", "1156723467");
        ivo = new Cliente("46567898", "Ivo", "zeus123", "ivillar9@gmail.com", "Villar", "1132156809");
        facturacionIvo = new DatosDeFacturacion("Ivo Villar Andres", "20-46567898-5", "Larrea 2343, Quilmes");

        usuarios.añadirCliente("46567898", "Ivo", "zeus123", "ivillar9@gmail.com", "Villar", "1132156809");
        usuarios.añadirVendedor("46231522", "Matias", "corsita123", "mdcacia@gmail.com", "Cacia", "1156723467");

    }
    
    Usuario matias;
    Usuario ivo;
    DatosDeFacturacion facturacionIvo;

    
    // ORDENES
    public void crearOrdenDeCompra( String patente, String metodoDePago) {
        //harcodeamos la parte de usuario y vendedor para la ejecucion por consola (en la web con el front si se hace una correcta ejecucion)
        MetodoDePago metodo;

        switch (metodoDePago.toUpperCase()) {
            case "CONTADO":
                metodo = new Contado();
                break;
            case "TARJETA":
                metodo = new Tarjeta();
                break;
            case "TRANSFERENCIA":
                metodo = new Transferencia();
                break;
            default:
                System.out.println("Método de pago inválido. Se usará CONTADO por defecto.");
                metodo = new Contado();
                break;
        }   // parte harcodeada con usuarios y adicionales
        ordenes.crearOrdenDeCompra( ThreadLocalRandom.current().nextInt(1, 101), vehiculos.getVehiculo(patente), matias, ivo, facturacionIvo, metodo);
    }
    
    public void eliminarOrdenDeCompra(OrdenDeCompra orden) {
        ordenes.eliminarOrdenDeCompra(orden);
    }

    public void mostrarOrdenes() {
        ordenes.mostrarOrdenes(); // ✅ Usás el método ya implementado
    }

    // USUARIOS
    public void añadirCliente(String dni, String nombre, String apellido, String password, String telefono, String correo) {
        usuarios.añadirCliente( dni, nombre, apellido, password, telefono, correo);
    }

    public void eliminarUsuario(){
        usuarios.eliminarUsuario(null);
    }

    public void mostrarCompradores() {
        usuarios.mostrarCompradores(); // ✅ Delegás al facade real
    }

    public void mostrarOrdenesDeUsuario(){ // harcodeado
        ordenes.mostrarOrdenesDeUsuario(ivo.getDni());
    }

    // VEHICULOS
    public void ingresarAuto(String marca, String modelo, int año, String colorStr, String patente, int numeroChasis, int numeroMotor, double precio, List<ConfiguracionAdicional> adicionales) {
        try {
            Color color = Color.valueOf(colorStr.toUpperCase());
            vehiculos.agregarVehiculo(vehiculos.crearAuto(marca, modelo, año, color, patente, numeroChasis, numeroMotor, precio, adicionales));
        } catch (IllegalArgumentException e) {
            System.out.println("Color inválido. Los colores válidos son: BLANCO, ROJO, AZUL, NEGRO, GRIS, NARANJA.");
        }
    }

    public void ingresarCamioneta(String marca, String modelo, int año, String colorStr, String patente, int numeroChasis, int numeroMotor, double precio, List<ConfiguracionAdicional> adicionales) {
        try {
            Color color = Color.valueOf(colorStr.toUpperCase());
            vehiculos.agregarVehiculo(vehiculos.crearCamioneta(marca, modelo, año, color, patente, numeroChasis, numeroMotor, precio, adicionales));
        } catch (IllegalArgumentException e) {
            System.out.println("Color inválido. Los colores válidos son: BLANCO, ROJO, AZUL, NEGRO, GRIS, NARANJA.");
        }
    }

    public void ingresarCamion(String marca, String modelo, int año, String colorStr, String patente, int numeroChasis, int numeroMotor, double precio, List<ConfiguracionAdicional> adicionales) {
        try {
            Color color = Color.valueOf(colorStr.toUpperCase());
            vehiculos.agregarVehiculo(vehiculos.crearCamion(marca, modelo, año, color, patente, numeroChasis, numeroMotor, precio, adicionales));
        } catch (IllegalArgumentException e) {
            System.out.println("Color inválido. Los colores válidos son: BLANCO, ROJO, AZUL, NEGRO, GRIS, NARANJA.");
        }
    }

    public void ingresarMoto(String marca, String modelo, int año, String colorStr, String patente, int numeroChasis, int numeroMotor, double precio, List<ConfiguracionAdicional> adicionales) {
        try {
            Color color = Color.valueOf(colorStr.toUpperCase());
            vehiculos.agregarVehiculo(vehiculos.crearMoto(marca, modelo, año, color, patente, numeroChasis, numeroMotor, precio, adicionales));
        } catch (IllegalArgumentException e) {
            System.out.println("Color inválido. Los colores válidos son: BLANCO, ROJO, AZUL, NEGRO, GRIS, NARANJA.");
        }
    }

    public void mostrarCatalogo() {
        vehiculos.mostrarCatalogo();
    }

    public void mostrarCatalogoDisponibles(){
        vehiculos.mostrarCatalogoDisponibles();
    }

    // REPORTES
    public void generarReportePorFecha(LocalDateTime desde, LocalDateTime hasta, String format) {
        reportes.generarReportePorFecha(ordenes.getOrdenes(),desde,hasta,format);
    }

    public void generarReportePorEstado( String estado, String formato) {
        reportes.generarReportePorEstado(ordenes.getOrdenes(),estado,formato);
    }

}


