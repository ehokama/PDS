package backend.Areas;

import backend.Areas.PatronHandler.HandlerEtapa;
import backend.Areas.PatronIObserver.IAreaObserver;
import backend.Pedidos.OrdenDeCompra;

public class Logistica implements HandlerEtapa, IAreaObserver{

    @Override
    public void update(OrdenDeCompra ordenDeCompra) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void setNextHandler(HandlerEtapa handler) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNextHandler'");
    }

    @Override
    public void handle(OrdenDeCompra ordenDeCompra) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
    
}
