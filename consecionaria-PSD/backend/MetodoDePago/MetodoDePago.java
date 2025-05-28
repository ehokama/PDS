package backend.MetodoDePago;

public class MetodoDePago{

    private String descripcion;

    public MetodoDePago(String descripcion) {
        this.descripcion = descripcion;
    }

    public double calcularTotalConRecargos(){
        double totalConRecargos = 0;
        return totalConRecargos;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
}