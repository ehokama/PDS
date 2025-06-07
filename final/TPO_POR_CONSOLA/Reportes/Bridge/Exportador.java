package Reportes.Bridge;

import java.util.List;

import Ordenes.OrdenDeCompra;

public interface Exportador {
    void exportar(List<OrdenDeCompra> ordenes);
}