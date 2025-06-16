package com.example.ConsecionariaPDS.models.Ordenes;


import jakarta.persistence.*;



@Embeddable
public class DatosDeFacturacion {

    private String nombreCompleto;
    private String cuit;
    private String direccion;

    public DatosDeFacturacion() {
    }

    public DatosDeFacturacion(String nombreCompleto, String cuit, String direccion) { //se debería ingresar cuando se quiere reservar el pedido
        this.nombreCompleto = nombreCompleto;
        this.cuit = cuit;
        this.direccion = direccion;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCuit() { return cuit; }
    public void setCuit(String cuit) { this.cuit = cuit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    
}