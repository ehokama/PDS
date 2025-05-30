package com.example.demo.models.Areas;

import java.sql.Date;

import com.example.demo.models.Areas.PatronHandler.HandlerEtapa;
import com.example.demo.models.Areas.PatronIObserver.IAreaObserver;
import com.example.demo.models.Estados.EstadoPedido;
import com.example.demo.models.Pedidos.OrdenDeCompra;

public class Ventas extends Area implements HandlerEtapa, IAreaObserver {
    private static Ventas instancia = null;
    private HandlerEtapa siguienteHandler = Cobranzas.getInstancia() ;;

    private  Ventas() {
        setNombre("Ventas");
    }

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        System.out.println("Ventas recibió una actualización del pedido #" + ordenDeCompra.getNumeroPedido());

    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {

        System.out.println("Ventas está procesando el pedido #" + ordenDeCompra.getNumeroPedido()); //1. la procesa el area actual
        ordenDeCompra.getEstadoPedido().setFinalizado(true);       //2. al procesarla le cambia el estado a finalizada (finaliza esta etapa), ,
        ordenDeCompra.getEstadoPedido().setFechaFinalizacion(new Date(System.currentTimeMillis()));//2bis. al procesarla le cambia el estado a finalizada (finaliza esta etapa),
        ordenDeCompra.añadirEstadoAlHistorial(ordenDeCompra.getEstadoPedido());                     //3. agrega el estado al historial de la orden

        if(siguienteHandler!=null){
            ordenDeCompra.setEstadoPedido( new EstadoPedido(Cobranzas.getInstancia()) );            //4.  crea un nuevo estado, se lo asigna al nextHandler
            ordenDeCompra.setAreaActual((IAreaObserver) siguienteHandler);                          //5.  le asigna la proxima area al Area de la orden
            System.out.println("se paso a la area: "+ this.siguienteHandler);                       //6.  avisa que se le pasa al otra area
            siguienteHandler.handle(ordenDeCompra);                                                 //7.  el otro area empieza a manejar la orden
        }else{
            System.out.println("Proceso del pedido "+ordenDeCompra.getNumeroPedido() +" finalizado.");
        }
    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        this.siguienteHandler = handler;
    }

    // Método estático para obtener la instancia
    public static Ventas getInstancia() {
        if (instancia == null) {
            instancia = new Ventas();
        }
        return instancia;
    }

}
