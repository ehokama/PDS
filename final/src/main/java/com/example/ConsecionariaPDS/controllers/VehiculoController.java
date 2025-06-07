package com.example.ConsecionariaPDS.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.ConsecionariaPDS.models.Vehiculos.Entidades.Vehiculo;
import com.example.ConsecionariaPDS.repositories.VehiculoRepositorio;



@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/vehiculos") //el endpoint es la URL mediante la cual se accede al recurso
public class VehiculoController {
    
    private VehiculoRepositorio repositorio;

    public VehiculoController(VehiculoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping //este método se ejecuta cuando alguien hace un POST a esta URL
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody Vehiculo vehiculo){ //ResponseEntity es una forma de devolver una respuesta HTTP con codigo(Status Code) y cuerpo(JSON con los datos si es un get)
        Vehiculo guardado = repositorio.save(vehiculo);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @GetMapping //este método se ejecuta cuando alguien hace un GET a la URL establecida en RequestMapping
    public ResponseEntity<List<Vehiculo>> obtenerVehiculos() {
        List<Vehiculo> lista = repositorio.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}  