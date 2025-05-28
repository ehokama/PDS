package backend.MetodoDePago;

public class MetodoDePago{
    private String nombre;
    private String descripcion;

    public MetodoDePago(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public double calcularTotalConRecargos(){
        double totalConRecargos = 0;
        return totalConRecargos;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    
}