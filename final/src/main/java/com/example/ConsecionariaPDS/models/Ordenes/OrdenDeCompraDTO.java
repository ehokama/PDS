package com.example.ConsecionariaPDS.models.Ordenes;


public class OrdenDeCompraDTO {

    private String compradorDni;
    private String vendedorDni;
    private String patenteVehiculo;
    private String metodoDePagoId;
    private String nombreCompleto;
    private String cuit;
    private String direccion;

    // getters y setters


    public String getCompradorDni() {
        return compradorDni;
    }

    public void setCompradorDni(String compradorDni) {
        this.compradorDni = compradorDni;
    }

    public void setVendedorDni(String vendedorDni) {
        this.vendedorDni = vendedorDni;
    }

    public String getVendedorDni() {
        return vendedorDni;
    }


    public String getPatenteVehiculo() {
        return patenteVehiculo;
    }

    public void setPatenteVehiculo(String patenteVehiculo) {
        this.patenteVehiculo = patenteVehiculo;
    }

    public String getMetodoDePagoId() {
        return metodoDePagoId;
    }

    public void setMetodoDePagoId(String metodoDePagoId) {
        this.metodoDePagoId = metodoDePagoId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
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
