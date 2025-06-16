package com.example.ConsecionariaPDS.models.Ordenes;

import java.time.LocalDateTime;

public class OrdenDeCompraDTO {

    private int numeroDeOrden; // opcional, para crear se suele omitir o dejar 0
    private String compradorDni;
    private String vendedorDni;
    private String patenteVehiculo;
    private int metodoDePagoId;
    private String nombreCompleto;
    private String cuit;
    private String direccion;

    private LocalDateTime fechaCreacion; // puede omitirse si se genera automático

    // getters y setters

    public int getNumeroDeOrden() {
        return numeroDeOrden;
    }

    public void setNumeroDeOrden(int numeroDeOrden) {
        this.numeroDeOrden = numeroDeOrden;
    }

    public String getCompradorDni() {
        return compradorDni;
    }

    public void setCompradorDni(String compradorDni) {
        this.compradorDni = compradorDni;
    }

    public String getDniVendedor() {
        return vendedorDni;
    }

    public void setVendedorDni(String vendedorDni) {
        this.vendedorDni = vendedorDni;
    }

    public String getPatenteVehiculo() {
        return patenteVehiculo;
    }

    public void setPatenteVehiculo(String patenteVehiculo) {
        this.patenteVehiculo = patenteVehiculo;
    }

    public int getMetodoDePagoId() {
        return metodoDePagoId;
    }

    public void setMetodoDePagoId(int metodoDePagoId) {
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
