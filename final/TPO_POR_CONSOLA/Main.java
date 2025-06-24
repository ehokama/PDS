import Vehiculos.Builder.AutoBuilder;
import Vehiculos.Builder.CamionetaBuilder;
import Vehiculos.Entidades.Auto;
import Vehiculos.Entidades.Camioneta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import MetodosDePago.MetodoDePago;
import MetodosDePago.Tarjeta;
import Ordenes.DatosDeFacturacion;
import Ordenes.EstadoAreaPedido;
import Ordenes.OrdenDeCompra;
import Reportes.Bridge.ExportadorCSV;
import Reportes.Bridge.ExportadorTXT;
import Reportes.Bridge.ReporteDeOrdenes;
import Reportes.Bridge.ReportePorEstado;
import Reportes.Bridge.ReportePorFecha;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Usuarios.Vendedor;
import Vehiculos.Caracteristicas.*;

public class Main{

    //en este main se prueba todo a pelo

    public static void main(String[] args) {

        // los accesorios son opcionales al auto, tienen precio determinado
        ConfiguracionAdicional portaequipajes = new Accesorio("Portaequipajes FOX", "carga", 76000); 
        ConfiguracionAdicional polarizadAccesorio = new Accesorio("Vidrios polarizados 3M", "vista", 250000); 

        //el equipamiento extra no posee precio porque son caracteristicas intrinsecas al vehiculo que se pueden mencionar si el vehiculo las posee
        ConfiguracionAdicional camaraReversa = new EquipamentoExtra("Camara de reversa", "Sistemas Avanzados de Asistencia al Conductor (ADAS"); 
        ConfiguracionAdicional sensoresTraseros = new EquipamentoExtra("Sensores de estacionamiento traseros", "Sistemas Avanzados de Asistencia al Conductor (ADAS)"); 
        ConfiguracionAdicional techoPanoramico = new EquipamentoExtra("Portaequipajes FOX", "Interior"); 


        //estas son las 4 garantias por defecto existentes en el sistema
        //ConfiguracionAdicional sinGarantia = new GarantiaExtendida("Sin garantia", "No posee ningun tipo de cobertura", 5000);
        ConfiguracionAdicional garantiaPlata = new GarantiaExtendida("Garantia Plata", "GALENO SEGUROS: Duracion 3 meses", 300000);
        //ConfiguracionAdicional garantiaOro = new GarantiaExtendida("Garantia Oro", "GALENO SEGUROS: Duracion 6 meses", 500000);
        ConfiguracionAdicional garantiaPlatino = new GarantiaExtendida("Garantia Diamante", "GALENO SEGUROS: Duracion 12 meses", 800000);

        //la lista de adicionales incluye los tipos de configuracion adicional que posee el auto incluyendo accesorios,equipamiento o garantia
        List<ConfiguracionAdicional> adicionalesAuto1 = Arrays.asList(portaequipajes,camaraReversa,garantiaPlata);
        List<ConfiguracionAdicional> adicionalesCamioneta1 = Arrays.asList(polarizadAccesorio,techoPanoramico,camaraReversa,sensoresTraseros,garantiaPlatino);

        //instanciamos 2 vehiculos 
        AutoBuilder auto1 = new AutoBuilder();
        auto1.setMarca("Chevrolet");
        auto1.setModelo("Corsa");
        auto1.setAño(2016);
        auto1.setColor(Color.GRIS);
        auto1.setPatente("AA020TT");
        auto1.setNumeroChasis(54342);
        auto1.setNumeroMotor(567);
        auto1.setPrecio(15000000);
        auto1.setAdicionales(adicionalesAuto1);

        Auto autoFinal1 = auto1.getAuto();
        System.out.println(autoFinal1.toString());

        CamionetaBuilder camioneta1 = new CamionetaBuilder();
        camioneta1.setMarca("Volkswagen");
        camioneta1.setModelo("Amarok");
        camioneta1.setAño(2025);
        camioneta1.setColor(Color.AZUL);
        camioneta1.setPatente("AH345LN");
        camioneta1.setNumeroChasis(78986);
        camioneta1.setNumeroMotor(923);
        camioneta1.setPrecio(57000000);
        camioneta1.setAdicionales(adicionalesCamioneta1);

        Camioneta camionetaFinal1 = camioneta1.getCamioneta();
        System.out.println(camionetaFinal1.toString());


        //probamos el patron state de vehiculo

        System.out.println(autoFinal1.getVehiculoEstado());

        autoFinal1.setProcesoDeVenta();

        System.out.println(autoFinal1.getVehiculoEstado());

        autoFinal1.setVendido();

        System.out.println(autoFinal1.getVehiculoEstado());

        autoFinal1.setDisponible();
        autoFinal1.setProcesoDeVenta();


        System.out.println(camionetaFinal1.getVehiculoEstado());

        camionetaFinal1.setProcesoDeVenta();

        System.out.println(camionetaFinal1.getVehiculoEstado());

        camionetaFinal1.setVendido();

        System.out.println(camionetaFinal1.getVehiculoEstado());

        camionetaFinal1.setDisponible();
        camionetaFinal1.setProcesoDeVenta();

        //probando los metodos de pago
        double montoBase = 1000000; 
        System.out.println(montoBase);
        MetodoDePago metodo = new Tarjeta(); 
        double recargo = metodo.calcularRecargo(montoBase);
        double totalFinal = montoBase + recargo;
        System.out.println(totalFinal);
        
        System.out.println("----------------------");

        //probando la creacion de una ordenDeCompra

        Usuario matias = new Vendedor("46231522", "Matias", "corsita123", "mdcacia@gmail.com", "Cacia", "1156723467");
        Usuario ivo = new Cliente("46567898", "Ivo", "zeus123", "ivillar9@gmail.com", "Villar", "1132156809");
        DatosDeFacturacion facturacionIvo = new DatosDeFacturacion(ivo, "20-46567898-5", "Larrea 2343, Quilmes");

        OrdenDeCompra orden1 = new OrdenDeCompra( 345, autoFinal1, matias, ivo, facturacionIvo, new Tarjeta());
        System.out.println(orden1.getAreaActual());
        System.out.println("PRECIO FINAL:" + orden1.getCostoTotal());
        orden1.notificar();

        System.out.println(orden1.getAreaActual());

        System.out.println("----------------------");

        //probamos el historialDeEstados de la orden1

        System.out.println("asdasd");
    
        for (EstadoAreaPedido estado : orden1.getHistorialDeEstados()) {
            System.out.println(estado.toString());
        }
        
        //Probamos los reportes de ordenes
        System.out.println("asdasd");

        List<OrdenDeCompra> lista1 = new ArrayList<>();
        lista1.add(orden1);

        ReporteDeOrdenes reporte1 = new ReportePorEstado(new ExportadorTXT(), "PENDIENTE"); // puede ser APROBADA,CANCELADA,PENDIENTE
        ReporteDeOrdenes reporte2 = new ReportePorFecha(new ExportadorCSV(), LocalDateTime.now().minusDays(30), LocalDateTime.now().plusDays(30) );

        reporte1.generarReporte(lista1);
        reporte2.generarReporte(lista1);

        System.out.println(autoFinal1.getVehiculoEstado());
    }   


}    