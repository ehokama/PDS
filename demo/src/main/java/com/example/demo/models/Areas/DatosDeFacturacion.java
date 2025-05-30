package com.example.demo.models.Areas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DatosDeFacturacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;
    private String cuit;
    private String direccion;

    
    public DatosDeFacturacion(String nombreCompleto, String cuit, String direccion) { //se debería ingresar cuando se quiere reservar el pedido
        this.nombreCompleto = nombreCompleto;
        this.cuit = cuit;
        this.direccion = direccion;
    }

    public String getnombreCompleto() {
        return nombreCompleto;
    }
    public void setnombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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