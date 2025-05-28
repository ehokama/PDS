package backend.Vehiculos.Caracteristicas;

public class GarantiaExtendida extends ConfiguracionAdicional{

    private int duracionMeses;

    public GarantiaExtendida(String nombre, String descripcion, double precio, int duracionMeses) {
        super(nombre, descripcion, precio);
        this.duracionMeses = duracionMeses;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }
    
    


}
