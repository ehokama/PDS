package Vistas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Facades.FacadeConsecionaria;
import Vehiculos.Caracteristicas.Accesorio;
import Vehiculos.Caracteristicas.ConfiguracionAdicional;
import Vehiculos.Caracteristicas.EquipamentoExtra;
import Vehiculos.Caracteristicas.GarantiaExtendida;

public class VistaAdministrador implements Vista {
    private FacadeConsecionaria facade;

    public VistaAdministrador(FacadeConsecionaria facade) {
        this.facade = facade;
    }

    @Override
    public void mostrarMenu() {

        // PARTE HARCODEADA DE ADICIONALES
        ConfiguracionAdicional portaequipajes = new Accesorio("Portaequipajes FOX", "carga", 76000); 
        ConfiguracionAdicional polarizadAccesorio = new Accesorio("Vidrios polarizados 3M", "vista", 250000); 
        ConfiguracionAdicional camaraReversa = new EquipamentoExtra("Camara de reversa", "Sistemas Avanzados de Asistencia al Conductor (ADAS"); 
        ConfiguracionAdicional sensoresTraseros = new EquipamentoExtra("Sensores de estacionamiento traseros", "Sistemas Avanzados de Asistencia al Conductor (ADAS)"); 
        ConfiguracionAdicional techoPanoramico = new EquipamentoExtra("Portaequipajes FOX", "Interior"); 
        ConfiguracionAdicional garantiaPlata = new GarantiaExtendida("Garantia Plata", "GALENO SEGUROS: Duracion 3 meses", 300000);
        ConfiguracionAdicional garantiaPlatino = new GarantiaExtendida("Garantia Diamante", "GALENO SEGUROS: Duracion 12 meses", 800000);
        List<ConfiguracionAdicional> adicionalesAuto1 = Arrays.asList(portaequipajes,camaraReversa,garantiaPlata);
        List<ConfiguracionAdicional> adicionalesCamioneta1 = Arrays.asList(polarizadAccesorio,techoPanoramico,camaraReversa,sensoresTraseros,garantiaPlatino);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1 - Ver todos los compradores");
            System.out.println("2 - Ver todos los vehículos");
            System.out.println("3 - Ingresar Vehiculo");
            System.out.println("4 - Ver todas las órdenes");
            System.out.println("5 - Generar reporte por fecha");
            System.out.println("6 - Generar reporte por estado");
            System.out.println("7 - Salir");
            System.out.print("Elegí una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    facade.mostrarCompradores();
                    break;
                case 2:
                    facade.mostrarCatalogo();
                    break;
                case 3:
                    System.out.print("Ingrese marca: ");
                    String marca = scanner.nextLine().toUpperCase();
                    System.out.print("Ingrese modelo: ");
                    String modelo = scanner.nextLine().toUpperCase();
                    System.out.print("Ingrese año: ");
                    int año = Integer.parseInt(scanner.nextLine());

                    System.out.print("Ingrese color ( BLANCO,ROJO,AZUL,NEGRO,GRIS,NARANJA ): ");
                    String color = scanner.nextLine().toUpperCase();

                    System.out.print("Ingrese patente: ");
                    String patente = scanner.nextLine().toUpperCase();

                    System.out.print("Ingrese numeroChasis: ");
                    int numeroChasis = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese numeroMotor: ");
                    int numeroMotor = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese precio: ");
                    double precio = Double.parseDouble(scanner.nextLine());

                    System.out.print("Ingrese adicionales: ");
                    System.out.println("en esta linea se deberia hacer el ingreso de adicionales (esta harcodeado)");
                     // harcodeamos el ingreso de adicionales
                    
                    System.out.print("Especifique tipo de vehiculo a ingresar (Auto / Camioneta / Camion / Moto): ");
                    String vehiculo = scanner.nextLine().toUpperCase();
                    switch (vehiculo) {
                        case "AUTO":
                            facade.ingresarAuto(marca, modelo, año, color, patente, numeroChasis, numeroMotor, precio, adicionalesAuto1);
                            System.out.println("se ingreso exitosamente");
                            break;
                        case "CAMION":
                            facade.ingresarCamion(marca, modelo, año, color, patente, numeroChasis, numeroMotor, precio, adicionalesCamioneta1);
                            System.out.println("se ingreso exitosamente");
                            break;                    
                        case "CAMIONETA":
                            facade.ingresarCamioneta(marca, modelo, año, color, patente, numeroChasis, numeroMotor, precio, adicionalesCamioneta1);
                            System.out.println("se ingreso exitosamente");
                            break;                    
                        case "MOTO":
                            facade.ingresarMoto(marca, modelo, año, color, patente, numeroChasis, numeroMotor, precio, adicionalesCamioneta1);
                            System.out.println("se ingreso exitosamente");
                            break;
                }
                    break;
                case 4:
                    facade.mostrarOrdenes();
                    break;
                case 5:
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    
                        System.out.print("Ingrese fecha y hora de inicio (AAAA-MM-DD HH:MM): ");
                        String inicioStr = scanner.nextLine();
                        LocalDateTime fechaInicio = LocalDateTime.parse(inicioStr, formatter);
                    
                        System.out.print("Ingrese fecha y hora final (AAAA-MM-DD HH:MM): ");
                        String finStr = scanner.nextLine();
                        LocalDateTime fechaFin = LocalDateTime.parse(finStr, formatter);
                    
                        System.out.print("Ingrese formato (CSV / TXT): ");
                        String formato = scanner.nextLine().toUpperCase();
                    
                        facade.generarReportePorFecha(fechaInicio, fechaFin, formato);
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato inválido. Debe ser AAAA-MM-DD HH:MM (ejemplo: 2025-06-06 14:30)");
                    }
                    break;
                case 6:
                    System.out.print("Ingrese estado (APROBADA / PENDIENTE / CANCELADA): ");
                    String estado = scanner.nextLine().toUpperCase();
                    System.out.print("Ingrese formato ( CSV / TXT ): ");
                    String formato = scanner.nextLine().toUpperCase();
                    facade.generarReportePorEstado(estado, formato);
                    break;
                case 7:
                    System.out.println("Saliendo de la vista de administrador...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 7);
    }
}