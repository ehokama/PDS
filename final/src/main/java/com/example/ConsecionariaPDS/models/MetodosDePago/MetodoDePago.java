package com.example.ConsecionariaPDS.models.MetodosDePago;

public abstract class MetodoDePago {
    
    private String nombre;
    private String descripcion;
    private double recargoPorcentaje;

    public MetodoDePago(String descripcion, String nombre, double recargoPorcentaje) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.recargoPorcentaje = recargoPorcentaje;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getRecargoPorcentaje(){
        return this.recargoPorcentaje;
    }

    public double calcularRecargo(double montoBase) {
        return montoBase * (recargoPorcentaje / 100.0);
    }

}