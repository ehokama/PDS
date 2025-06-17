package com.example.ConsecionariaPDS.controllers;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ConsecionariaPDS.models.MetodosDePago.MetodoDePago;
import com.example.ConsecionariaPDS.repositories.MetodoDePagoRepositorio;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/metodos") //el endpoint es la URL mediante la cual se accede al recurso
public class MetodoDePagoController {
    private MetodoDePagoRepositorio repositorio;
    
    public MetodoDePagoController(MetodoDePagoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    public ResponseEntity<MetodoDePago> crearUsuario(@RequestBody MetodoDePago metodoDePago){ 
        MetodoDePago guardado = repositorio.save(metodoDePago);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoDePago> obtenerVehiculo(@PathVariable String id){
        Optional<MetodoDePago> opt = repositorio.findById(id);  
        if(opt.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(opt.get());
        }
    }



}
