package com.example.ConsecionariaPDS.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;
import com.example.ConsecionariaPDS.repositories.OrdenDeCompraRepositorio;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/ordenes")
public class OrdenDeCompraController {

    private final OrdenDeCompraRepositorio ordenRepositorio;

    public OrdenDeCompraController(OrdenDeCompraRepositorio ordenRepositorio) {
        this.ordenRepositorio = ordenRepositorio;

    }

    @PostMapping
    public ResponseEntity<OrdenDeCompra> crearOrden(@RequestBody OrdenDeCompra ordenDeCompra) {        
        OrdenDeCompra guardado = ordenRepositorio.save(ordenDeCompra);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }
}
