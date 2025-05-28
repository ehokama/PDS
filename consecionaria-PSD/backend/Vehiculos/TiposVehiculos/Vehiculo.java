package backend.Vehiculos.TiposVehiculos;

import java.util.List;

import backend.Vehiculos.Caracteristicas.Color;
import backend.Vehiculos.Caracteristicas.ConfiguracionAdicional;
import backend.Vehiculos.Caracteristicas.Estado;

public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private int año;
    private Color color;
    private int patente;
    private int numeroChasis;
    private int numeroMotor;
    private int asientos;
    private double precio;
    private boolean disponible;
    private List<ConfiguracionAdicional> adicionales;
    private Estado estado;

    public abstract double calcularImpuestos();

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

    public int getPatente() {
        return patente;
    }

    public void setPatente(int patente) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
}
