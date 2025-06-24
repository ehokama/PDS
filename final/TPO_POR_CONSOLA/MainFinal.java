

import java.util.Scanner;

import Facades.FacadeConsecionaria;
import Vistas.VistaAdministrador;
import Vistas.VistaComprador;
import Vistas.VistaVendedor;


public class MainFinal {
    
    //en este main se prueba todo segun las vistas
    //parte de los usuarios y adicionales esta harcodeada, sin embargo la creacion de las ordenes, vehiculos y reportes se encuentra totalmente funcional
    public static void main(String[] args) {
        FacadeConsecionaria facade = new FacadeConsecionaria();
        Scanner scanner = new Scanner(System.in); // mover también fuera del loop
        int opcion = 0; // declarar fuera del do

        do {
            System.out.println("Bienvenido al sistema");
            System.out.println("Ingrese una opción:");
            System.out.println("1 - Ingresar como Administrador");
            System.out.println("2 - Ingresar como Comprador");
            System.out.println("3 - Ingresar como Vendedor");
            System.out.println("4 - Finalizar sesión");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    VistaAdministrador vista1 = new VistaAdministrador(facade);
                    vista1.mostrarMenu();
                    break;
                case 2:
                    VistaComprador vista2 = new VistaComprador(facade);
                    vista2.mostrarMenu();                
                    break;
                case 3:
                    VistaVendedor vista3 = new VistaVendedor(facade);
                    vista3.mostrarMenu();
                    break;
                case 4:
                    System.out.println("Sesión finalizada.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }


}
