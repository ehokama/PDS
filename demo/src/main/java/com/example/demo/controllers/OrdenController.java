package com.example.demo.controllers;

import java.sql.Date;
import java.util.List;
asdasd
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Ordenes.OrdenDeCompra;
import com.example.demo.models.Vehiculos.Vehiculo;
import com.example.demo.repositories.OrdenDeCompraRepositorio;
import com.example.demo.repositories.VehiculoRepositorio;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class OrdenController {
    OrdenDeCompraRepositorio repositorio;
    private final VehiculoRepositorio vehiculoRepositorio;

    public OrdenController(OrdenDeCompraRepositorio repositorio, VehiculoRepositorio vehiculoRepositorio) {
        this.repositorio = repositorio;
        this.vehiculoRepositorio = vehiculoRepositorio;
    }
    

@PostMapping("/api/ordenes")
public ResponseEntity<?> guardarOrden(@RequestBody OrdenDeCompra orden) {
    if (orden == null){
        return ResponseEntity.badRequest().build();
    }

    // Verificar que vehiculo y patente existan
    if (orden.getVehiculo() == null || orden.getVehiculo().getPatente() == null) {
        return ResponseEntity.badRequest().body("Vehiculo o patente no proporcionados");
    }

    // Buscar vehículo en la base de datos
    Vehiculo vehiculoExistente = vehiculoRepositorio.findById(orden.getVehiculo().getPatente()).orElse(null);
    if (vehiculoExistente == null) {
        return ResponseEntity.badRequest().body("Vehiculo con patente no existe");
    }

    // Asignar vehículo completo a la orden antes de guardar
    orden.setVehiculo(vehiculoExistente);
    orden.setFecha(new Date(System.currentTimeMillis())); 
    orden.setTotal();

    repositorio.save(orden);
    return ResponseEntity.ok(orden);
}

    @GetMapping("/api/ordenes")
    public List<OrdenDeCompra> obtenerOrdenes(){
        return repositorio.findAll();   
    }
    


    
}
