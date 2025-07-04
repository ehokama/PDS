package Facades;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import MetodosDePago.Contado;
import MetodosDePago.MetodoDePago;
import MetodosDePago.Tarjeta;
import MetodosDePago.Transferencia;
import Ordenes.DatosDeFacturacion;
import Ordenes.OrdenDeCompra;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Usuarios.Vendedor;
import Vehiculos.Caracteristicas.Color;
import Vehiculos.Caracteristicas.ConfiguracionAdicional;


public class FacadeConsecionaria {
    
    FacadeOrdenes ordenes = new FacadeOrdenes();
    FacadeVehiculos vehiculos = new FacadeVehiculos();
    FacadeUsuarios usuarios = new FacadeUsuarios();
    FacadeReportes reportes = new FacadeReportes();
   
    public FacadeConsecionaria() { //harcodeamos esta parte
        matias = new Vendedor("46231522", "Matias", "123", "mdcacia@gmail.com", "Cacia", "1156723467");
        profe = new Cliente("46567898", "Manuel Adrian", "123", "macaceres@gmail.com", "Caceres", "1132156809");
        facturacionIvo = new DatosDeFacturacion(profe, "20-46567898-5", "Andres Baranda 2343, Quilmes");

        usuarios.añadirCliente("46567898", "Ivo", "zeus123", "ivillar9@gmail.com", "Villar", "1132156809");
        usuarios.añadirVendedor("46231522", "Matias", "corsita123", "mdcacia@gmail.com", "Cacia", "1156723467");

    }
    
    Usuario matias;
    Usuario profe;
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
        ordenes.crearOrdenDeCompra( ThreadLocalRandom.current().nextInt(1, 101), vehiculos.getVehiculo(patente), matias, profe, facturacionIvo, metodo);
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
        ordenes.mostrarOrdenesDeUsuario(profe.getDni());
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


