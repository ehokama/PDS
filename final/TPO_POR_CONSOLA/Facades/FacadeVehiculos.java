package Facades;
import java.util.ArrayList;
import java.util.List;

import Vehiculos.Builder.AutoBuilder;
import Vehiculos.Builder.MotoBuilder;
import Vehiculos.Builder.CamionetaBuilder;
import Vehiculos.Builder.CamionBuilder;
import Vehiculos.Caracteristicas.Color;
import Vehiculos.Entidades.Auto;
import Vehiculos.Entidades.Camion;
import Vehiculos.Entidades.Camioneta;
import Vehiculos.Entidades.Moto;
import Vehiculos.Entidades.Vehiculo;
import Vehiculos.State.Disponible;
import Vehiculos.Caracteristicas.ConfiguracionAdicional;

public class FacadeVehiculos {
    List<Vehiculo> catalogoVehiculos = new ArrayList<>();

    public Auto crearAuto(String marca, String modelo, int año, Color color, String patente, int numeroChasis, int numeroMotor, double precio, List<ConfiguracionAdicional> adicionales ){
        AutoBuilder auto1 = new AutoBuilder();
        auto1.setMarca(marca);
        auto1.setModelo(modelo);
        auto1.setAño(año);
        auto1.setColor(color);
        auto1.setPatente(patente);
        auto1.setNumeroChasis(numeroChasis);
        auto1.setNumeroMotor(numeroMotor);
        auto1.setPrecio(precio);
        auto1.setAdicionales(adicionales);
        return auto1.getAuto();
    }


    public Camion crearCamion(String marca, String modelo, int año, Color color, String patente, int numeroChasis, int numeroMotor, double precio, List<ConfiguracionAdicional> adicionales ){
        CamionBuilder camion1 = new CamionBuilder();
        camion1.setMarca(marca);
        camion1.setModelo(modelo);
        camion1.setAño(año);
        camion1.setColor(color);
        camion1.setPatente(patente);
        camion1.setNumeroChasis(numeroChasis);
        camion1.setNumeroMotor(numeroMotor);
        camion1.setPrecio(precio);
        camion1.setAdicionales(adicionales);
        return camion1.getCamion();
    }
    
    public Camioneta crearCamioneta(String marca, String modelo, int año, Color color, String patente, int numeroChasis, int numeroMotor, double precio, List<ConfiguracionAdicional> adicionales ){
        CamionetaBuilder camioneta1 = new CamionetaBuilder();
        camioneta1.setMarca(marca);
        camioneta1.setModelo(modelo);
        camioneta1.setAño(año);
        camioneta1.setColor(color);
        camioneta1.setPatente(patente);
        camioneta1.setNumeroChasis(numeroChasis);
        camioneta1.setNumeroMotor(numeroMotor);
        camioneta1.setPrecio(precio);
        camioneta1.setAdicionales(adicionales);
        return camioneta1.getCamioneta();
    }

    public Moto crearMoto(String marca, String modelo, int año, Color color, String patente, int numeroChasis, int numeroMotor, double precio, List<ConfiguracionAdicional> adicionales ){
        MotoBuilder moto1 = new MotoBuilder();
        moto1.setMarca(marca);
        moto1.setModelo(modelo);
        moto1.setAño(año);
        moto1.setColor(color);
        moto1.setPatente(patente);
        moto1.setNumeroChasis(numeroChasis);
        moto1.setNumeroMotor(numeroMotor);
        moto1.setPrecio(precio);
        moto1.setAdicionales(adicionales);
        return moto1.getMoto();
    }

    public void agregarVehiculo(Vehiculo vehiculo){
        catalogoVehiculos.add(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo){
        catalogoVehiculos.remove(vehiculo);
    }

    public Vehiculo getVehiculo(String patente){
        Vehiculo vehiculo = null;
        for (Vehiculo v : catalogoVehiculos) {
            if (v.getPatente().equals(patente)){
                vehiculo = v;
            }
        }
        return vehiculo;
    }

    public void mostrarCatalogo(){
        for (Vehiculo vehiculo : catalogoVehiculos) {
            System.out.println(vehiculo.toString());
        }
    }

    public void mostrarCatalogoDisponibles(){
        System.out.println("SE APLICA UN RECARGO DEL 10% SI SE PAGA CON TARJETA DE CREDITO");
        for (Vehiculo vehiculo : catalogoVehiculos) {
            if (vehiculo.getVehiculoEstado() instanceof Disponible) {
                System.out.println(vehiculo.toString());
            }
        }
    }


}
