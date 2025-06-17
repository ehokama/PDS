package com.example.ConsecionariaPDS.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ConsecionariaPDS.models.MetodosDePago.MetodoDePago;
import com.example.ConsecionariaPDS.models.Ordenes.DatosDeFacturacion;
import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;
import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompraDTO;
import com.example.ConsecionariaPDS.models.Usuarios.Usuario;
import com.example.ConsecionariaPDS.models.Vehiculos.Entidades.Vehiculo;
import com.example.ConsecionariaPDS.repositories.MetodoDePagoRepositorio;
import com.example.ConsecionariaPDS.repositories.OrdenDeCompraRepositorio;
import com.example.ConsecionariaPDS.repositories.UsuarioRepositorio;
import com.example.ConsecionariaPDS.repositories.VehiculoRepositorio;


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
}
