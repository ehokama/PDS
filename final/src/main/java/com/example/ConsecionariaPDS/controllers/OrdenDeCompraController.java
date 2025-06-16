package com.example.ConsecionariaPDS.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ConsecionariaPDS.models.Ordenes.DatosDeFacturacion;
import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;
import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompraDTO;
import com.example.ConsecionariaPDS.models.Usuarios.Usuario;
import com.example.ConsecionariaPDS.models.Vehiculos.Entidades.Vehiculo;
import com.example.ConsecionariaPDS.models.MetodosDePago.MetodoDePago;
import com.example.ConsecionariaPDS.repositories.OrdenDeCompraRepositorio;
import com.example.ConsecionariaPDS.repositories.UsuarioRepositorio;
import com.example.ConsecionariaPDS.repositories.VehiculoRepositorio;
import com.example.ConsecionariaPDS.repositories.MetodoDePagoRepositorio;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/ordenes")
public class OrdenDeCompraController {

    private final OrdenDeCompraRepositorio ordenRepositorio;
    private final VehiculoRepositorio vehiculoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final MetodoDePagoRepositorio metodoPagoRepositorio;

    public OrdenDeCompraController(OrdenDeCompraRepositorio ordenRepositorio,
                                   VehiculoRepositorio vehiculoRepositorio,
                                   UsuarioRepositorio usuarioRepositorio,
                                   MetodoDePagoRepositorio metodoPagoRepositorio) {
        this.ordenRepositorio = ordenRepositorio;
        this.vehiculoRepositorio = vehiculoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.metodoPagoRepositorio = metodoPagoRepositorio;
    }

    @PostMapping
    public ResponseEntity<?> crearOrden(@RequestBody OrdenDeCompraDTO dto) {
        try {
            // Buscar entidad Vehiculo
            Vehiculo vehiculo = vehiculoRepositorio.findById(dto.getPatenteVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

            // Buscar comprador
            Usuario comprador = usuarioRepositorio.findByDni(dto.getCompradorDni())
                .orElseThrow(() -> new RuntimeException("Comprador no encontrado"));

            // Buscar vendedor
            Usuario vendedor = usuarioRepositorio.findByDni(dto.getDniVendedor())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

            // Buscar método de pago
            MetodoDePago metodoPago = metodoPagoRepositorio.findById(dto.getMetodoDePagoId())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

            // Crear datos de facturación
            DatosDeFacturacion datosFacturacion = new DatosDeFacturacion(
                dto.getNombreCompleto(),
                dto.getCuit(),
                dto.getDireccion()
            );

            // Crear nueva orden de compra
            OrdenDeCompra orden = new OrdenDeCompra(
                0, // numeroDeOrden, si es autogenerado puede pasarse 0 o no pasarse
                vehiculo,
                vendedor,
                comprador,
                datosFacturacion,
                metodoPago
            );

            // Guardar orden
            OrdenDeCompra ordenGuardada = ordenRepositorio.save(orden);

            return new ResponseEntity<>(ordenGuardada, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
