package com.example.demo.models.Ordenes;

import java.sql.Date;

import com.example.demo.models.Usuarios.Usuario;
import com.example.demo.models.Vehiculos.Vehiculo;


import jakarta.persistence.*;

@Entity
public class OrdenDeCompra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    @Enumerated(EnumType.STRING)
    private EstadoOrden estadoOrden;

    @OneToOne
    @JoinColumn(name = "vehiculo_patente", referencedColumnName = "patente")
    private Vehiculo vehiculo;

    private Date fecha;
    private double total;

    @ManyToOne
    @JoinColumn(name = "usuario_dni", referencedColumnName = "dni")
    private Usuario usuario;

 
    public OrdenDeCompra() {}

    public OrdenDeCompra(Long idOrden, EstadoOrden estadoOrden, Vehiculo vehiculo, Date fecha, double total) {
        this.idOrden = idOrden;
        this.estadoOrden = estadoOrden;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.total = total;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public EstadoOrden getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = vehiculo.calcularPrecioFinal();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    
}
