package Vistas;

import java.util.Scanner;

import Facades.FacadeConsecionaria;

public class VistaComprador implements Vista {
    private FacadeConsecionaria facade;

    public VistaComprador(FacadeConsecionaria facade) {
        this.facade = facade;
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Comprador ---");
            System.out.println("1 - Ver vehiculos disponibles");
            System.out.println("2 - Realizar compra");
            System.out.println("3 - Ver historial de compras");
            System.out.println("4 - Salir");
            System.out.print("Elegí una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

        // Lógica según la opción
            switch (opcion) {
                case 1:
                    facade.mostrarCatalogoDisponibles();
                    break;
                case 2:
                    System.out.print("Ingrese patente: ");
                    String patente = scanner.nextLine().toUpperCase();
                    System.out.print("Ingrese metodo de pago( CONTADO / TARJETA / TRANSFERENCIA): ");
                    String metodoDePago = scanner.nextLine().toUpperCase();
                    facade.crearOrdenDeCompra(patente,metodoDePago);
                    break;
                case 3:
                    facade.mostrarOrdenesDeUsuario(); //dni del comprador harcodeado
                    break;
                case 4:
                    System.out.println("Saliendo de la vista de comprador...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }
}