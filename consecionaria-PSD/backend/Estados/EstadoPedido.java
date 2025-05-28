package backend.Estados;

import java.sql.Date;

import backend.Areas.PatronIObserver.IAreaObserver;

public class EstadoPedido {
    private EstadoPosiblesPedido estado;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private IAreaObserver areaResponsable;
}
