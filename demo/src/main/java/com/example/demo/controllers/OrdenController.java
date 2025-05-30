package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Pedidos.OrdenDeCompra;
import com.example.demo.repositories.OrdenRepositorio;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class OrdenController {
    OrdenRepositorio repositorio;

    public OrdenController(OrdenRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/api/ordenes")
    public ResponseEntity<?> guardarOrden(@RequestBody OrdenDeCompra orden) {
        if (orden == null){
            return ResponseEntity.badRequest().build();
        }
        repositorio.save(orden);
        return ResponseEntity.ok(orden);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/api/ordenes")
    public List<OrdenDeCompra> obtenerOrdenes(){
        return repositorio.findAll();   
    }
    
    
}
