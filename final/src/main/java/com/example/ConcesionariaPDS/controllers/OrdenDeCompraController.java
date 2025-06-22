package com.example.ConcesionariaPDS.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ConcesionariaPDS.models.MetodosDePago.MetodoDePago;
import com.example.ConcesionariaPDS.models.Ordenes.DatosDeFacturacion;
import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompra;
import com.example.ConcesionariaPDS.models.Ordenes.OrdenDeCompraDTO;
import com.example.ConcesionariaPDS.models.Usuarios.Usuario;
import com.example.ConcesionariaPDS.models.Vehiculos.Entidades.Vehiculo;
import com.example.ConcesionariaPDS.repositories.MetodoDePagoRepositorio;
import com.example.ConcesionariaPDS.repositories.OrdenDeCompraRepositorio;
import com.example.ConcesionariaPDS.repositories.UsuarioRepositorio;
import com.example.ConcesionariaPDS.repositories.VehiculoRepositorio;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/ordenes")
public class OrdenDeCompraController {

    private final OrdenDeCompraRepositorio ordenRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final VehiculoRepositorio vehiculoRepositorio;
    private final MetodoDePagoRepositorio metodoDePagoRepositorio;

    public OrdenDeCompraController(OrdenDeCompraRepositorio ordenRepositorio, UsuarioRepositorio usuarioRepositorio, VehiculoRepositorio vehiculoRepositorio, MetodoDePagoRepositorio metodoDePagoRepositorio) {
        this.ordenRepositorio = ordenRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.vehiculoRepositorio = vehiculoRepositorio;
        this.metodoDePagoRepositorio = metodoDePagoRepositorio;

    }

@PostMapping
public ResponseEntity<?> crearOrden(@RequestBody OrdenDeCompraDTO dto) {
    try {
        // Buscar entidades por identificadores
        Usuario comprador = usuarioRepositorio.findByDni(dto.getCompradorDni())
                .orElseThrow(() -> new RuntimeException("Comprador no encontrado"));
        Usuario vendedor = usuarioRepositorio.findByDni(dto.getVendedorDni())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        Vehiculo vehiculo = vehiculoRepositorio.findByPatente(dto.getPatenteVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        MetodoDePago metodo = metodoDePagoRepositorio.findById(dto.getMetodoDePagoId())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        // Crear datos de facturación
        DatosDeFacturacion datosFacturacion = new DatosDeFacturacion(
                dto.getNombreCompleto(), dto.getCuit(), dto.getDireccion());

        // Crear la orden
        OrdenDeCompra orden = new OrdenDeCompra(
                0, vehiculo, vendedor, comprador, datosFacturacion, metodo);

        ordenRepositorio.save(orden);

        vehiculo.setTipoEstado("Vendido");
        vehiculoRepositorio.save(vehiculo);

        return new ResponseEntity<>(orden, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

    @GetMapping //este método se ejecuta cuando alguien hace un GET a la URL establecida en RequestMapping
    public ResponseEntity<List<OrdenDeCompra>> obtenerOrdenes() {
        List<OrdenDeCompra> lista = ordenRepositorio.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
