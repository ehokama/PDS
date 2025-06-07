package Vistas;
import java.util.Scanner;

import Facades.FacadeConsecionaria;

public class VistaVendedor implements Vista {
    private FacadeConsecionaria facade;

    public VistaVendedor(FacadeConsecionaria facade) {
        this.facade = facade;
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Comprador ---");
            System.out.println("1 - Ver vehiculos disponibles");
            System.out.println("2 - Salir");
            System.out.print("Elegí una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

        // Lógica según la opción
            switch (opcion) {
                case 1:
                    facade.mostrarCatalogoDisponibles();
                    break;
                case 2:
                    System.out.println("Saliendo de la vista de Vendedor...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 2);
    }
}