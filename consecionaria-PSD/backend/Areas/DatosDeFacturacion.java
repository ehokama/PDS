package backend.Areas;


public class DatosDeFacturacion{
    private String nombre;
    private String cuit;
    private String direccion;

    

    public DatosDeFacturacion(String nombre, String cuit, String direccion) { //se debería ingresar cuando se quiere reservar el pedido
        this.nombre = nombre;
        this.cuit = cuit;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}