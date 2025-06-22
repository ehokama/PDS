package com.example.ConcesionariaPDS.models.Vehiculos.Entidades;

import java.util.ArrayList;
import java.util.List;

import com.example.ConcesionariaPDS.models.Vehiculos.Builder.IVehiculoBuilder;
import com.example.ConcesionariaPDS.models.Vehiculos.Caracteristicas.Color;
import com.example.ConcesionariaPDS.models.Vehiculos.Caracteristicas.ConfiguracionAdicional;
import com.example.ConcesionariaPDS.models.Vehiculos.State.VehiculoEstado;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;



@Entity	
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vehiculo")
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo_vehiculo"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Auto.class, name = "Auto"),
    @JsonSubTypes.Type(value = Camioneta.class, name = "Camioneta"),
    @JsonSubTypes.Type(value = Moto.class, name = "Moto"),
    @JsonSubTypes.Type(value = Camion.class, name = "Camion"),
})

public abstract class Vehiculo implements IVehiculoBuilder{
    
    @Id
    private String patente;

    private String marca;
    private String modelo;
    private int año;
    private int kilometraje;

    @Enumerated(EnumType.STRING)
    private Color color;

    private int numeroChasis;
    private int numeroMotor;
    private double precio;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehiculo_patente") // clave foránea en la tabla de adicionales
    private List<ConfiguracionAdicional> adicionales = new ArrayList<>();
    
    
    @Transient
    private VehiculoEstado vehiculoEstado;
    
    private String tipoEstado;  
    private String imagenUrl;

    public  Vehiculo(){
    }

    // cada tipo de vehiculo tiene distinta cantidad de impuestos
    public abstract double calcularImpuestos();

    public double calcularPrecioFinal(){
        return (precio + calcularImpuestos() + calcularTotalAdicionales() );
    }
    
    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    private double calcularTotalAdicionales() {
        double total = 0;
        for (ConfiguracionAdicional configuracionAdicional : adicionales) {
            total+=configuracionAdicional.getPrecio();
        }
        return total;
    }


    public void setVehiculoEstado(VehiculoEstado vehiculoEstado) {
        this.vehiculoEstado = vehiculoEstado;
    }

    public void setDisponible(){
        vehiculoEstado.setDisponible();
    }

    public void setProcesoDeVenta(){
        vehiculoEstado.setProcesoDeVenta();
    }

    public void setVendido(){
        vehiculoEstado.setVendido();
    }

    public VehiculoEstado getVehiculoEstado() {
        return vehiculoEstado;
    }

    public void agregarAdicional(ConfiguracionAdicional adicional){
        adicionales.add(adicional);
    }

    public void eliminarAdicional(ConfiguracionAdicional adicional){
        adicionales.remove(adicional);

    }

    // getters y setters

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


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<ConfiguracionAdicional> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(List<ConfiguracionAdicional> adicionales) {
        this.adicionales = adicionales;
    }

    @Override
    public String toString() {
        return "Vehiculo [marca=" + marca + ", modelo=" + modelo + ", año=" + año + ", color=" + color + ", patente="
                + patente + ", numeroChasis=" + numeroChasis + ", numeroMotor=" + numeroMotor + ", precio=" + precio +", precioFinalConImpuestosYAccesorios=" + calcularPrecioFinal()
                + ", adicionales=" + adicionales + ", estado=" + tipoEstado +"]";
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }



    


}
