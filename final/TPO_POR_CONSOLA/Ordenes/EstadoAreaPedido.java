package Ordenes;

import java.time.LocalDateTime;

import Areas.Area;

//estado específico de procesamiento o flujo interno del pedido: en qué área está, cuándo inició y finalizó, si está finalizado o no.

public class EstadoAreaPedido {
    private int numeroPedido;
    private boolean finalizado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion; 
    private Area areaResponsable;
    
    public EstadoAreaPedido( int numeroPedido, Area areaResponsable) {
        this.numeroPedido = numeroPedido;
        this.finalizado = false;                                    // valor por defecto, ya que cuando se inicia un estado no se finalizo aun
        this.fechaInicio = LocalDateTime.now();                     // valor por defecto, siempre la fechainicial es cuando se crea
        this.fechaFinalizacion = null;                               // null hasta que se pase a la proxima area (hasta se finalice este estado)
        this.areaResponsable = areaResponsable;                      // area actual
    }
    
    public void finalizarEstadoPedido(){ 
        this.finalizado = true;
        this.fechaFinalizacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "EstadoPedido [numeroPedido=" + numeroPedido + ", finalizado=" + finalizado + ", fechaInicio="
                + fechaInicio + ", fechaFinalizacion=" + fechaFinalizacion + ", areaResponsable=" + areaResponsable
                + "]";
    }

    public boolean getFinalizado(){
        return finalizado;
    }
    public EstadoAreaPedido clonar() {
        EstadoAreaPedido copia = new EstadoAreaPedido(this.numeroPedido, this.areaResponsable);
        copia.numeroPedido = this.numeroPedido;
        copia.finalizado = this.finalizado;
        copia.fechaInicio = this.fechaInicio;
        copia.fechaFinalizacion = this.fechaFinalizacion;
        return copia;
}
}
