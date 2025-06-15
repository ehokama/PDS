package com.example.ConsecionariaPDS.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ConsecionariaPDS.models.Ordenes.OrdenDeCompra;
import com.example.ConsecionariaPDS.repositories.OrdenDeCompraRepositorio;



@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/ordenes") //el endpoint es la URL mediante la cual se accede al recurso
public class OrdenDeCompraController {
    
    private OrdenDeCompraRepositorio repositorio;

    public OrdenDeCompraController(OrdenDeCompraRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping //este método se ejecuta cuando alguien hace un POST a esta URL
    public ResponseEntity<OrdenDeCompra> crearOrden(@RequestBody OrdenDeCompra orden){ //ResponseEntity es una forma de devolver una respuesta HTTP con codigo(Status Code) y cuerpo(JSON con los datos si es un get)
        OrdenDeCompra guardado = repositorio.save(orden);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

}  