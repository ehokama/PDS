package Ordenes;

import Usuarios.Usuario;

public class DatosDeFacturacion {

    private Long id;
    private String nombreCompleto;
    private String cuit;
    private String direccion;

    
    public DatosDeFacturacion(Usuario comprador, String cuit, String direccion) { //se debería ingresar cuando se quiere reservar el pedido
        this.nombreCompleto = comprador.getNombre() + comprador.getApellido();
        this.cuit = cuit;
        this.direccion = direccion;
    }

    public String getnombreCompleto() {
        return nombreCompleto;
    }

    public String getCuit() {
        return cuit;
    }

    public String getDireccion() {
        return direccion;
    }

    
}