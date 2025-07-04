package com.example.ConcesionariaPDS.controllers;

import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ConcesionariaPDS.models.Vehiculos.Entidades.Vehiculo;
import com.example.ConcesionariaPDS.repositories.VehiculoRepositorio;



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

    @GetMapping("/{patente}")
    public ResponseEntity<Vehiculo> obtenerVehiculo(@PathVariable String patente){
        Optional<Vehiculo> opt = repositorio.findById(patente);  
        if(opt.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(opt.get());
        }
    }

    @PutMapping("/{patente}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable String patente, @RequestBody Vehiculo vehiculo) {
        Optional<Vehiculo> optional = repositorio.findByPatente(patente);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repositorio.save(vehiculo);
        return ResponseEntity.ok(vehiculo);
    }

}  