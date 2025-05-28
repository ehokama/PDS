package backend.Estados;

import java.sql.Date;

import backend.Areas.PatronIObserver.IAreaObserver;

public class EstadoPedido {
    private EstadoPosiblesPedido estado = EstadoPosiblesPedido.PENDIENTE; // valor por defecto
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private IAreaObserver areaResponsable;

    public EstadoPosiblesPedido getEstado() {
        return estado;
    }
    public void setEstadoPosible(EstadoPosiblesPedido estado) {
        this.estado = estado;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }
    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
    public IAreaObserver getAreaResponsable() {
        return areaResponsable;
    }
    public void setAreaResponsable(IAreaObserver areaResponsable) {
        this.areaResponsable = areaResponsable;
    }

    
}
