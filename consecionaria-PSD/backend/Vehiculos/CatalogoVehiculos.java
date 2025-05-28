package backend.Vehiculos;

import java.util.ArrayList;
import java.util.List;

import backend.Vehiculos.TiposVehiculos.Vehiculo;

// controlador de vehiculos

public class CatalogoVehiculos {
    private List<Vehiculo> vehiculos = new ArrayList<>() ;
    
    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculos;
    }

    public void mostrarVehiculos() {
        for (Vehiculo v : vehiculos) {
            System.out.println(v);
            System.out.println(v.getAsientos());
            System.out.println(v.getPatente());
        }
    }
}
