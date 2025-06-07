package Vehiculos.Entidades;

import java.util.List;

import Vehiculos.Builder.IVehiculoBuilder;
import Vehiculos.Caracteristicas.Color;
import Vehiculos.Caracteristicas.ConfiguracionAdicional;
import Vehiculos.State.VehiculoEstado;

public abstract class Vehiculo implements IVehiculoBuilder{
    private String marca;
    private String modelo;
    private int año;
    private Color color;
    private String patente;
    private int numeroChasis;
    private int numeroMotor;
    private double precio;
    private List<ConfiguracionAdicional> adicionales;
    private VehiculoEstado vehiculoEstado;
    
    // cada tipo de vehiculo tiene distinta cantidad de impuestos
    public abstract double calcularImpuestos();

    public double calcularPrecioFinal(){
        return (precio + calcularImpuestos() + calcularTotalAdicionales() );
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
                + ", adicionales=" + adicionales + ", vehiculoEstado=" + vehiculoEstado + "]";
    }



    


}
