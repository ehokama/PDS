package com.example.demo.models.Estados;

import java.sql.Date;

import com.example.demo.models.Areas.Area;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroPedido;
    
    private boolean finalizado;
    private Date fechaInicio;
    private Date fechaFinalizacion; 
    @ManyToOne
    private Area areaResponsable;
    
    public EstadoPedido(Area areaResponsable) {
        this.finalizado = false;                                    // valor por defecto, ya que cuando se inicia un estado no se finalizo aun
        this.fechaInicio = new Date(System.currentTimeMillis());     // valor por defecto, siempre la fechainicial es cuando se crea
        this.fechaFinalizacion = null;                               // null hasta que se pase a la proxima area (hasta se finalice este estado)
        this.areaResponsable = areaResponsable;                      // area actual
    }


    public void finalizarEstadoPosible(){
        this.finalizado = true;
        this.fechaFinalizacion = new Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "EstadoPedido [finalizado=" + finalizado + ", fechaInicio=" + fechaInicio + ", fechaFinalizacion="
                + fechaFinalizacion + ", areaResponsable=" + areaResponsable + "]";
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }
    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }
    public Area getAreaResponsable() {
        return this.areaResponsable;
    }


    public boolean getEstadoPosible() {
        return finalizado;
    }


    public EstadoPedido getEstadoPedido() {
        return this;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }


    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }


    public void setAreaResponsable(Area areaResponsable) {
        this.areaResponsable = areaResponsable;
    }

    
}
