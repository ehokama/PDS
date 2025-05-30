package com.example.demo.models.Vehiculos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;
@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vehiculo", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")

@JsonSubTypes({
@JsonSubTypes.Type(value = Auto.class, name = "Auto"),
@JsonSubTypes.Type(value = Moto.class, name = "Moto"),
@JsonSubTypes.Type(value = Camioneta.class, name = "Camioneta"),
@JsonSubTypes.Type(value = Camion.class, name = "Camion")
})


public class Vehiculo {
    @Id
    private String patente;
    private String marca;
    private String modelo;
    private int año;
    @Enumerated(EnumType.STRING)
    private Color color;
    private int numeroChasis;
    private int numeroMotor;
    private int asientos;
    private double precio;
    private boolean disponible;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehiculo_patente") // crea columna tipo key en la tabla
    private List<ConfiguracionAdicional> adicionales = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    private EstadoVehiculo estado;
    private int kilometraje; 
    private String imagenUrl;

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }    


    public double calcularImpuestos(){
        // suma de impuesto nacional + provincial general + provincial adicional
        return ( getPrecio() * ( 0.21 + 0.05 + 0.01 ) );
    }

    public Vehiculo() {}
    
    public Vehiculo(String patente, String marca, String modelo, int año, Color color, int numeroChasis,
            int numeroMotor, int asientos, double precio, boolean disponible, List<ConfiguracionAdicional> adicionales,
            EstadoVehiculo estado, int kilometraje, String imagenUrl) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.asientos = asientos;
        this.precio = precio;
        this.disponible = disponible;
        this.adicionales = adicionales;
        this.estado = estado;
        this.kilometraje = kilometraje;
        this.imagenUrl = imagenUrl;
    }

    public double calcularPrecioFinal(){
        return (precio + calcularImpuestos() + calcularAdicionales() );
    }

    public double calcularAdicionales() {
        double total = 0;
        for (ConfiguracionAdicional configuracionAdicional : adicionales) {
            total+=configuracionAdicional.getPrecio();
        }
        return total;
    }

    public void agregarAdicional(ConfiguracionAdicional adicional){
        adicionales.add(adicional);
    }

    public void eliminarAdicional(ConfiguracionAdicional adicional){
        adicionales.remove(adicional);

    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(int numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public int getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(int numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<ConfiguracionAdicional> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<ConfiguracionAdicional> adicionales) {
        this.adicionales = adicionales;
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }

    public void setEstadoVehiculo(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    
}
